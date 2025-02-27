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
	public ArrayList<String> getLibSongTitles() {
		ArrayList<String> titles = new ArrayList<String>();
		for(Song s : libSongs.getSongs()) {
			titles.add(s.getTitle());
		}
		return titles;
	}
	
	// return artists of songs in library
	public ArrayList<String> getLibArtists() {
		ArrayList<String> artists = new ArrayList<String>();
		for(Song s : libSongs.getSongs()) {
			artists.add(s.getArtist());
		}
		return artists;
	}
	
	// return list of albums or strings of album names
	public ArrayList<Album> getLibAlbums() {
		return libAlbums.getAlbums();
	}
	
	// return list of playlists
	public ArrayList<SongList> getPlaylists() {
		ArrayList<SongList> cList = new ArrayList<SongList>();
		for (SongList s : playlists) {
			cList.add(s);
		}
		return cList;
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
	
	// add song to playlist - Song object can be changes to two strings - artist/title
	public void addSongToPlaylist(String pName, Song song) {
		for(SongList s : playlists) {
			if (s.getPlaylistName().equals(pName)) s.addSong(song);
		}
	}
	
	public void removeSongFromPlaylist(String pName, Song song) {
		for(SongList s : playlists) {
			if (s.getPlaylistName().equals(pName)) s.removeSong(song);
		}
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
