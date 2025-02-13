package music;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MusicStore {
	
	public static void readAlbumsFile(AlbumList albumList) throws FileNotFoundException {
		Scanner s = new Scanner(new File("albums/albums.txt"));
		while (s.hasNextLine()) {
			// goes into each album (one album per line)
			String albumInfo = s.nextLine().replace(",", "_") + ".txt";
			// access that file
			// add to album list (which makes album object)
			// when we get song, add song to album list, make song object, and to song list
			
		}
		s.close();
	}
	
	public static void storesAlbumNames() {
		// keeps track of all album names
		// list of album objects
	}
	
	public static void storesAlbumSongs() {
		// creates an object for the album
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		AlbumList albumList = new AlbumList();
		SongList songList = new SongList();
		SongList favSongList = new SongList();
		readAlbumsFile(albumList);
	}
	
	
	
	

}
