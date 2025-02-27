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
		alist.add(newAlbum.makeCopyAlbum());
	}
	
	public String getAlbumByTitle(String title) {
		String result = "";
		for (Album a : alist) {
			if (a.getTitle().toLowerCase().equals(title.toLowerCase())) {
				result += a.toString() + "\n";
			}
		}
		if (result.length() == 0) return "Not found";
		return result.substring(0, result.length()-1);
		
//		ArrayList<Album> albums = new ArrayList<Album>();
//		for (Album a : alist) {
//			if (a.getTitle().equals(title)) {
//				albums.add(a.makeCopyAlbum());
//			}
//		}
//		return albums;
	}
	
	public String getAlbumByArtist(String artist) {
		String result = "";
		for (Album a : alist) {
			if (a.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				result += a.toString() + "\n\n";
			}
		}
		if (result.length() == 0) return "Not found";
		return result.substring(0, result.length()-2);
		
//		ArrayList<Album> albums = new ArrayList<Album>();
//		for (Album a : alist) {
//			if (a.getArtist().equals(artist)) {
//				albums.add(a.makeCopyAlbum());
//			}
//		}
//		return albums;
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
