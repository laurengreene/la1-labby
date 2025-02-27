/*
 * Album.java is an album object that holds song objects and
 * is in album lists.
 */

package music;

import java.util.ArrayList;

public class Album {
	
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
			this.songlist.addNewSong(s);
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
	
	// maybe get rid of this method because no songs need to be added to it.
	// need to figure out how to make a deep copy of the list. maybe
	// make string immutable?
	public void addToSongList(Song song) {
		songlist.addSong(song);
	}
	
	public ArrayList<Song> getSongList() {
		return songlist.makeCopyList();
	}
	
	public Album makeCopyAlbum() {  // need to make deep copy of songlist and replace the songs
		Album cAlbum = new Album(artist, title, genre, year, new ArrayList<Song>());
		ArrayList<Song> cSonglist = songlist.getSongs();
		for (Song s : cSonglist) {
			cAlbum.addToSongList(s);
		}
		return cAlbum;
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