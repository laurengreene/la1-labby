

/*
 * Album.java stores a SongList of songs. It is stored in
 * 
 */

package music;

import java.util.ArrayList;

public final class Album {
	
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
	
	public boolean songInAlbum(Song song) {
		if (songlist.containsSong(song)) return true;
		return false;
	}
	
	@Override
	public boolean equals(Object otherAlbum) {
		if (otherAlbum == null) return false;
		if (otherAlbum.getClass() != this.getClass()) return false;
		if (((Album)otherAlbum).getArtist().equals(artist) && ((Album)otherAlbum).getTitle()
				.equals(title) && ((Album)otherAlbum).getGenre().equals(genre) && 
				((Album)otherAlbum).getYear().equals(year)) return true;
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
