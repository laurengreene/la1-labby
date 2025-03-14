
package music;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
	private static Scanner scn;
	private static MusicStore mStore;
	private static LibraryModel libModel;
	private static String username;
	private static UserManager userMain; 
	
	private View() throws FileNotFoundException {}
	
	private static void logInPage() {
		System.out.println("Welcome to the Labby Music Store!\nTo create an account, "
				+ "input 'c'. To log in, input 'l'.");
		String result = scn.next();scn.nextLine();
		if (result.equals("c")) {
			createAccount();
			logIn();
		}
		else if (result.equals("l")) {
			logIn();
		}
		else {
			System.out.println("Invalid input.\n");
			logInPage();
		}
		userMain.getUserData(username);
		start();
	}
	
	private static void createAccount() {
		System.out.println("Create account below");
		System.out.print("Enter a username: ");
		username = scn.next();scn.nextLine();
		// check for existing username
		// want to be false
		System.out.print("Password: ");
		userMain.addUser(username, scn.next());
		System.out.println("Your account has been created. Continue to log in page.\n");
	}
	
	private static void logIn() {
		System.out.println("Log in below.");
		System.out.print("username: ");
		username = scn.next();scn.nextLine();
		System.out.print("Password: ");
		boolean exists = userMain.checkForExistingUsername(username);
		boolean correctLogIn = userMain.checkPassword(username, scn.next());
		if (!exists || !correctLogIn) {
			System.out.println("Incorrect username or password.");
			System.out.println("Try again (t) or go back to log in page (l)?");
			String input = scn.next();scn.nextLine();
			if (input.equals("t")) logIn();
			else if (input.equals("l")) logInPage();
			else {
				System.out.println("Invalid input. Redirecting to log in page.\n");
				logInPage();
			}
		}
	}
	
	private static void start() {
		System.out.println("\nWelcome to the Labbys Music Store!\n To get to "
				+ "this page, input 'home'-- To exit the store, input 'done'"
				+ " \nWould you like to: \nCreate Playlist(c) \nSearch(s)");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "c" :
			createPlaylist();
			break;
		case "s" :
			search();
			break;
		default :
			System.out.println("Invalid Input");
			start();
		}
	}
	
	
	private static void createPlaylist() {
		System.out.println("Input Playlist Name:");
		String pName = scn.next();scn.nextLine();
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
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch(input) {
		case "s" :
			searchStore();
			break;
		case "l" :
			searchLib();
			break;
		default :
			System.out.println("Invalid Input");
			search();
		}
	}
	
	private static void searchStore() {
		System.out.println("Search for Song(s) or Album(a)?");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch(input) {
		case "a" :
			searchStoreForAlbum();
			break;
		case "s" :
			searchStoreForSong();
			break;
		default :
			System.out.println("Invalid Input");
			searchStore();
		}
	}
	
	private static void searchStoreForAlbum() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.next();scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.next();scn.nextLine();
		checkIfDone(name);
		ArrayList<Album> albums = new ArrayList<Album>();
		switch (tOra) {
		case "a" :
			albums = mStore.searchStoreAlbumByArtist(name);
			break;
		case "t" :
			albums = mStore.searchStoreAlbumByTitle(name);
			break;
		default :
			System.out.println("Invalid Input");
			searchStoreForAlbum();
		}
		if(albums.size() == 0) { System.out.println("Album not found"); searchStoreForAlbum();}
		for (Album a : albums) {
			System.out.println(a);
		}
		System.out.println("Add album to library? (y)/(n)");
		String ifAdd = scn.next();scn.nextLine();
		checkIfDone(ifAdd);
		if(ifAdd.equals("y")) {
			if (albums.size() == 1) libModel.addAlbumToLib(albums.get(0)); 
			else {
				System.out.println("Enter the name of the album you would like to add");
				String input = scn.next();scn.nextLine();
				for(Album a : albums) {
					if (input.equalsIgnoreCase(a.getTitle())) {
						libModel.addAlbumToLib(a);
					}
				}
			}
			System.out.println("Album added");
		} 
		start();
	}
	
	private static void searchStoreForSong() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.next();scn.nextLine();
		checkIfDone(name);
		ArrayList<Song> songs;
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
		if(songs.size() == 0) { System.out.println("Song not found"); searchStoreForSong();}
		for (Song s : songs) {
			System.out.println(s);
		}
		System.out.println("Add song to library? (y)/(n)");
		String ifAdd = scn.next();scn.nextLine();
		checkIfDone(ifAdd);
		if(ifAdd.equals("y")) {
			Song toAdd;
			if(tOra.equals("a")) {
				System.out.println("Song title to add:");
				String title = scn.nextLine();
				toAdd = mStore.getStoreSongByTandA(title, name);
			}
			else if (tOra.equals("t")) {
				System.out.println("Song artist to add:");
				String artist = scn.nextLine();
				toAdd = mStore.getStoreSongByTandA(name, artist);
			}
			else{toAdd = null;}
			if(toAdd != null) {libModel.addSongToLib(toAdd); System.out.println("Song added to Library");}
			else System.out.println("Song not found");
		}
		start();
	}
	
	public static void searchLib() {
		System.out.println("Search for Specific Information(s) or Get All(a)");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "s" :
			getOne();
			break;
		case "a" :
			getAll(); // to return list of all objects
			break;
		default :
			System.out.println("Invalid Input");
			searchLib();
		}
	}
	
	public static void getOne() {
		System.out.println("Search for: \nSong(s) \nAlbum(a) \nPlaylist(p)");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch(input) {
		case "s" :
			searchLibForSong();
			break;
		case "a" :
			searchLibForAlbum();
			break;
		case "p" :
			searchForPlaylist();
			break;
		default :
			System.out.println("Invalid Input");
			getOne();
		}
	}
	
	private static void searchLibForSong() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.next();scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.next();scn.nextLine();
		checkIfDone(name);
		ArrayList<Song> songs;
		switch(tOra) {
		case "a" :
			songs = libModel.getLibSongbyArtistSong(name);
			break;
		case "t" :
			songs = libModel.getLibSongbyTitleSong(name);
			break;
		default :
			System.out.println("Invalid Input");
			searchLibForSong();
			songs = new ArrayList<Song>();
		}
		if(songs.size() == 0) {
			System.out.println("No Songs Found");
			searchLibForSong();
		}
		for(int i = 0; i < songs.size(); i++) {
			String str = "";
			str += (i + 1) + ": " + songs.get(i).toString();
			System.out.println(str);
		}
		if(songs.size() == 0) { System.out.println("No songs found"); searchLibForSong();}
		System.out.println("Would you like to rate the song?(y)/(n)");
		String toRate = scn.next();scn.nextLine();
		checkIfDone(toRate);
		if(toRate.equals("y")) {
			Song songToRate = null;
			if (songs.size() > 1) {
				int sIndex = (scn.nextInt());
				if(sIndex > songs.size() + 1) {
					System.out.println("Invalid number");  // add try again
					System.out.println("What song would you like to rate?(by number)");
					sIndex = (scn.nextInt());
				}
				songToRate = songs.get(sIndex - 1);
			} else {
				songToRate = songs.get(0);
			}
			
			System.out.println("Rating out of 5: ");
			int rate = (scn.nextInt());
			if (rate > 5) {
				System.out.println("Invalid rating");
				System.out.println("Rating out of 5: ");
				rate = (scn.nextInt());
			}
			libModel.setRatingOfSong(songToRate, rate);
			System.out.println("Rating saved");
			start();
		} else {
			start();
		}
		
	}
	
	private static void searchLibForAlbum() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.next();scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.next();scn.nextLine();
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
		String name = scn.next();scn.nextLine();
		checkIfDone(name);
		String playlist = libModel.getPlaylist(name);
		if(playlist.equals("Not Found")) {
			System.out.println("Playlist Not Found"); 
			searchForPlaylist(); }
		System.out.println(playlist);
		System.out.println("Edit Playlist? (y)/(n)");
		String ifEdit = scn.next();scn.nextLine();
		checkIfDone(ifEdit);
		if (ifEdit.equals("y")) editPlaylist(name);
		else start();
	}
	
	private static void editPlaylist(String pName) {
		System.out.println("Add(a) or Remove(r) song?");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "a" :
			addSongToPlaylist(pName);
			break;
		case "r" :
			removeSongFromPlaylist(pName);
			break;
		}
	}
	
	private static void removeSongFromPlaylist(String pName) {
		System.out.println("Song TITLE of song to remove:");
		String title = scn.next();scn.nextLine();
		checkIfDone(title);
		System.out.println("Song ARTIST of song to remove:");
		String artist = scn.next();scn.nextLine();
		checkIfDone(artist);
		System.out.println(libModel.removeSongFromPlaylist(pName, artist, title));
		start();
	}
	
	private static void addSongToPlaylist(String pName) {
		System.out.println("Song TITLE of song to add:");
		String title = scn.next();scn.nextLine();
		checkIfDone(title);
		System.out.println("Song ARTIST of song to add:");
		String artist = scn.next();scn.nextLine();
		checkIfDone(artist);
		System.out.println(libModel.addSongToPlaylist(pName, artist, title));
		start();
	}
	
	// playlist branch
	private static void viewPlaylists() {
		System.out.println(libModel.getPlaylists());
		System.out.println("Would you like to view a playlist? (y/n)");
		String response = scn.next();scn.nextLine();
		if (response.equals("y")) {
			System.out.println("Which one would you like to view? Type in its name");
			String input = scn.next();scn.nextLine();
			System.out.println(libModel.getPlaylist(input));
		}
		start();
	}
	
	private static void getAll() {
		System.out.println("What would you like to search? \nSong Titles(s)"
				+ " \nArists(r) \nAlbums(l) \nPlaylists(p) \nFavorites(f)");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "s" :
			System.out.println(libModel.getLibSongTitles());
			break;
		case "r" :
			System.out.println(libModel.getLibArtists());
			break;
		case "l" :
			System.out.println(libModel.getLibAlbums());
			break;
		case "p" :
			viewPlaylists();
			break;
		case "f" :
			System.out.println(libModel.getFavorites());
			break;
		default :
			System.out.println("Invalid Input");
			getAll();
		}
		start();
	}
	
	
	private static void checkIfDone(String s) {
		if(s.equals("done")) {
			System.out.println("Would you like to save your data? (y/n)");
			String input = scn.next();scn.nextLine();
			if (input.equals("y")) {
				userMain.turnUserDataIntoFile(username); // save data
				System.out.println("Data saved. Goodbye.");
			}
			scn.close();
			System.exit(0);
		}
		if(s.equals("home")) start();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		mStore = new MusicStore();
		libModel = new LibraryModel();
		userMain = new UserManager(libModel);  // holds user information
		scn = new Scanner(System.in);
		logInPage();
	}

}