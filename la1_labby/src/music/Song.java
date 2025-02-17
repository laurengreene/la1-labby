package music;

public class Song {

	// instance variables to add (if needed):
	// artist
	// rating
	// album
	// don't need boolean for favorite. can determine in method based on rating
	
	// methods to add:
	// set rating  ( when hits 5, adds to favorite song playlist)
	// get rating
	// get artist
	// get album name
	// isFavorite
	
	// instance variables
	private String title;
	
	// constructor
	public Song(String title) {
		this.title = title;
	}
	
	// methods
	public String getTitle() {
		return title;
	}

}
