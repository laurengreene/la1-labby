package music;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaylistList {
	
	// INSTANCE VARIABLES
//	private ArrayList<SongList> plist;
	private ArrayList<SongList> userPlists;
	private HashMap<String, Integer> genres;
	private LibraryModel libMod;
	private HashMap<Song, Rating> ratedSongs;
	
	
	// CONSTRUCTOR
	public PlaylistList(LibraryModel libMod) {
		this.userPlists = new ArrayList<SongList>();
		this.genres = new HashMap<String, Integer>();
		this.ratedSongs = new HashMap<Song, Rating>();
		this.libMod = libMod;
	}
	
	// METHODS
	public void addToGenres(Song song) {
		genres.putIfAbsent(song.getGenre(), 0);  // add genre to hashmap if absent
		genres.put(song.getGenre(), genres.get(song.getGenre()) + 1);  // one more song in this genre
	}
	
	public String getPlaylists() {
		String plists = "";
		//int count = 0;
		
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
		return plists;
	}
	
	private ArrayList<SongList> getGenrePlaylists() {
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
	
	private SongList getSongsByGenre(String genre) {
		SongList songlist = new SongList();
		for (Song s : libMod.getLibrarySongs()) {
			if (s.getGenre().toLowerCase().equals(genre.toLowerCase())) {
				songlist.addSong(s);
			}
		}
	return songlist;
}
	
	public void addToPlaylist(String playlistName, Song song) {
		// find the playlist
		for (SongList s : userPlists) {
			if (s.getPlaylistName().equals(playlistName)) s.addSong(song);
		}
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
			if (s.getPlaylistName().toLowerCase().equals(pName.toLowerCase())) {
				// find the song in the library and add it
				try {
					s.addSong(song);
					return "Song added";
				} catch(NullPointerException e) {
					return "Song not found";
				}
					
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
		return "Not found";
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

}
