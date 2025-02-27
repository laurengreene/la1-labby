package music;

import java.util.ArrayList;

public class SongList {
	
	// instance variables
	private ArrayList<Song> slist;
	private String name;
	
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
	
	public String getSongByTitle(String title) {
		String result = "";
		for (Song s : slist) {
			if (s.getTitle().toLowerCase().equals(title.toLowerCase())) {
				result += s.toString() + "\n";
			}
		}
		if (result.length() == 0) return "Not found";
		return result.substring(0, result.length()-1);
		
//		ArrayList<Song> songs = new ArrayList<Song>();
//		for (Song s : slist) {
//			if (s.getTitle().equals(title)) {
//				songs.add(s.makeCopy());
//			}
//		}
//		return songs;
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
		
//		ArrayList<Song> songs = new ArrayList<Song>();
//		for (Song s : slist) {
//			if (s.getArtist().equals(artist)) {
//				songs.add(s.makeCopy());
//			}
//		}
//		return songs;
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
