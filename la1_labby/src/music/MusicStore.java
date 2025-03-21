

/*
 * MusicStore.java is a database of songs and albums.
 */



package music;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MusicStore {
	
	// INSTANCE VARIABLES
	private AlbumList storeAlbums;
	private SongList storeSongs;
	
	public MusicStore() throws FileNotFoundException {
		this.storeAlbums = new AlbumList();
		this.storeSongs = new SongList();
		
		ArrayList<String> albumFilenames = readAlbumsFile();  // read in all the albums text files
		for (String s : albumFilenames) {  
			// goes through each album file and adds music to the store
			readOneAlbumFile(s);
		}
	}
	
	// HELPER METHODS
	// returns an ArrayList of the album text files that need to be read in
	private ArrayList<String> readAlbumsFile() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("albums/albums.txt"));
		ArrayList<String> albumFilenames = new ArrayList<String>();
		
		while (scanner.hasNextLine()) {
			String filename = scanner.nextLine().replace(",", "_") + ".txt";
			albumFilenames.add(filename);
		}
		
		scanner.close();
		return albumFilenames;
	}
	
	
	// creates album and song objects for an album and adds to necessary lists
	private void readOneAlbumFile(String filename) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("albums/" + filename));
		String[] albumInfo = scanner.nextLine().split(",");	
		String artist = albumInfo[1]; String albumTitle = albumInfo[0];
		String genre = albumInfo[2]; String year = albumInfo[3];
		
		ArrayList<Song> albumSongList = new ArrayList<Song>();
		while(scanner.hasNextLine()) {
			String songTitle = scanner.nextLine();
			Song song = new Song(songTitle, artist, albumTitle, genre, year);
			albumSongList.add(song);
		}
		scanner.close();
		addMusicToStore(artist, albumTitle, genre, year, albumSongList);
	}
	
	private void addMusicToStore(String artist, String albumTitle, String genre, String year,
			ArrayList<Song> songlist) {
		
		Album album = new Album(artist, albumTitle, genre, year, songlist);
		storeAlbums.addAlbum(album);
		for (Song s : songlist) {
			storeSongs.addSong(s);
		}
	}
	
	// METHODS
	
	public Song getStoreSongByTandA(String title, String artist){
		return storeSongs.getSongByTitleAndArtist(artist, title);
	}
	
	// search for song by title
	public ArrayList<Song> searchStoreSongByTitle(String title) {
		return storeSongs.getSongObjectByTitle(title);
	}
	// search for song by artist
	public ArrayList<Song> searchStoreSongByArtist(String artist) {
		return storeSongs.getSongObjectByArtist(artist);
	}
	
	// search for album by title
	public ArrayList<Album> searchStoreAlbumByTitle(String title) {
		return storeAlbums.getAlbumObjectsByTitle(title);
	}
	// search for album by artist
	public ArrayList<Album> searchStoreAlbumByArtist(String artist) {
		return storeAlbums.getAlbumObjectsByArtist(artist);
	}
	
}