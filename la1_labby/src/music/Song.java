

package music;

public class Song {
	
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
		
	public boolean equals(Song otherSong) {
		if(otherSong.getAlbumTitle().equals(albumTitle) &&
				otherSong.getArtist().equals(artist) &&
				otherSong.getTitle().equals(title)) return true;
		return false;
	}

	
	public String toString() {
		return "Song: " + this.title + "; Artist: " + this.artist +
				"; Album: " + this.albumTitle;
	}
	
	
}