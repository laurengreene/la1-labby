/*
 * LibraryModel.java is a database of the user's songs,
 * albums, and playlists.
 */

package music;

import java.util.ArrayList;

public class LibraryModel {
	
	// INSTANCE VARIABLES
	private AlbumList libAlbums;
	private SongList libSongs;
	private ArrayList<SongList> playlists;
	
	// CONSTRUCTOR
	public LibraryModel() {
		this.libAlbums = new AlbumList();
		this.libSongs = new SongList();
		this.playlists = new ArrayList<SongList>();	
	}
	
	// METHODS
	public String getLibSongByTitle(String title) {
		return libSongs.getSongByTitle(title);
	}
	
	public String getLibSongByArtist(String artist) {
		return libSongs.getSongByArtist(artist);
	}
	
	public String getLibAlbumByTitle(String title) {
		return libAlbums.getAlbumByTitle(title);
	}
	
	public String getLibAlbumByArtist(String artist) {
		return libAlbums.getAlbumByArtist(artist);
	}
	
	// add song to library
	public void addSongToLib(Song song) {
		libSongs.addSong(song.makeCopy());
	}
	
	// add album to library, add all songs in album to song library
	public void addAlbumToLib(Album album) {
		libAlbums.addAlbum(album);
		ArrayList<Song> aSongs = album.getSongList();
		for(Song s : aSongs) {
			libSongs.addSong(s);
		}
	}
	
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
	
	// search for a specific playlist
	public String getPlaylist(String playlistName) {
		for (SongList slist : this.playlists) {
			if (slist.getPlaylistName().equals(playlistName)) {
				return slist.toString();
			}
		}
		return "Not found";
	}
	
	// return list of favorites
	public String getFavorites() {
		String result = "";

		for (Song s : this.libSongs.getSongs()) {
			if (s.getRating()==5) result += s.toString();
		}
		
		return result;
	}
	
	// create playlist given name
	public void createPlaylist(String name) {
		SongList playList = new SongList();
		playList.setPlaylistName(name);
		playlists.add(playList);
	}
	
	// add song to playlist
	public String addSongToPlaylist(String pName, String artist, String title) {
		ArrayList<Song> songs = libSongs.getSongObjectsByTitle(title);
		Song toAdd = null;
		for(Song s : songs) {
			if (s.getArtist().equals(artist)) {
				toAdd = s;
				break;
			}
		}
		if (toAdd != null) {
			for(SongList p : playlists) {
				if(p.getPlaylistName().equals(pName)) {
					p.addSong(toAdd);
					return("Song added to playlist");
				}
			}
		}
		return("Song not found");
	}
	
	public String removeSongFromPlaylist(String pName, String artist, String title) {
		for(SongList p : playlists) {
			if(p.getPlaylistName().equals(pName)) {
				return(p.removeSong(title, artist));
			}
		}
		return("Playlist not found");
	}
	
	public void setSongToFavorite(Song songName) {
		// find the song
		// set rating to five
		for (Song s : this.libSongs.getSongs()) {
			if (s.getTitle().equals(songName)) {
				s.setRating(5);
			}
		}
	}
}