package music;

import java.util.ArrayList;

public class Album {
	
	// instance variables
	private String artist;
	private String title;
	private SongList songlist;  // store songs in album object as a songlist
	private String genre;
	private String year;
	
	// constructor
	public Album(String artist, String title, String genre, String year) {
		this.artist = artist;
		this.title = title;
		this.songlist = new SongList();
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
	
	public void addToSongList(Song song) {
		songlist.addSong(song);
	}
	
	public ArrayList<Song> getSongList() {  // do we need to return songList, or is returning arraylist okay
		return songlist.makeCopyList();
	}
	
	public Album makeCopyAlbum() {
		Album cAlbum = new Album(artist, title, genre, year);
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
		String result = "Album: " + this.title + "\nArtist: " + this.artist + "\nYear: " + year + "\nGenre: " + genre + "\nSongs: ";
		for (Song s : songlist.getSongs()) {
			result += "\n" + s.getTitle();
		}
		return result;
	}
	
}
