

/*
 * AlbumList.java is an ArrayList of Albums.
 */

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
//		alist.add(newAlbum.makeCopyAlbum()); in case we do need copy
		alist.add(newAlbum);
	}
	
	public Album getAlbumByTitleAndArtist(String title, String artist) {
		for (Album a : alist) {
			if (a.getArtist().toLowerCase().equals(artist.toLowerCase()) &&
					a.getTitle().toLowerCase().equals(title.toLowerCase())) {
				return a;
			}
		}
		return null;
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
	}	
	
	public Album getAlbumObjectByTitle(String title) {
		for (Album a : alist) {
			if (a.getTitle().toLowerCase().equals(title.toLowerCase())) {
				return a;
			}
		}
		return null;
	}
	
	public ArrayList<Album>	getAlbumObjectsByTitle(String title) {
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a : alist) {
			if (a.getTitle().toLowerCase().equals(title.toLowerCase())) {
				albums.add(a);
			}
		}
		return albums;
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
	}
	
	public ArrayList<Album> getAlbumObjectsByArtist(String artist) {
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a : alist) {
			if (a.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				albums.add(a);
			}
		}
		return albums;
	}
	
	public boolean albumInList(Album album) {
		return alist.contains(album);
	}
	
	public void removeAlbum(Album album) {
		alist.remove(album);
	}
	
	public String toString() {
		String result = "";
		for (Album a : alist) {
			result += a.getTitle() + "\n";
		}
		return result;
	}
	
}