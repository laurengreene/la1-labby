package music;

import java.util.ArrayList;

public class SongList {
	
	// instance variables
	private ArrayList<Song> slist;
	private String name;  // playlists have a name, but some don't need one
	
	// constructor
	public SongList() {
		this.slist = new ArrayList<Song>();
	}
	
	// methods
	public void addSong(Song newSong) {
		slist.add(newSong);
	}
	
	public ArrayList<Song> getSongByTitle(String title) { // fix escaping reference!
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song s : slist) {
			if (s.getTitle().equals(title)) {
				songs.add(s);
			}
		}
		return songs;
	}
	
	public ArrayList<Song> getSongByArtist(String artist) {  // fix escaping reference!
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song s : slist) {
			if (s.getArtist().equals(artist)) {
				songs.add(s);
			}
		}
		return songs;
	}
	
	public ArrayList<Song> getSongs() {  // fix escaping reference!
		return slist;
	}
	
	public boolean containsSong(Song song) {
		return slist.contains(song);
	}
	
	public String toString() {
		String result = "";
		for (Song s : slist) {
			result += s.getTitle() + "\n";
		}
		return result;
	}

}
