package music;

import java.util.ArrayList;

public class LibraryModel {
	
	// stores all the objects / lists
	private AlbumList libAlbums = new AlbumList();
	private SongList libSongs = new SongList();
	private SongList favorites = new SongList();
	private ArrayList<SongList> playlists = new ArrayList<SongList>();
	
	// search Library
	// need to print message if nothing found
	public ArrayList<Song> getLibSongByTitle(String title) {
		return libSongs.getSongByTitle(title);
	}
	
	public ArrayList<Song> getLibSongByArtist(String artist) {
		return libSongs.getSongByArtist(artist);
	}
	
	public ArrayList<Album> getLibAlbumByTitle(String title) {
		return libAlbums.getAlbumByTitle(title);
	}
	
	public ArrayList<Album> getLibAlbumByArtist(String artist) {
		return libAlbums.getAlbumByArtist(artist);
	}
	
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
	
	// return list of favorites
	public ArrayList<Song> getFavorites() {
		return favorites.getSongs();
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
	
	public void setSongToFavorite(Song s) {
		favorites.addSong(s.makeCopy());
		// set song rating
	}

}
