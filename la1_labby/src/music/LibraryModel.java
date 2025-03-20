


/*
 * LibraryModel.java is a database of the user's songs,
 * albums, and playlists.
 */

package music;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LibraryModel {
	
	// INSTANCE VARIABLES
	private AlbumList libAlbums;
	private SongList libSongs;
	private PlaylistManager playlists;
	private MusicStore mStore;
	
	
	// CONSTRUCTOR
	public LibraryModel() throws FileNotFoundException {
		this.libAlbums = new AlbumList();
		this.libSongs = new SongList();
		this.playlists = new PlaylistManager(this);	
		this.mStore = new MusicStore();
		
	}
	
	// for user file
	public HashMap<Song, Rating> getRatedSongs() {
		return playlists.getRatedSongs();
	}
	
	public ArrayList<SongList> getUserPlaylists() {
		return playlists.getUserPlaylists();
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
		// only print out songs in library
				String albumsString = "";
				ArrayList<Album> albumsList = libAlbums.getAlbumObjectsByTitle(title);	
				for(Album a: albumsList) {
					albumsString += (getSongsInLibAndAlbum(a) + "/n");
				}
				return albumsString;
	}
	
	public String getLibAlbumByArtist(String artist) {
		// only print out songs in library
		String albumsString = "";
		ArrayList<Album> albumsList = libAlbums.getAlbumObjectsByArtist(artist);	
		for(Album a: albumsList) {
			albumsString += (getSongsInLibAndAlbum(a) + "/n");
		}
		return albumsString;
	}
	
	private String getSongsInLibAndAlbum(Album album) {
		ArrayList<Song> songs = album.getSongList();
		String aString = "Album: " + album.getTitle() + "; Artist: " + album.getArtist() 
		+ "; Year: " + album.getYear() + "; Genre: " + album.getGenre() + "; Songs: ";
		//String aString = album.noSongsString();
		// only songs from album that are also in library
		for(Song s : songs) {
			if(libSongs.containsSong(s)) {
				aString += s.toString();
			}
		}
		return aString;
	}
	
	// search for a specific playlist
	public String getPlaylist(String playlistName) {
		return playlists.getPlaylist(playlistName);

	}
	
	// get songs sorted by title
	public String sortedByTitle() {
		ArrayList<Song> songs = libSongs.sortedSongsByTitle();
		if (songs.size() == 0) return "No Songs In Library";
		String result = "";
		for(Song s : songs) {
			result += s.toString() + "\n";
		}
		return result;
	}
	
	// get songs sorted by artist
	public String sortedByArtist() {
		ArrayList<Song> songs = libSongs.sortedSongsByArtist();
		if (songs.size() == 0) return "No Songs In Library";
		String result = "";
		for(Song s : songs) {
			result += s.toString() + "\n";
		}
		return result;
	}
	
	// get songs sorted by rating
	public String sortedbyRating() {
		return playlists.sortedSongsByRating();
	}
	
	
	// ADDING METHODS
	// add song to library
	public void addSongToLib(Song song) {
		libSongs.addSong(song);
		playlists.addToGenres(song);
		// add album, but dont add all songs
		addAlbumNotSongs(mStore.searchStoreAlbumByTitle(song.getAlbumTitle()).getFirst());
	}
	
	// add album to library, add all songs in album to song library
	public void addAlbumToLib(Album album) {
		libAlbums.addAlbum(album);
		ArrayList<Song> aSongs = album.getSongList();
		for(Song s : aSongs) {
			addSongToLib(s);
		}
	}
	
	public void addAlbumNotSongs(Album album) {
		// only add album if not already in list
		if(!libAlbums.albumInList(album)) {
			libAlbums.addAlbum(album);
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
		Song song = libSongs.getSongByTitleAndArtist(artist, title);  // songs in library
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
	
	public String getGenreNames() {
		String result = "All Genres in Library:\n";
		ArrayList<SongList> genrePlaylists = playlists.getGenrePlaylists();
		for (SongList genrePlaylist : genrePlaylists) {
			result += genrePlaylist.getPlaylistName() + "\n";
		}
		return result;
	}
	
	public String getSongsByGenre(String genre) {
		SongList slist = playlists.getSongsByGenre(genre);
		return slist.toString();
	}
	
	public String getGenres() {
		String result = "Genre Playlists:\n";
		ArrayList<SongList> genres = playlists.getGenrePlaylists();
		for(SongList slist : genres) {
			result += slist.getPlaylistName() + "Songs: /n";
			ArrayList<Song> songs = slist.getSongs();
			for(Song s : songs) {
				result += s.toString() + "\n";
			}
		}
		return result;
	}
	
	public String getRecents() {
		String result = "Recently Played Songs:\n";
		ArrayList<Song> recents = playlists.getRecents();
		for(Song s : recents) {
			result += s.toString() + "\n";
		}
		return result;
	}
	
	public String getFrequents() {
		String result = "Most Played Songs:\n";
		ArrayList<Song> frequents = playlists.mostPlayed();
		for (Song s : frequents) {
			result += s.toString() + "\n";
		}
		return result;
	}
	
	public String getAlbumFromSong(Song song) {
		Album album = libAlbums.getAlbumByTitleAndArtist(song.getAlbumTitle(), song.getArtist());
		String result = album.toString();
		result += "\nAlbum is in Library";
		return result;
	}
	
	public ArrayList<Song> getLibrarySongs() {
		return new ArrayList<Song>(this.libSongs.getSongs());
	}
	
	public void shuffleSongs() {
		libSongs.shuffleSongs();
	}
	
	public String shufflePlaylist(String pName) {
		return playlists.shufflePlaylist(pName);
	}
	
	public void removeSong(Song song) {
		String aName = song.getAlbumTitle();
		libSongs.removeSong(song);
		Album album = libAlbums.getAlbumObjectByTitle(aName);
		ArrayList<Song> songs = album.getSongList();
		boolean songsLeft = false;
		for (Song s : songs) {
			for (Song libS : libSongs.getSongs()) {
				if (s.equals(libS)) {
					songsLeft = true;
				}
			}
		}
		// if there are no songs in the album that are also in library
		if (songsLeft == false) {
			libAlbums.removeAlbum(album);
		}
		
	}
	
	public void removeAlbum(String aName) {
		Album album = libAlbums.getAlbumObjectByTitle(aName);
		libAlbums.removeAlbum(album);
	}
	
	
}