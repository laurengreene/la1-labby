package music;

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
	
	public SongList getSongList() {  // fix escaping reference!
		return songlist;
	}
	
	public String toString() {
		String result = "Album: " + this.title + "\nArtist: " + this.artist + "\nYear: " + year + "Genre: " + genre + "\nSongs:";
		for (Song s : songlist.getSongs()) {
			result += "\n" + s.getTitle();
		}
		return result;
	}
	
}
