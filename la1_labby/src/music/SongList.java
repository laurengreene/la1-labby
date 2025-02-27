
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
			if (s.getTitle().toLowerCase().equals(title.toLowerCase())) {
				result += s.toString() + "\n";
			}
		}
		if (result.length() == 0) return "Not found";
		return result.substring(0, result.length()-1);
		

	}
	
	public ArrayList<Song> getSongObjectByTitle(String title) {
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song s : slist) {
			if (s.getTitle().toLowerCase().equals(title.toLowerCase())) {
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
			if (s.getArtist().toLowerCase().equals(artist.toLowerCase()) &&
					s.getTitle().toLowerCase().equals(title.toLowerCase())) {
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
	
	public void addNewSong(Song song) {
		slist.add(song);
		
	}

}