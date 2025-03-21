


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
	
	// stores library songs, rated songs, and user made playlists into a text file. each section is split by a '**'
	public void turnUserDataIntoFile(String username) {
		String content = readOutSongData();  // song data that is file ready
		content += "\n**\n";
		content += readOutUserPlaylists();
		content += "\n**\n";
		System.out.println(content);  // ************************************************************************prints out contents of user's file**
		writeInFile(username + ".txt", content, false);
	}
	
	private String readOutSongData() {
		// song data
		ArrayList<Song> libSongs = libMod.getLibrarySongs();
		String songs = "";
		for (Song s : libSongs) {
			songs += s.toString() + "\n";  // song attributes are separated by commas
		}
		if (songs.length() == 0) return "no songs";
		songs = songs.substring(0, songs.length() - 1);
		return "songs:\n" + songs;
	}
	
	private String readOutUserPlaylists() {
		// get user playlists
		String content = "";
		ArrayList<SongList> ulists = libMod.getUserPlaylists();
		for (SongList slist : ulists) {
			content += slist.getPlaylistName();
			for (Song s : slist.getSongs()) {
				content += "," + s.getArtist() + "," + s.getTitle();
			}
			content += "\n";
		}
		if (content.length() == 0) return "no playlists";
		content = content.substring(0, content.length() - 1);
		return "playlists: \n" + content;
	}
	
	
	
	// GETTING DATA WHEN LOGGING IN
	public void getUserData(String username) {
		File userInfo = new File(username + ".txt");
		try (Scanner scn = new Scanner(userInfo)) {
			readInSongs(scn);  // reads in song, album, genre info
			readInPlaylists(scn);  // reads in user playlists
			// read in played songs
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}
	
	private void readInSongs(Scanner scn) {
		// add songs to library
		String firstLine = "";
		if(scn.hasNext()) firstLine = scn.nextLine();
		if (firstLine.equals("no songs")) {
			if(scn.hasNext())scn.nextLine();
			return;
		}
		while (scn.hasNext()) {
			String line = scn.nextLine();
			if (line.equals("**")) break;
			String[] songInfo = line.split(",");
			libMod.addSongToLib(new Song(songInfo[0], songInfo[1], 
					songInfo[2], songInfo[3], songInfo[4]));
		}
		readInAlbums();  // only adds albums if there are songs in library
	}
	
	private void readInAlbums() {
		// find all albums from added songs
		HashMap<String, SongList> albums = new HashMap<String, SongList>();
		for (Song s : libMod.getLibrarySongs()) {
			String albumName = s.getAlbumTitle();
			if (!albums.containsKey(albumName)) {
				albums.put(albumName, new SongList());
			}
			albums.get(albumName).addSong(s);
		}
		
		// add albums to library and add only songs that were added to the library
		for (HashMap.Entry<String, SongList> entry : albums.entrySet()) {
			Song song = entry.getValue().getSongs().get(0);  // get album attributes from song
			Album album = new Album(song.getArtist(), song.getAlbumTitle(),
					song.getGenre(), song.getYear(), entry.getValue().getSongs());
			libMod.addAlbumNotSongs(album);  // add to libMod if not already in libMod
		}
	}
	
	private void readInPlaylists(Scanner scn) {
		// need to read in user playlists, but not favorite or top rated
		String firstLine = "";
		if(scn.hasNext())firstLine = scn.nextLine();
		if (firstLine.equals("no playlists")) {
			if(scn.hasNext()) scn.nextLine(); return;
		}
		
		while(scn.hasNext()) {
			String line = scn.nextLine();
			if (line.equals("**")) break;
			String[] playlistInfo = line.split(",");
			String name = playlistInfo[0];
			libMod.addSongToPlaylist(name, playlistInfo[1], playlistInfo[2]);
		}
	}
}