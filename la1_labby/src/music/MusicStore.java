package music;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MusicStore {
	
	// returns an ArrayList of the album text files that need to be read in
	public static ArrayList<String> readAlbumsFile() throws FileNotFoundException {
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
	public static void readOneAlbumFile(String filename, AlbumList albumList, SongList songList) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("albums/" + filename));
		String[] albumInfo = scanner.nextLine().split(",");		
		Album album = new Album(albumInfo[0], albumInfo[1], albumInfo[2], albumInfo[3]);
		albumList.addAlbum(album);
		
		while(scanner.hasNextLine()) {
			Song song = new Song(scanner.nextLine(), album.getArtist(), album.getTitle());  // create song object
			album.addToSongList(song);  // add song to album
			songList.addSong(song);  // add song to overall songlist
		}
		
		scanner.close();
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		AlbumList albumList = new AlbumList();
		SongList songList = new SongList();
		SongList favSongList = new SongList();
		
		ArrayList<String> albumFilenames = readAlbumsFile();  // collects names of individual album names from initial albums.txt file
		
		for (String s : albumFilenames) {  // goes through each album file
			readOneAlbumFile(s, albumList, songList);
		}	
	}
	
}