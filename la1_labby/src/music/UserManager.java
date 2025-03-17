package music;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {
	
	
	private LibraryModel libMod;
	
	
	public UserManager(LibraryModel libModel) {
		this.libMod = libModel;
		writeInFile("userinfo.txt", ":", false);
	}
	
	
	private void writeInFile(String filename, String content, boolean dontOverwrite) {
		try (FileWriter writer = new FileWriter(filename, dontOverwrite)) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addUser(String username, String password) {
		writeInFile(username + ".txt", "", false);
		password = saltPassword(password);
		writeInFile("userinfo.txt", username + "," + password +"\n", true);
	}
	
	
	// more secure password
	private String saltPassword(String password) {
		return password;
	}
	
	
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
	
	
	public boolean checkForExistingUsername(String username){
		// see if username is currently in file
		return true;
	}
	
	
	public boolean checkPassword(String username, String password) {
		// check username/password pair
		return true;
	}
	
	
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