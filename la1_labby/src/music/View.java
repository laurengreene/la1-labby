

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
		String result = scn.nextLine();
		if (result.equals("c")) {
			System.out.println("Create account below");
			createAccount();
			logIn();
		}
		else if (result.equals("l")) {
			logIn();
			userMain.getUserData(username);
		}
		else {
			System.out.println("Invalid input.\n");
			logInPage();
		}
		
		start();
	}
	
	private static void createAccount() {
		System.out.print("Enter a username: ");
		username = scn.nextLine();
		if (userMain.usernameExists(username)) {  // username already exists
			System.out.println("Username taken. Enter a new username below.\n");
			createAccount();
			return;
		}
		System.out.print("Password: ");
		userMain.addUser(username, scn.next());
		System.out.println("Your account has been created. Continue to log in page.\n");
	}
	
	private static void logIn() {
		System.out.println("Log in below.");
		System.out.print("username: ");
		username = scn.next();scn.nextLine();
		System.out.print("Password: ");
		boolean exists = userMain.usernameExists(username);  // want to be true
		boolean correctLogIn = userMain.checkPassword(username, scn.next());
		if (!exists || !correctLogIn) {
			System.out.println("\nIncorrect username or password.");
			System.out.println("Try again (t) or go back to log in page (l)?");
			String input = scn.nextLine();
			if (input.equals("t")) logIn();
			else if (input.equals("l")) logInPage();
			else {
				System.out.println("Invalid input. Redirecting to log in page.\n");
				logInPage();
			}
		}
	}
	
	private static void start() {
		System.out.println("\nWelcome to the Labbys Music Store " + username+ "!\nTo get to "
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
		System.out.println("Search for: \nSong(s) \nAlbum(a) \nPlaylist(p) \nGenre(g)");
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
		case "g" :
			searchForGenre();
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
		System.out.println("Would you like to remove the song? (y)/(n)");
		String toRemove = scn.next();scn.nextLine();
		checkIfDone(toRemove);
		if (toRemove.equals("y")) {
			Song songToRemove = null;
			if (songs.size() > 1) {
				System.out.println("What song would you like to remove?(by number)");
				int sIndex = (scn.nextInt());
				if(sIndex > songs.size() + 1) {
					System.out.println("Invalid number");  // add try again
					System.out.println("What song would you like to remove?(by number)");
					sIndex = (scn.nextInt());
				}
				songToRemove = songs.get(sIndex - 1);
			} else {
				songToRemove = songs.get(0);
			}
			libModel.removeSong(songToRemove);
			System.out.println("Song removed");
		}
		System.out.println("Would you like to rate the song?(y)/(n)");
		String toRate = scn.next();scn.nextLine();
		checkIfDone(toRate);
		if(toRate.equals("y")) {
			Song songToRate = null;
			if (songs.size() > 1) {
				System.out.println("What song would you like to rate?(by number)");
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
				return;  // should fix recursion ***************************************************
			}
			libModel.setRatingOfSong(songToRate, rate);
			System.out.println("Rating saved");
			start();
		} else {
			System.out.println("Would you like to get album information? (y)/(n)");
			String aInfo = scn.next();scn.nextLine();
			checkIfDone(aInfo);
			if (aInfo.equals("y")) {
				Song getInfo = null;
				if (songs.size() > 1) {
					System.out.println("What song would you like to get information for?(by number)");
					int sIndex = (scn.nextInt());
					if(sIndex > songs.size() + 1) {
						System.out.println("Invalid number");  // add try again
						System.out.println("What song would you like to get information for?(by number)");
						sIndex = (scn.nextInt());
					}
					getInfo = songs.get(sIndex - 1);
				} else {getInfo = songs.get(0);}
				System.out.println(libModel.getAlbumFromSong(getInfo));
			}
		}
		start();
	}
	
	private static void searchLibForAlbum() {
		System.out.println("Search by Artist(a) or Title(t)?");
		String tOra = scn.next();scn.nextLine();
		checkIfDone(tOra);
		System.out.println("Input Name:");
		String name = scn.next();scn.nextLine();
		checkIfDone(name);
		String album;
		switch (tOra) {
		case "a" :
			album = libModel.getLibAlbumByArtist(name);
			break;
		case "t" :
			album = libModel.getLibAlbumByTitle(name);
			break;
		default :
			System.out.println("Invalid Input");
			searchLibForAlbum();
			album = "Invalid Input";
		}
		System.out.print(album);
		System.out.println("Remove Album? (y)/(n)");
		String ifRemove = scn.next();scn.nextLine();
		if (ifRemove.equals("y")) {
			libModel.removeAlbum(name);
			System.out.println("Album Removed");
		}
	}
	
	private static void searchForGenre() {
		System.out.println(libModel.getGenreNames());
		System.out.println("Which genre would you like to see?");
		String genre = scn.next();scn.nextLine();
		System.out.println(libModel.getSongsByGenre(genre));
		start();
	}
	
	private static void searchForPlaylist() {
		System.out.println("Would you like to get User Playlist(u) or Automatic Playlist(a)");
		String uorA = scn.next();scn.nextLine();
		checkIfDone(uorA);
		if (uorA.equals("a")) automaticPlaylists();
		System.out.println("Playlist name: ");
		String name = scn.next();scn.nextLine();
		checkIfDone(name);
		String playlist = libModel.getPlaylist(name);
		if(playlist.equals("Not Found")) {
			System.out.println("Playlist Not Found"); 
			searchForPlaylist(); }
		System.out.println(playlist);
		System.out.print("Shuffle Playlist? (y)/(n)");
		String ifShuffle = scn.next();scn.nextLine();
		if(ifShuffle.equals("y")) {
			System.out.println(libModel.shufflePlaylist(name));
			System.out.println(libModel.getPlaylist(name));
		}
		System.out.println("Edit Playlist? (y)/(n)");
		String ifEdit = scn.next();scn.nextLine();
		checkIfDone(ifEdit);
		if (ifEdit.equals("y")) editPlaylist(name);
		else start();
	}
	
	private static void automaticPlaylists() {
		System.out.println("What playlist would you like? /nRecents(r) /nFrequents(q) /nFavorites(f) /nGenres(g) /nTop Rated(t)");
		String input = scn.next();scn.nextLine();
		switch (input) {
		case "r" :
			System.out.println(libModel.getRecents());
			start();
			break;
		case "q" :
			System.out.println(libModel.getFrequents());
			start();
			break;
		case "f" :
			System.out.println(libModel.getFavorites());
			start();
			break;
		case "g" :
			System.out.println(libModel.getGenres());
			start();
			break;
		case "t" :
			System.out.println(libModel.getTopRatedSongs());
			start();
			break;
		default :
			System.out.println("Invalid Input");
			automaticPlaylists();
		}
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
		default :
			System.out.println("Invalid Input");
			editPlaylist(pName);
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
		System.out.println("Song TITLE of song to add:");
		String title = scn.nextLine();
		checkIfDone(title);
		System.out.println("Song ARTIST of song to add:");
		String artist = scn.nextLine();
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
		System.out.println("Would you like to search for Sorted(s) or Unsorted(u)?");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "s" :
			getAllSorted();
			break;
		case "u" :
			getAllUnsorted();
			break;
		default :
			System.out.println("Invalid Input");
			getAll();
		}
	}
	
	private static void getAllSorted() {
		System.out.println("What would you like to sort by? \nSong Titles(t) \nSong Artists(a) \nSong Ratings(r)");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "t" :
			System.out.println(libModel.sortedByTitle());
			break;
		case "a" :
			System.out.println(libModel.sortedByArtist());
			break;
		case "r" :
			System.out.println(libModel.sortedbyRating());
			break;
		default :
			System.out.println("Invalid Input");
			getAllSorted();
		}
		start();
	}
	
	private static void getAllUnsorted() {
		System.out.println("What would you like to search? \nSong Titles(s)"
				+ " \nArists(r) \nAlbums(l) \nPlaylists(p) \nFavorites(f)");
		String input = scn.next();scn.nextLine();
		checkIfDone(input);
		switch (input) {
		case "s" :
			System.out.println(libModel.getLibSongTitles());
			System.out.println("Shuffle Songs? (y)/(n)");
			String ifShuffle = scn.next();scn.nextLine();
			if (ifShuffle.equals("y")) {
				libModel.shuffleSongs();
				System.out.println("Songs Shuffled");
				System.out.println(libModel.getLibSongTitles());
			}
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
			getAllUnsorted();
		}
		start();
	}
	
	
	private static void checkIfDone(String s) {
		if(s.equals("done")) {
			userMain.turnUserDataIntoFile(username); // save user data in text file
			System.out.println("Data saved.");
			username = "";
			System.out.println("You are logged out. Would you like to go back to the log in page (l) or end this session(e)?");
			String input = scn.nextLine();
			if (input.equals("e")) {
				System.out.println("Thank you for using Labby Music Store!");
				System.exit(0);
			} else if (input.equals("l")) {
				System.out.println("Sending you to the log in page.");
				logInPage();
				return;
			}
			else {
				System.out.println("\nInvalid input. Sending you to the log in page.\n");
				logInPage();
				return;
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