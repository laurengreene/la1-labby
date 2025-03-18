
package music;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UserManager {
	
	
	private LibraryModel libMod;
	private ArrayList<User> userList;
	
	public UserManager(LibraryModel libModel) {
		this.libMod = libModel;
		this.userList = new ArrayList<User>();
		writeInFile("userinfo.txt", "", true);
		loadInUsers();
	}
	
	
	private void writeInFile(String filename, String content, boolean dontOverwrite) {
		try (FileWriter writer = new FileWriter(filename, dontOverwrite)) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// SET UP
	private void loadInUsers() {
		// read in existing users from userinfo.txt file
		try (BufferedReader reader = new BufferedReader(new FileReader("userinfo.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");  // composed of username, salt, password
				User user = new User(parts[0], parts[1], parts[2]);
				userList.add(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// ADDING USER
	
	public void addUser(String username, String password) {
		writeInFile(username + ".txt", "", true);
		String salt = getSalt();
		String saltedPassword = password + salt;
		String saltedHashed = hashPassword(saltedPassword);
		User user = new User(username, salt, saltedHashed);
		writeInFile("userinfo.txt", username + "," + salt + "," + saltedHashed + "\n", true);
		userList.add(user);
	}
	
	private String getSalt() {
		// salt password
		byte[] salt = new byte[16];
		new SecureRandom().nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}
	
	private String hashPassword(String password) {
		MessageDigest md;
		String hashedPassword = "";
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] hashing = md.digest(password.getBytes());
			hashedPassword = Base64.getEncoder().encodeToString(hashing);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashedPassword;
	}
	
	
	// CHECKING USER DATA
	
	public boolean usernameExists(String username){
		// see if username is currently in file
		// need to read file
		try (BufferedReader reader = new BufferedReader(new FileReader("userinfo.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] items = line.split(",");
				if (items[0].equals(username)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// check if password matches
	public boolean checkPassword(String username, String password) {
		try (BufferedReader reader = new BufferedReader(new FileReader("userinfo.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");  // composed of username, salt, and password
				if (parts[0].equals(username)) {  // find the user
					User user = getUser(username);
					String inputtedPassword = hashPassword(password + user.getSalt());
					if (inputtedPassword.equals(user.getSecurePassword())) return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private User getUser(String username) {
		for (User u : userList) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	
	

	
	
	
	// SAVING DATA WHEN LOGGING OUT
	
	// stores library songs, rated songs, and user made playlists into a text file
	public void turnUserDataIntoFile(String username) {
		ArrayList<Song> libSongs = libMod.getLibrarySongs();
//		HashMap<String, ArrayList<Song>> libPlaylists = libMod.getPlaylistsForFile();
//		HashMap<Song, Rating> ratedSongs = libMod.getRatedSongs();
		
		String content = turnUserDataIntoFileHelper(libSongs); //libPlaylists, ratedSongs);
		writeInFile(username + ".txt", content, false);
	}
	
	
	private String turnUserDataIntoFileHelper(ArrayList<Song> libSongs)
//											HashMap<String, ArrayList<Song>> libPlaylists,
//											HashMap<Song, Rating> ratedSongs)
			{
		
		// song data
		String songs = "";
		for (Song s : libSongs) {
			songs += s.toStringFile() + "\n";
		}
		songs = songs.substring(0, songs.length() - 1) + "**";
		
		// playlist data
		String playlists = "";
		
		// rated song data
		String ratedSongsStr = "";
		return songs + playlists + ratedSongsStr;
	}
	
	

	
	// GETTING DATA WHEN LOGGING IN
	public void getUserData(String username) {
		File userInfo = new File(username + ".txt");
		try (Scanner scn = new Scanner(userInfo)) {
			readInSongs(scn);  // separated by commas
			// albums can be calculated from songs:
			// add user made playlists to library
			// create rated songs hashmap
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}
	
	private void readInSongs(Scanner scn) {
		// add songs to library
		while (scn.hasNext()) {
			String line = scn.next();
			if (line.equals("**")) break;
			String[] songDeets = line.split(",");
			libMod.addSongToLib(new Song(songDeets[0], songDeets[1], 
					songDeets[2], songDeets[3], songDeets[4]));
		}
	}
}
