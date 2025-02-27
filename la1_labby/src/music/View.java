
package music;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
	private static Scanner scn;
	private static MusicStore mStore;
	private static LibraryModel libModel;
	
	private View() throws FileNotFoundException {
		
	}
	
	private static void start() {
		System.out.println("Welcome to the Labbys Music Store!\n To get to "
				+ "this page, input 'home'-- To exit the store, input 'done'"
				+ " \nWould you like to: \nCreate Playlist(c) \nSearch(s)");
		String input = scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "c" :
			createPlaylist();
		case "s" :
			search();
		default :
			System.out.println("Invalid Input");
			start();
		}
	}
	
	
	private static void createPlaylist() {
		System.out.println("Input Playlist Name:");
		String pName = scn.nextLine();
		checkIfDone(pName);
		if (libModel.getPlaylist(pName).equals("Not Found")) {
			libModel.createPlaylist(pName);
			System.out.println("Playlist " + pName + " created.");
			start();
		}
		else {
			System.out.println("Playlist Name Already In Use"); 
			createPlaylist();
		}
	}
	
	private static void search() {
		System.out.println("Search Store(s) or Library(l)?");
		String input = scn.nextLine();
		checkIfDone(input);
		switch(input) {
		case "s" :
			searchStore();
		case "l" :
			searchLib();
		default :
			System.out.println("Invalid Input");
			search();
		}
	}
	
	private static void searchStore() {
		System.out.println("Search for Song(s) or Album(a)?");
		String input = scn.nextLine();
		checkIfDone(input);
		switch(input) {
		case "a" :
			searchStoreForAlbum();
		case "s" :
			searchStoreForSong();
		default :
			System.out.println("Invalid Input");
			searchStore();
		}
	}
	
	private static void searchStoreForAlbum() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.nextLine();
		checkIfDone(name);
		ArrayList<Album> albums;
		switch (tOra) {
		case "a" :
			albums = mStore.searchStoreAlbumByArtist(name);
		case "t" :
			albums = mStore.searchStoreAlbumByTitle(name);
		default :
			System.out.println("Invalid Input");
			searchStoreForAlbum();
			albums = new ArrayList<Album>();
		}
		if(albums.size() == 0) System.out.println("Album not found"); searchStoreForAlbum();
		for (Album a : albums) {
			System.out.println(a);
		}
		System.out.println("Add album to library? (y)/(n)");
		String ifAdd = scn.nextLine();
		checkIfDone(ifAdd);
		if(ifAdd.equals("y")) {
			for(Album a : albums) {
				libModel.addAlbumToLib(a); 
			}
		} 
		start();
	}
	
	private static void searchStoreForSong() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.nextLine();
		checkIfDone(tOra);
		System.out.println(tOra);
		System.out.println("Input Name:");
		String name = scn.nextLine();
		checkIfDone(name);
		ArrayList<Song> songs;
		System.out.println(tOra);
		switch (tOra) {
		case "a" :
			songs = mStore.searchStoreSongByArtist(name);
			break;
		case "t" :
			songs = mStore.searchStoreSongByTitle(name);
			break;
		default :
			System.out.println("Invalid Input");
			searchStoreForSong();
			songs = new ArrayList<Song>();
		}
		if(songs.size() == 0) System.out.println("Song not found"); searchStoreForSong();
		for (Song s : songs) {
			System.out.println(s);
		}
		System.out.println("Add song to library? (y)/(n)");
		String ifAdd = scn.nextLine();
		checkIfDone(ifAdd);
		if(ifAdd.equals("y")) {
			for(Song s : songs)
			libModel.addSongToLib(s); 
		}
		start();
	}
	
	public static void searchLib() {
		System.out.println("Search for Specific Information(s) or Get All(a)");
		String input = scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "s" :
			getOne();
		case "a" :
			getAll(); // to return list of all objects
		default :
			System.out.println("Invalid Input");
			searchLib();
		}
	}
	
	public static void getOne() {
		System.out.println("Search for: \nSong(s) \nAlbum(a) \nPlaylist(p)");
		String input = scn.nextLine();
		checkIfDone(input);
		switch(input) {
		case "s" :
			searchLibForSong();
		case "a" :
			searchLibForAlbum();
		case "p" :
			searchForPlaylist();
		default :
			System.out.println("Invalid Input");
			getOne();
		}
	}
	
	private static void searchLibForSong() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.nextLine();
		checkIfDone(name);
		switch(tOra) {
		case "a" :
			System.out.println(libModel.getLibSongByArtist(name));
			break;
		case "t" :
			System.out.println(libModel.getLibSongByTitle(name));
			break;
		}
	}
	
	private static void searchLibForAlbum() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.nextLine();
		checkIfDone(name);
		switch (tOra) {
		case "a" :
			System.out.println(libModel.getLibAlbumByArtist(name));
			break;
		case "t" :
			System.out.println(libModel.getLibAlbumByTitle(name));
			break;
		default :
			System.out.println("Invalid Input");
			searchLibForAlbum();
		}
	}
	
	private static void searchForPlaylist() {
		System.out.println("Playlist name: ");
		String name = scn.nextLine();
		checkIfDone(name);
		String playlist = libModel.getPlaylist(name);
		if(playlist == "Not Found") {
			System.out.println("Playlist Not Found"); 
			searchForPlaylist(); }
		System.out.println(playlist);
		System.out.println("Edit Playlist? (y)/(n)");
		String ifEdit = scn.nextLine();
		checkIfDone(ifEdit);
		if (ifEdit.equals("y")) editPlaylist(name);
		else start();
	}
	
	private static void editPlaylist(String pName) {
		System.out.println("Add(a) or Remove(r) song?");
		String input = scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "a" :
			addSongToPlaylist(pName);
		case "r" :
			removeSongFromPlaylist(pName);
		}
	}
	
	private static void removeSongFromPlaylist(String pName) {
		System.out.println("Song TITLE of song to remove:");
		String title = scn.nextLine();
		checkIfDone(title);
		System.out.println("Song ARTIST of song to remove:");
		String artist = scn.nextLine();
		checkIfDone(artist);
		System.out.println(libModel.removeSongFromPlaylist(pName, artist, title));
		start();
	}
	
	private static void addSongToPlaylist(String pName) {
		// maybe print out all songs in library so user can read from them and pick?
		System.out.println("Song TITLE of song to add:");
		String title = scn.nextLine();
		checkIfDone(title);
		System.out.println("Song ARTIST of song to add:");
		String artist = scn.nextLine();
		checkIfDone(artist);
		System.out.println(libModel.addSongToPlaylist(pName, artist, title));
		start();
	}
	
	private static void getAll() {
		System.out.println("What would you like to search? \nSong Titles(s)"
				+ " \nArists(r) \nAlbums(l) \nPlaylists(p) \nFavorites(f)");
		String input = scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "s" :
			System.out.println(libModel.getLibSongTitles());
		case "r" :
			System.out.println(libModel.getLibArtists());
		case "l" :
			System.out.println(libModel.getLibAlbums());
		case "p" :
			System.out.println(libModel.getPlaylists());
		case "f" :
			System.out.println(libModel.getFavorites());
		default :
			System.out.println("Invalid Input");
			getAll();
		}
	}
	
	
	private static void checkIfDone(String s) {
		if(s.equals("done")) System.exit(0);
		if(s.equals("home")) start();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		mStore = new MusicStore();
		libModel = new LibraryModel();
		scn = new Scanner(System.in);
		start();
	}

}