package music;

public class Song {

	// instance variables to add (if needed):
	// rating
	// don't need boolean for favorite. can determine in method based on rating
	
	// methods to add:
	// set rating  ( when hits 5, adds to favorite song playlist)
	// get rating
	// isFavorite
	
	// instance variables
	private String title;
	private String artist;
	private String albumTitle;
	
	// constructor
	public Song(String title, String artist, String albumTitle) {
		this.title = title;
		this.artist = artist;
		this.albumTitle = albumTitle;
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
	
	public String toString() {
		return "Song: " + this.title + "\nArtist: " + this.artist +
				"\nAlbum: " + this.albumTitle;
	}

}
