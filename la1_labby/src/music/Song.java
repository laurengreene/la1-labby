
package music;

public class Song {
	
	// instance variables
	private String title;
	private String artist;
	private String albumTitle;
	public enum Rating{UNRATED, ONE, TWO, THREE, FOUR, FIVE}
	private Rating rating; 
	
	
	// constructor
	public Song(String title, String artist, String albumTitle) {
		this.title = title;
		this.artist = artist;
		this.albumTitle = albumTitle;
		rating = Rating.UNRATED;
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
	
	public void setRating(int r) {
		if(r > 5 || r < 0) {
			System.out.println("Unable to set rating");
		}
		switch(r) {
		case 5 :
			rating = Rating.FIVE;
		case 4 :
			rating = Rating.FOUR;
		case 3 :
			rating = Rating.THREE;
		case 2 :
			rating = Rating.TWO;
		case 1 :
			rating = Rating.ONE;
		default :
			rating = Rating.UNRATED;
		}
		
	}
	
	public int getRating() {
		switch(rating) {
		case Rating.FIVE :
			return 5;
		case Rating.FOUR :
			return 4;
		case Rating.THREE :
			return 3;
		case Rating.TWO :
			return 2;
		case Rating.ONE :
			return 1;
		default :
			return 0;
		}
	}
	
	public boolean checkFavorite() {
		return(rating == Rating.FIVE);
	}
	
	// return copy of song
	public Song makeCopy() {
		Song cSong = new Song(title, artist, albumTitle);
		cSong.setRating(this.getRating());
		return cSong;
	}
		
	public boolean equals(Song otherSong) {
		if(otherSong.getAlbumTitle().equals(albumTitle) &&
				otherSong.getArtist().equals(artist) &&
				otherSong.getTitle().equals(title)) return true;
		return false;
	}

	
	public String toString() {
		return "Song: " + this.title + "\nArtist: " + this.artist +
				"\nAlbum: " + this.albumTitle;
	}
	
	
}