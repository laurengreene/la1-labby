

/*
 * SongList.java is an ArrayList of songs. It is used in
 * MusicStore and LibraryModel for a list of songs
 */


package music;

import java.util.ArrayList;
import java.util.Collections;

public class SongList {
	
	// instance variables
	private ArrayList<Song> slist;
	private String name;
	
	// constructor
	public SongList() {
		this.slist = new ArrayList<Song>();
		this.name = "";
	}
	
	// methods
	public void addSong(Song newSong) {
		// check if song already in list
		for(Song s : slist) {
			if (newSong.equals(s)) return;
		}
		slist.add(newSong);
	}
	
	public String removeSong(Song removeSong) {
		if(slist.contains(removeSong)) {
			slist.remove(removeSong);
			return "Song Removed";
		}
		return "Song Not Found";
	}
	
	public String getSongByTitle(String title) {
		String result = "";
		for (Song s : slist) {
			if (s.getTitle().equalsIgnoreCase(title)) {
				result += s.toString() + "\n";
			}
		}
		if (result.length() == 0) return "Not found";
		return result.substring(0, result.length()-1);
	}
	
	public ArrayList<Song> getSongObjectByTitle(String title) {
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song s : slist) {
			if (s.getTitle().toLowerCase().trim().equals(title.toLowerCase().trim())) {
				songs.add(s);
			}
		}
		return songs;
	}
	
	public String getSongByArtist(String artist) { 
		String result = "";
		for (Song s : slist) {
			if (s.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				result += s.toString() + "\n";
			}
		}
		if (result.length() == 0) return "Not found";
		return result.substring(0, result.length()-1);
	}
	

	public ArrayList<Song> getSongObjectByArtist(String artist) {
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song s : slist) {
			if (s.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				songs.add(s);
			}
		}
		return songs;
	}

	public Song getSongByTitleAndArtist(String artist, String title) {
		for (Song s : slist) {
			if (s.getArtist().equalsIgnoreCase(artist) &&
					s.getTitle().equalsIgnoreCase(title)) {
				return s;
			}
		}
		return null;
	}
	
	// shallow copy of the list
	public ArrayList<Song> getSongs() {
		ArrayList<Song> cList = new ArrayList<Song>();
		for (Song s : slist) {
			cList.add(s);
		}
		return cList;
	}
	
	public boolean containsSong(Song song) {
		for (Song s : slist) {
			if (s.equals(song)) return true;
		}
		return false;
	}
	
	public String getPlaylistName() {
		return name;
	}
	
	public void setPlaylistName(String n) {
		this.name = n;
	}
	
	public String toString() {
		String result = "";
		if(name != null) {
			result += "Playlist " + name + ": \n";
		}
		for (Song s : slist) {
			result += s.getTitle() + "\n";
		}
		return result;
	}
	
	public ArrayList<Song> sortedSongsByTitle() {
		ArrayList<Song> allSongs = new ArrayList<Song>();
		for(Song s : slist) {
			boolean added = false;
			for(int i = 0; i < allSongs.size(); i++) {
				if (s.getTitle().compareTo(allSongs.get(i).getTitle()) < 0) {
					allSongs.add(i, s);
					added = true;
					break;
				}
			}
			if(added == false) {
				allSongs.add(s);
			}
				
		}
		return allSongs;
	}
	
	public ArrayList<Song> sortedSongsByArtist() {
		ArrayList<Song> allSongs = new ArrayList<Song>();
		for(Song s : slist) {
			boolean added = false;
			for(int i = 0; i < allSongs.size(); i++) {
				if (s.getArtist().compareTo(allSongs.get(i).getArtist()) < 0) {
					allSongs.add(i, s);
					added = true;
					break;
				}
			}
			if(added == false) {
				allSongs.add(s);
			}
				
		}
		return allSongs;
	}
	
	public void shuffleSongs() {
		Collections.shuffle(slist);
	}
}