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
	public static void readOneAlbumFile(String filename, LibraryModel library) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("albums/" + filename));
		String[] albumInfo = scanner.nextLine().split(",");	
		String artist = albumInfo[0];
		String albumTitle = albumInfo[1];
		String genre = albumInfo[2];
		String year = albumInfo[3];
		
		ArrayList<Song> albumSongList = new ArrayList<Song>();
		while(scanner.hasNextLine()) {
			String songTitle = scanner.nextLine();
			Song song = new Song(songTitle, artist, albumTitle);
			albumSongList.add(song);
		}
		library.addNewAlbumToStore(artist, albumTitle, genre, year, albumSongList);
		scanner.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		
		ArrayList<String> albumFilenames = readAlbumsFile();  // collects names of individual album names from initial albums.txt file
		
		for (String s : albumFilenames) {  // goes through each album file
			readOneAlbumFile(s, library);
		}
		
		// use while loop to call view methods and loop through
	}
	
}