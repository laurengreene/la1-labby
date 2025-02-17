package music;

import java.util.ArrayList;

public class AlbumList {
	
	// methods to add: 
	// add album
	// search for albums by artist
	// get album object (by title or by artist)
	
	// instance variables
	private ArrayList<Album> alist;
	
	// constructor
	public AlbumList() {
		this.alist = new ArrayList<Album>();
	}
	
	// methods
	public void addAlbum(Album newAlbum) {
		alist.add(newAlbum);
	}
	
	public String toString() {
		String result = "";
		for (Album a : alist) {
			result += a.getTitle() + "\n";
		}
		return result;
	}
	
}
