
/*
 * LibraryModel.java is a database of the user's songs,
 * albums, and playlists.
 */

package music;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryModel {
	
	// INSTANCE VARIABLES
	private AlbumList libAlbums;
	private SongList libSongs;
	private ArrayList<SongList> playlists;
	private HashMap<Song, Rating> ratedSongs;
	
	
	// CONSTRUCTOR
	public LibraryModel() {
		this.libAlbums = new AlbumList();
		this.libSongs = new SongList();
		this.playlists = new ArrayList<SongList>();	
		this.ratedSongs = new HashMap<Song, Rating>();
	}
	
	// SEARCH METHODS
	public String getLibSongByTitle(String title) {
		return libSongs.getSongByTitle(title);
	}
	
	public ArrayList<Song> getLibSongbyTitleSong(String title) {
		return libSongs.getSongObjectByTitle(title);
	}
	
	public String getLibSongByArtist(String artist) {
		return libSongs.getSongByArtist(artist);
	}
	
	public ArrayList<Song> getLibSongbyArtistSong(String artist) {
		return libSongs.getSongObjectByTitle(artist);
	}
	
	public String getLibAlbumByTitle(String title) {
		return libAlbums.getAlbumByTitle(title);
	}
	
	public String getLibAlbumByArtist(String artist) {
		return libAlbums.getAlbumByArtist(artist);
	}
	
	// search for a specific playlist
	public String getPlaylist(String playlistName) {
		for (SongList slist : this.playlists) {
			if (slist.getPlaylistName().equals(playlistName)) {
				return slist.toString();
			}
		}
		return "Not found";
	}
	
	// ADDING METHODS
	// add song to library
	public void addSongToLib(Song song) {
		libSongs.addSong(song);
	}
	
	// add album to library, add all songs in album to song library
	public void addAlbumToLib(Album album) {
		libAlbums.addAlbum(album);
		ArrayList<Song> aSongs = album.getSongList();
		for(Song s : aSongs) {
			libSongs.addSong(s);
		}
	}
	
	// RETURNING METHODS
	// return titles of songs in library
	public String getLibSongTitles() {
		String titles = "";
		for(Song s : libSongs.getSongs()) {
			titles += s.getTitle() + "\n";
		}
		return titles;
	}
	
	// return artists of songs in library
	public String getLibArtists() {
		String artists = "";
		for(Song s : libSongs.getSongs()) {
			if (artists.contains(s.getArtist())) {
				artists += s.getArtist() + "\n";
			}
		}
		return artists;
	}
	

	// return string of album names
	public String getLibAlbums() {
		return libAlbums.getAlbumsString();
	}
	
	// return string of playlists
	public String getPlaylists() {
		String plists = "";
		for (SongList s : playlists) {
			plists += s.getPlaylistName() + "\n";
		}
		return plists;
	}
	
	// create playlist given name
	public void createPlaylist(String name) {
		SongList playList = new SongList();
		playList.setPlaylistName(name);
		playlists.add(playList);
	}
	
	// add song to playlist - Song object can be changes to two strings - artist/title
	public String addSongToPlaylist(String pName, String artist, String title) {
		for(SongList s : playlists) {
			if (s.getPlaylistName().equals(pName)) {
				// find the song in the library and add it
				try {
					libSongs.addSong(s.getSongByTitleAndArtist(title, artist));
					return "Song added";
				} catch(NullPointerException e) {
					return "Song not found";
				}
					
			}
		}
		return "Playlist not found";
	}
	
	public String removeSongFromPlaylist(String pName, String artist, String title) {
		for(SongList s : playlists) {
			if (s.getPlaylistName().equals(pName)) {
				try {
					s.addSong(s.getSongByTitleAndArtist(title, artist));
					return "Song removed";
				} catch(NullPointerException e) {
					return "Song not found";
				}
			}
		}
		return "Playlist not found";
	}
	
	// RATING/FAVORITE METHODS
	public void setSongToFavorite(Song songName) {
		for (Song s : this.libSongs.getSongs()) {
			if (s.equals(songName)) {
				this.ratedSongs.put(s, Rating.FIVE);
				return;
			}
		}
	}
	
	public void setRatingOfSong(Song songName, Rating rating) {
		for (Song s : this.libSongs.getSongs()) {
			if (s.equals(songName)) {
				this.ratedSongs.put(s, rating);
			}
		}
	}
	

	// returns a string of the favorite songs
	public String getFavorites() {
		String result = "";
		for (HashMap.Entry<Song, Rating> entry : this.ratedSongs.entrySet()) {
			if (entry.getValue() == Rating.FIVE) {
				result += entry.getKey().toString() + "\n";
			}
		}
		return result;
	}
}
