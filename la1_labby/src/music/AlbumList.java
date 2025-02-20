package music;

import java.util.ArrayList;

public class AlbumList {
	
	// methods to add: 
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
	
	public ArrayList<Album> getAlbumByTitle(String title) { // fix escaping reference!
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a : alist) {
			if (a.getTitle().equals(title)) {
				albums.add(a);
			}
		}
		return albums;
	}
	
	public ArrayList<Album> getAlbumByArtist(String artist) { // fix escaping reference!
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a : alist) {
			if (a.getArtist().equals(artist)) {
				albums.add(a);
			}
		}
		return albums;
	}
	
	public String toString() {
		String result = "";
		for (Album a : alist) {
			result += a.getTitle() + "\n";
		}
		return result;
	}
	
}
