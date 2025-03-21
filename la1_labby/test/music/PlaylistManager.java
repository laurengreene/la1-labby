

package music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
public class PlaylistManager {
	
	// INSTANCE VARIABLES
//	private ArrayList<SongList> plist;
	private ArrayList<SongList> userPlists;
	private HashMap<String, Integer> genres;
	private LibraryModel libMod;
	private HashMap<Song, Rating> ratedSongs;
	private ArrayList<Song> recents;
	private HashMap<Song, Integer> timesPlayed;
	
	
	// CONSTRUCTOR
	public PlaylistManager(LibraryModel libMod) {
		this.userPlists = new ArrayList<SongList>();
		this.genres = new HashMap<String, Integer>();
		this.ratedSongs = new HashMap<Song, Rating>();
		this.libMod = libMod;
		this.recents = new ArrayList<Song>();
		this.timesPlayed = new HashMap<Song, Integer>();
		
	}
	
	// METHODS
	public HashMap<Song, Rating> getRatedSongs() {
		return new HashMap<Song, Rating>(ratedSongs);
	}
	
	public ArrayList<SongList> getUserPlaylists() {
	    ArrayList<SongList> ulist = new ArrayList<>();
	    for (SongList slist : userPlists) {
	        SongList copyslist = new SongList();
	        copyslist.setPlaylistName(slist.getPlaylistName()); 
	        for (Song s : slist.getSongs()) {
	            copyslist.addSong(s); 
	        }
	        ulist.add(copyslist);
	    }
	    return ulist;
	}
	
	public void addToGenres(Song song) {
		genres.putIfAbsent(song.getGenre(), 0);  // add genre to hashmap if absent
		genres.put(song.getGenre(), genres.get(song.getGenre()) + 1);  // one more song in this genre
	}
	
	public String getPlaylists() {
		String plists = "";
		// add favorite playlist
		plists += getFavoritePlaylist().getPlaylistName() + "\n";
		
		// add top rated playlists
		plists += getTopRatedSongs().getPlaylistName() + "\n";
		
		// add user playlists
		for (int i =0 ; i < userPlists.size(); i++) {
			plists += userPlists.get(i).getPlaylistName() + "\n";
		}
		
		// add genre playlists
		ArrayList<SongList> genrePlaylists = getGenrePlaylists();
		for (SongList genrePlaylist : genrePlaylists) {
			plists += genrePlaylist.getPlaylistName() + "\n";
		}
		
		//recents and frequents will always have the same names
		plists += "Recents\nMost Played\n";
		
		return plists;
	}
	
	public ArrayList<SongList> getGenrePlaylists() {
		// find all the genre playlists
		ArrayList<SongList> newPlaylistList = new ArrayList<SongList>();
		for (HashMap.Entry<String, Integer> entry : this.genres.entrySet()) {
			// check for 10 or more songs in a genre
			if (entry.getValue() >= 10) {
				SongList newSlist = getSongsByGenre(entry.getKey());  // this is a genre playlist
				newSlist.setPlaylistName(entry.getKey());
				newPlaylistList.add(newSlist);
			}
		}
		return newPlaylistList;
	}
	
	public String getAllGenres() {
		String result = "";
		Set<String> keys = genres.keySet();
		for (String k : keys) {
			result += k + "\n";
		}
		return result;
	}
	
	public SongList getSongsByGenre(String genre) {
		SongList songlist = new SongList();
		for (Song s : libMod.getLibrarySongs()) {
			if (s.getGenre().toLowerCase().equals(genre.toLowerCase())) {
				songlist.addSong(s);
			}
		}
	return songlist;
}
	
	public void createPlaylist(String name) {
		SongList playList = new SongList();
		playList.setPlaylistName(name);
		userPlists.add(playList);
	}
	

	public String removeSongFromPlaylist(String pName, Song song) {
		for(SongList s : userPlists) {
			if (s.getPlaylistName().equals(pName)) {
				return(s.removeSong(song));
			}
		}
		return "Playlist not found";
	}
	
	public String addSongToPlaylist(String pName, Song song) {
		for(SongList s : userPlists) {
			if (s == null) {
				return "Not found";
			}
			if (s.getPlaylistName().equalsIgnoreCase(pName)) {
					s.addSong(song);
					return "Song added";		
			}
		}
		return "Playlist not found";
	}
	
	public String getPlaylist(String playlistName) {
		if (playlistName.equalsIgnoreCase(getFavoritePlaylist().getPlaylistName())) {
			return getFavoritePlaylist().toString();
		}
		
		if (playlistName.equalsIgnoreCase(getTopRatedSongs().getPlaylistName())) {
			return getTopRatedSongs().toString();
		}
		
		for (HashMap.Entry<String, Integer> entry : this.genres.entrySet()) {
			if (playlistName.equalsIgnoreCase(entry.getKey())) {
				return getSongsByGenre(entry.getKey()).toString();
			}
		}
		
		for (SongList slist : userPlists) {
			if (slist.getPlaylistName().equals(playlistName)) {
				return slist.toString();
			}
		}
		return "Not Found";
	}
	
	public void setRatingOfSong(Song song, int rate) {
		Rating rating = Rating.convert(rate);
		ratedSongs.put(song, rating);
	}
	
	public SongList getFavoritePlaylist() {
		SongList favPlaylist = new SongList();
		for (HashMap.Entry<Song, Rating> entry : ratedSongs.entrySet()) {
			if (entry.getValue() == Rating.FIVE) {
				favPlaylist.addSong(entry.getKey());
			}
		}
		favPlaylist.setPlaylistName("Favorite Songs");
		return favPlaylist;
	}
	
	public SongList getTopRatedSongs() {
		SongList topRatedPlaylist = new SongList();
		for (HashMap.Entry<Song, Rating> entry : ratedSongs.entrySet()) {
			if (entry.getValue() == Rating.FOUR || entry.getValue() == Rating.FIVE) {
				topRatedPlaylist.addSong(entry.getKey());
			}
		}
		topRatedPlaylist.setPlaylistName("Top Rated Songs");
		return topRatedPlaylist;
	}
	
	public void playSong(Song song) {
		recents.add(song);
		// only store past 10 songs played
		if (recents.size() > 10) recents.removeFirst();
		
		// update times played
		Integer played = 1;
		if (timesPlayed.containsKey(song)) {
			played = timesPlayed.get(song) + 1;
			}
		timesPlayed.put(song, played);
	}
	
	public ArrayList<Song> getRecents() {
		ArrayList<Song> newRecents = new ArrayList<>(recents);
		return newRecents;
	}
	
	public ArrayList<Song> mostPlayed() {
	    ArrayList<Song> mostSongs = new ArrayList<>();
	    ArrayList<HashMap.Entry<Song, Integer>> sorted = new ArrayList<>(timesPlayed.entrySet());
	    sorted.sort((a, b) -> b.getValue().compareTo(a.getValue())); 
	    for (int i = 0; i < Math.min(10, sorted.size()); i++) {
	        mostSongs.add(sorted.get(i).getKey());
	    }
	    return mostSongs;
	}

	
	public String sortedSongsByRating() {
		String result = "";
		String eachRating = "";
		for (HashMap.Entry<Song, Rating> entry : ratedSongs.entrySet()) {
			if (entry.getValue() == Rating.ONE) {
				eachRating += entry.getKey().toString() + "\n";
			}
		}
		if (eachRating.length() == 0) {result += "No Songs Rated 1\n";}
		else {result += ("Songs Rated 1: \n" + eachRating);}
		
		eachRating = "";
		for (HashMap.Entry<Song, Rating> entry : ratedSongs.entrySet()) {
			if (entry.getValue() == Rating.TWO) {
				eachRating += entry.getKey().toString() + "\n";
			}
		}
		if (eachRating.length() == 0) {result += "No Songs Rated 2\n";}
		else {result += ("Songs Rated 2: \n" + eachRating);}
		
		eachRating = "";
		for (HashMap.Entry<Song, Rating> entry : ratedSongs.entrySet()) {
			if (entry.getValue() == Rating.THREE) {
				eachRating += entry.getKey().toString() + "\n";
			}
		}
		if (eachRating.length() == 0) {result += "No Songs Rated 3\n";}
		else {result += ("Songs Rated 3: \n" + eachRating);}
		
		eachRating = "";
		for (HashMap.Entry<Song, Rating> entry : ratedSongs.entrySet()) {
			if (entry.getValue() == Rating.FOUR) {
				eachRating += entry.getKey().toString() + "\n";
			}
		}
		if (eachRating.length() == 0) {result += "No Songs Rated 4\n";}
		else {result += ("Songs Rated 4: \n" + eachRating);}
		
		eachRating = "";
		for (HashMap.Entry<Song, Rating> entry : ratedSongs.entrySet()) {
			if (entry.getValue() == Rating.FIVE) {
				eachRating += entry.getKey().toString() + "\n";
			}
		}
		if (eachRating.length() == 0) {result += "No Songs Rated 5\n";}
		else {result += ("Songs Rated 5: \n" + eachRating);}
		
		return result;
	}
	
	public String shufflePlaylist(String pName) {
		for(SongList slist: userPlists) {
			if(slist.getPlaylistName().equals(pName)) {
				slist.shuffleSongs();
				return "Playlist Shuffled";
			}
		}
		// if playlist not found, return empty list
		return "Playlist Not Found";
	}
	

}