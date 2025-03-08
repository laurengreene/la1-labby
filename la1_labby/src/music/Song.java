/*
 * Song.java is an immutable class that has attributes
 * like title artist.
 */


package music;

public class Song {
	
	// instance variables
	private String title;
	private String artist;
	private String albumTitle;
	private String genre;
	
	// constructor
	public Song(String title, String artist, String albumTitle, String genre) {
		this.title = title;
		this.artist = artist;
		this.albumTitle = albumTitle;
		this.genre = genre;
	}
	
	// methods
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbumTitle() {
		return albumTitle;
	}
	
	public String getGenre() {
		return genre;
	}
	
	@Override
	public int hashCode() {
		return title.hashCode() + artist.hashCode() + 
				albumTitle.hashCode() + genre.hashCode();
	}
	
	@Override
	public boolean equals(Object otherSong) {
		if (otherSong == null) return false;
		if (otherSong.getClass() != this.getClass()) return false;
		if(((Song)otherSong).getAlbumTitle().equals(albumTitle) &&
				((Song)otherSong).getArtist().equals(artist) &&
				((Song)otherSong).getTitle().equals(title) &&
				((Song)otherSong).getGenre().equals(genre)) return true;
		return false;
	}

	
	public String toString() {
		return "Song: " + this.title + "; Artist: " + this.artist +
				"; Album: " + this.albumTitle;
	}
	
	
}