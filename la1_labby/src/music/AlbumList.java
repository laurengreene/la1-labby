package music;

import java.util.ArrayList;

public class AlbumList {
	
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
	
	public ArrayList<Album> getAlbumByTitle(String title) { //escaping reference should be fixed
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a : alist) {
			if (a.getTitle().equals(title)) {
				albums.add(a.makeCopyAlbum());
			}
		}
		return albums;
	}
	
	public ArrayList<Album> getAlbumByArtist(String artist) { // escaping reference should be fixed
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a : alist) {
			if (a.getArtist().equals(artist)) {
				albums.add(a.makeCopyAlbum());
			}
		}
		return albums;
	}
	
	public ArrayList<Album> getAlbums() {
		return makeCopyList();
	}
	
	public ArrayList<Album> makeCopyList() {
		ArrayList<Album> cList = new ArrayList<Album>();
		for (Album s : alist) {
			cList.add(s.makeCopyAlbum());
		}
		return cList;
	}
	
	
	public String toString() {
		String result = "";
		for (Album a : alist) {
			result += a.getTitle() + "\n";
		}
		return result;
	}
	
}
