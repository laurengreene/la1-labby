
package music;

import java.io.FileNotFoundException;
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
		switch (tOra) {
		case "a" :
			// get album by artist from store
		case "t" :
			// get album by title from store
		default :
			System.out.println("Invalid Input");
			searchStoreForAlbum();
		}
		// save album information
		// print album information or 'album not in store'
		System.out.println("Add album to library? (y)/(n)");
		String ifAdd = scn.nextLine();
		checkIfDone(ifAdd);
		if(ifAdd.equals("y")) libModel.addAlbumToLib(null); // change null to album information
		start();
	}
	
	private void searchStoreForSong() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.nextLine();
		checkIfDone(name);
		switch (tOra) {
		case "a" :
			// get song by artist from store
		case "t" :
			// get song by title from store
		default :
			System.out.println("Invalid Input");
			searchStoreForSong();
		}
		// save song information
		// print song information or 'song not in store'
		System.out.println("Add song to library? (y)/(n)");
		String ifAdd = scn.nextLine();
		checkIfDone(ifAdd);
		if(ifAdd.equals("y")) libModel.addSongToLib(null); // change null to song information
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
			getAll();
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
		
	}
	
	private void checkIfDone(String s) {
		if(s.equals("done")) System.exit(0);
		if(s.equals("home")) start();
	}

}