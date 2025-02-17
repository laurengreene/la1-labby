package music;

import java.util.ArrayList;

public class SongList {
	
	// methods to add: get song by title, artist
	
	// instance variables
	private ArrayList<Song> slist;
	
	// constructor
	public SongList() {
		this.slist = new ArrayList<Song>();
	}
	
	// methods
	public void addSong(Song newSong) {
		slist.add(newSong);
	}
	
	public String toString() {
		String result = "";
		for (Song s : slist) {
			result += s.getTitle() + "\n";
		}
		return result;
	}

}
