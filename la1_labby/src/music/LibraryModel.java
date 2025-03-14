
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
	private PlaylistManager playlists;
	
	
	// CONSTRUCTOR
	public LibraryModel() {
		this.libAlbums = new AlbumList();
		this.libSongs = new SongList();
		this.playlists = new PlaylistManager(this);	
		
	}
	
	// for user file
	public HashMap<Song, Rating> getRatedSongs() {
		return playlists.getRatedSongs();
	}
	
	public HashMap<String, ArrayList<Song>> getPlaylistsForFile() {
		return playlists.getPlaylistsForFile();
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
		return libSongs.getSongObjectByArtist(artist);
	}
	
	public String getLibAlbumByTitle(String title) {
		return libAlbums.getAlbumByTitle(title);
	}
	
	public String getLibAlbumByArtist(String artist) {
		return libAlbums.getAlbumByArtist(artist);
	}
	
	// search for a specific playlist
	public String getPlaylist(String playlistName) {
		return playlists.getPlaylist(playlistName);

	}
	
	// ADDING METHODS
	// add song to library
	public void addSongToLib(Song song) {
		libSongs.addSong(song);
		playlists.addToGenres(song);
	}
	
	// add album to library, add all songs in album to song library
	public void addAlbumToLib(Album album) {
		libAlbums.addAlbum(album);
		ArrayList<Song> aSongs = album.getSongList();
		for(Song s : aSongs) {
			addSongToLib(s);
		}
	}
	
	// RETURNING METHODS
	// return titles of songs in library
	public String getLibSongTitles() {
		if (libSongs.getSongs().isEmpty()) return "No songs in library\n";
		String titles = "";
		for(Song s : libSongs.getSongs()) {
			titles += s.getTitle() + "\n";
		}
		return titles;
	}
	
	// return artists of songs in library
	public String getLibArtists() {
		if (libSongs.getSongs().isEmpty()) return "No artists in library\n";
		String artists = " ";
		for(Song s : libSongs.getSongs()) {
			if (!artists.toLowerCase().contains(s.getArtist().toLowerCase())) {
				artists += s.getArtist() + "\n";
			}
		}
		return artists.substring(0, artists.length()-1);
	}
	

	// return string of album names
	public String getLibAlbums() {
		String result = libAlbums.toString();
		if (result.length() == 0) return "No albums in library\n";
		return result;
	}
	
	// return string of playlists
	public String getPlaylists() {
		return playlists.getPlaylists();
	}
	
	// create playlist given name
	public void createPlaylist(String name) {
		this.playlists.createPlaylist(name);
	}
	
	// add song to playlist - Song object can be changes to two strings - artist/title
	public String addSongToPlaylist(String pName, String artist, String title) {
		Song song = libSongs.getSongByTitleAndArtist(artist, title);
		return playlists.addSongToPlaylist(pName, song);
	}
	
	public String removeSongFromPlaylist(String pName, String artist, String title) {
		Song song = libSongs.getSongByTitleAndArtist(artist, title);
		return playlists.removeSongFromPlaylist(pName, song);
	}
	
	
	public void setRatingOfSong(Song songName, int rate) {
		this.playlists.setRatingOfSong(songName, rate);
	}
	

	// returns a string of the favorite songs
	public String getFavorites() {
			String result = "";
			SongList favs = playlists.getFavoritePlaylist();
			for (Song s : favs.getSongs()) {
				result += s.toString() + "\n";
			}
		return result;
	}
	
	public String getTopRatedSongs() {
		String result = "";
		SongList topSongs = playlists.getTopRatedSongs();
		for (Song s : topSongs.getSongs()) {
			result += s.toString() + "\n";
		}
	return result;
	}
	
	public ArrayList<Song> getLibrarySongs() {
		return new ArrayList<Song>(this.libSongs.getSongs());
	}
}
