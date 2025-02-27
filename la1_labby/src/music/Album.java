

package music;

import java.util.ArrayList;

public class Album {
	
	/*
	 * @Pre artist != null && title != null && genre != null && year != null
	 */
	
	// instance variables
	private String artist;
	private String title;
	private SongList songlist;
	private String genre;
	private String year;
	
	// constructor
	public Album(String artist, String title, String genre,
			String year, ArrayList<Song> newSongs) {
		this.artist = artist;
		this.title = title;
		this.songlist = new SongList();
		for (Song s : newSongs) {
			this.songlist.addSong(s);
		}
		this.genre = genre;
		this.year = year;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public ArrayList<Song> getSongList() {
		return songlist.getSongs();
	}
	
	public Album makeCopyAlbum() {  // shallow copy
		ArrayList<Song> cSonglist = songlist.getSongs();
		return new Album(artist, title, genre, year, cSonglist);
	}
	
	public boolean equals(Album otherAlbum) {
		if (otherAlbum.getArtist().equals(artist) && otherAlbum.getTitle()
				.equals(title) && otherAlbum.getGenre().equals(genre) && 
				otherAlbum.getYear().equals(year)) return true;
		return false;
	}
	
	public String toString() {
		String result = "Album: " + this.title + "; Artist: " + this.artist + "; Year: " + year + "; Genre: " + genre + "; Songs: ";
		for (Song s : songlist.getSongs()) {
			result += "\n" + s.getTitle();
		}
		return result;
	}
	
}
