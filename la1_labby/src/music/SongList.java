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
		slist.add(newSong.makeCopy());
	}
	
	public void removeSong(Song removeSong) {
		if(slist.contains(removeSong)) slist.remove(removeSong);
	}
	
	public ArrayList<Song> getSongByTitle(String title) { // I think this should fix escaping reference? 
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song s : slist) {
			if (s.getTitle().equals(title)) {
				songs.add(s.makeCopy());
			}
		}
		return songs;
	}
	
	public ArrayList<Song> getSongByArtist(String artist) { 
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song s : slist) {
			if (s.getArtist().equals(artist)) {
				songs.add(s.makeCopy());
			}
		}
		return songs;
	}
	
	// return deep copy of list
	public ArrayList<Song> getSongs() {
		return makeCopyList();
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
	
	// make deep copy of List
	public ArrayList<Song> makeCopyList() {
		ArrayList<Song> cList = new ArrayList<Song>();
		for (Song s : slist) {
			cList.add(s.makeCopy());
		}
		return cList;
	}
	
	public String toString() {
		String result = "";
		for (Song s : slist) {
			result += s.getTitle() + "\n";
		}
		return result;
	}
	
	public void addNewSong(Song song) {
		slist.add(song);
		
	}

}
