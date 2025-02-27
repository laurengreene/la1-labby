
package music;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
	private Scanner scn;
	private MusicStore mStore;
	private LibraryModel libModel;
	
	private View() throws FileNotFoundException {
		mStore = new MusicStore();
		libModel = new LibraryModel();
		scn = new Scanner(System.in);
		start();
	}
	
	private void start() {
		System.out.println("Welcome to the Labbys Music Store!\n To get to "
				+ "this page, input 'home'-- To exit the store, input 'done'"
				+ " \n Would you like to: \nCreate Playlist(c) \nSearch(s)");
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
	
	
	private void createPlaylist() {
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
	
	private void search() {
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
	
	private void searchStore() {
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
	
	private void searchStoreForAlbum() {
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
	
	private void searchStoreForSong() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.nextLine();
		checkIfDone(name);
		ArrayList<Song> songs;
		switch (tOra) {
		case "a" :
			songs = mStore.searchStoreSongByArtist(name);
		case "t" :
			songs = mStore.searchStoreSongByTitle(name);
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
	
	public void searchLib() {
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
	
	public void getOne() {
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
	
	private void searchLibForSong() {
		// im not sure how we wanna do this one, we need to keep the 
		// song reference to update the rating if needed 
		// maybe use equals method and look through all objects in
		// songlist and update the one that is equal, could be easier way
	}
	
	private void searchLibForAlbum() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.nextLine();
		checkIfDone(name);
		switch (tOra) {
		case "a" :
			System.out.println(libModel.getLibAlbumByArtist(name));
		case "t" :
			System.out.println(libModel.getLibAlbumByTitle(name));
		default :
			System.out.println("Invalid Input");
			searchLibForAlbum();
		}
	}
	
	private void searchForPlaylist() {
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
	
	private void editPlaylist(String pName) {
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
	
	private void removeSongFromPlaylist(String pName) {
		System.out.println("Song TITLE of song to remove:");
		String title = scn.nextLine();
		checkIfDone(title);
		System.out.println("Song ARTIST of song to remove:");
		String artist = scn.nextLine();
		checkIfDone(artist);
		System.out.println(libModel.removeSongFromPlaylist(pName, artist, title));
		start();
	}
	
	private void addSongToPlaylist(String pName) {
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
	
	private void checkIfDone(String s) {
		if(s.equals("done")) System.exit(0);
		if(s.equals("home")) start();
	}

}
