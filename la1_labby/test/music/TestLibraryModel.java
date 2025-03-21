package music;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLibraryModel {
	
	MusicStore mStore;
	LibraryModel libMod;
	Song song1;
	Song song2;
	Song song3;
	Album album1;
	Album album2;
	Album album3;
	
	@BeforeEach
	void setUp() throws FileNotFoundException {
		 mStore = new MusicStore();
		 libMod = new LibraryModel();
		 song1 = mStore.getStoreSongByTandA("Fight for Your Mind", "Ben Harper");
		 song2 = mStore.getStoreSongByTandA("Lullaby", "Leonard Cohen");
		 song3 = mStore.getStoreSongByTandA("Lullaby", "OneRepublic");
		 libMod.addSongToLib(song1);
		 libMod.addSongToLib(song2);
		 libMod.addSongToLib(song3);
		 album1 = mStore.searchStoreAlbumByArtist("Adele").get(0);
		 album2 = mStore.searchStoreAlbumByArtist("Adele").get(1);
		 album3 = mStore.searchStoreAlbumByArtist("Dolly Parton").get(0);
		 libMod.addAlbumToLib(album1);
		 libMod.createPlaylist("playlist1");
		 
	}
	
	@Test
	void testGetRatedSongs() {
	    libMod.setRatingOfSong(song1, 4);
	    libMod.setRatingOfSong(song2, 5);

	    HashMap<Song, Rating> ratedSongs = libMod.getRatedSongs();

	    assertNotNull(ratedSongs);
	    assertEquals(2, ratedSongs.size());
	    assertTrue(ratedSongs.containsKey(song1));
	    assertEquals(Rating.FOUR, ratedSongs.get(song1));
	}
	
	@Test
	void testGetUserPlaylists() {
	    libMod.createPlaylist("playlist1");
	    libMod.addSongToPlaylist("playlist1", song1.getArtist(), song1.getTitle());
	    libMod.addSongToPlaylist("playlist1", song2.getArtist(), song2.getTitle());

	    ArrayList<SongList> userPlaylists = libMod.getUserPlaylists();
	    assertNotNull(userPlaylists);
	    
	    SongList playlist = userPlaylists.get(0);
	    assertEquals("playlist1", playlist.getPlaylistName());
	}

	@Test
	void testGetLibSongByTitle() {
		assertEquals(libMod.getLibSongByTitle("fight for your MIND"), song1.toString());
		assertEquals(libMod.getLibSongByTitle("nothing"), "Not found");
	}
	
	@Test
	void testGetLibSongByTitleSong() {
		assertEquals(libMod.getLibSongbyTitleSong("Lullaby").size(), 2);
		assertTrue(libMod.getLibSongbyTitleSong("nothing").isEmpty());
	}
	
	@Test
	void testGetLibSongByArtist() {
		assertEquals(libMod.getLibSongByArtist("OneRepublic"), song3.toString());
		assertEquals(libMod.getLibSongByArtist("nothing"), "Not found");
	}
	
	@Test
	void testGetLibSongByArtistSong() {
		assertEquals(libMod.getLibSongbyArtistSong("OneRepublic").size(), 1);
		assertTrue(libMod.getLibSongbyArtistSong("unknown").isEmpty());
	}
	
	@Test
	void testGetLibAlbumByTitle() {
		assertEquals(libMod.getLibAlbumByTitle("19"), "Album: 19; Artist: Adele; "
				+ "Year: 2008; Genre: Pop; Songs: Daydreamer,Adele,19,Pop,2008Best "
				+ "for Last,Adele,19,Pop,2008Chasing Pavements,Adele,19,Pop,2008Cold "
				+ "Shoulder,Adele,19,Pop,2008Crazy for You,Adele,19,Pop,2008Melt My Heart to "
				+ "Stone,Adele,19,Pop,2008First Love,Adele,19,Pop,2008Right as Rain,Adele,19,Pop,2008Make "
				+ "You Feel My Love,Adele,19,Pop,2008My Same,Adele,19,Pop,2008Tired,Adele,19,Pop,2008Hometown "
				+ "Glory,Adele,19,Pop,2008\n");
		assertEquals(libMod.getLibAlbumByTitle("unknown"), "");
	}
	
	@Test
	void testGetLibAlbumByArtist() {
		assertEquals(libMod.getLibAlbumByArtist("unknown"), "");
	}
	
	@Test
	void testGetPlaylist() {
	    libMod.createPlaylist("playlist1");
	    libMod.addSongToPlaylist("playlist1", song1.getArtist(), song1.getTitle());
	    libMod.addSongToPlaylist("playlist1", song2.getArtist(), song2.getTitle());
	    String playlist = libMod.getPlaylist("playlist1");
	    assertTrue(playlist.contains("playlist1"));
	}
	
	@Test
	void testSortedByTitle() {
	    libMod.addSongToLib(song1);
	    libMod.addSongToLib(song2);
	    String result = libMod.sortedByTitle();
	    assertEquals(result.charAt(0), 'B');
	    libMod.removeSong(song1);
	    libMod.removeSong(song2);
	}
	
	@Test
	void testSortedByArtist() {
	    libMod.addSongToLib(song1);
	    libMod.addSongToLib(song2);

	    String result = libMod.sortedByArtist();
	    assertNotNull(result);
	    assertTrue(result.contains(song1.toString()));
	    assertTrue(result.contains(song2.toString()));

	    libMod.removeSong(song1);
	    libMod.removeSong(song2);
	}
	
	@Test
	void testGetLibSongTitles() {
		assertEquals(libMod.getLibSongTitles(), "Fight for Your Mind\nLullaby"
				+ "\nLullaby\nDaydreamer\nBest for Last\nChasing Pavements\n"
				+ "Cold Shoulder\nCrazy for You\nMelt My Heart to Stone\n"
				+ "First Love\nRight as Rain\nMake You Feel My Love\nMy Same"
				+ "\nTired\nHometown Glory\n");
	}
	
	@Test
	void testGetLibArtists() {
		assertEquals(libMod.getLibArtists(), " Ben Harper\n"
				+ "Leonard Cohen\n"
				+ "OneRepublic\n"
				+ "Adele");
	}
	
	@Test
	void testGetLibAlbums() {
		assertEquals(libMod.getLibAlbums(), "Fight for Your Mind\nOld Ideas\nWaking Up\n19\n");
	}
	
	@Test
	void testGetPlaylists() {
		assertEquals(libMod.getPlaylists(), "Favorite Songs\nTop Rated Songs\n"
				+ "playlist1\nPop\nRecents\nMost Played\n");		
	}
	
	@Test
	void testCreatePlaylist() {
		libMod.createPlaylist("newPlaylist");
		assertTrue(libMod.getPlaylists().length() > 1);
	}
	
	@Test
	void testAddSongToPlaylist() {
		assertEquals(libMod.addSongToPlaylist("playlist1", 
				"Adele", "Chasing Pavements"), "Song added");
		assertEquals(libMod.addSongToPlaylist("not a playlist",
				"adele", ""), "Playlist not found");
	}
	
	@Test
	void testRemoveSongFromPlaylist() {
		libMod.addSongToPlaylist("playlist1", 
				"Adele", "Chasing Pavements");
		assertEquals(libMod.removeSongFromPlaylist("playlist1", 
				"Adele", "Chasing Pavements"), "Song Removed");
		assertEquals(libMod.removeSongFromPlaylist("playlist1", "Adele",
				"Not a song"), "Song Not Found");
		assertEquals(libMod.removeSongFromPlaylist("not a playlist",
				"adele", ""), "Playlist not found");
	}
	
	@Test
	void testSetRatingOfSong() {
		libMod.setRatingOfSong(song1, 3);
		assertTrue(libMod.getFavorites().length() == 0);
	}
	
	@Test
	void testGetFavorites() {
		assertTrue(libMod.getFavorites().length() == 0);
		libMod.setRatingOfSong(song1, 5);
		assertEquals(libMod.getFavorites(), song1.toString()+"\n");
	}
	
	@Test
	void testGetTopRatedSongs() {
	    libMod.setRatingOfSong(song1, 5);
	    libMod.setRatingOfSong(song2, 3);
	    libMod.addSongToLib(song1);
	    libMod.addSongToLib(song2);
	    String result = libMod.getTopRatedSongs();
	    assertTrue(result.contains(song1.toString()));
	}
	
	@Test
	void testGetGenreNames() {
	    String result = libMod.getGenreNames();
	    assertEquals("All Genres in Library:\n", result);
	}
	
	@Test
	void testGetGenres() {
		assertEquals(libMod.getGenres(), "Genre Playlists:\n"
				+ "PopSongs: /nDaydreamer,Adele,19,Pop,2008\n"
				+ "Best for Last,Adele,19,Pop,2008\n"
				+ "Chasing Pavements,Adele,19,Pop,2008\n"
				+ "Cold Shoulder,Adele,19,Pop,2008\n"
				+ "Crazy for You,Adele,19,Pop,2008\n"
				+ "Melt My Heart to Stone,Adele,19,Pop,2008\n"
				+ "First Love,Adele,19,Pop,2008\n"
				+ "Right as Rain,Adele,19,Pop,2008\n"
				+ "Make You Feel My Love,Adele,19,Pop,2008\n"
				+ "My Same,Adele,19,Pop,2008\n"
				+ "Tired,Adele,19,Pop,2008\n"
				+ "Hometown Glory,Adele,19,Pop,2008\n"
				+ "");
	}
	
	@Test
	void testGetRecents() {
		 assertEquals(libMod.getRecents(), "Recently Played Songs:\n");
	}
	
	@Test
	void testShuffleSongs() {
	    libMod.shuffleSongs();
	    ArrayList<Song> songsBeforeShuffle = new ArrayList<>(libMod.getLibrarySongs());
	    libMod.shuffleSongs();
	    ArrayList<Song> songsAfterShuffle = new ArrayList<>(libMod.getLibrarySongs());
	    assertNotEquals(songsBeforeShuffle, songsAfterShuffle);
	}
	
	@Test
	void testSortedByRating() {
	    libMod.addSongToLib(song1);
	    libMod.addSongToLib(song2);

	    libMod.addSongToPlaylist("playlist1", song1.getArtist(), song1.getTitle());
	    libMod.addSongToPlaylist("playlist1", song2.getArtist(), song2.getTitle());

	    libMod.setRatingOfSong(song1, 5);
	    libMod.setRatingOfSong(song2, 3);

	    String sortedSongs = libMod.sortedbyRating();

	    assertTrue(sortedSongs.contains(song1.toString()));
	    assertTrue(sortedSongs.contains(song2.toString()));
	    assertTrue(sortedSongs.indexOf(song1.toString()) > sortedSongs.indexOf(song2.toString()));
	}
	
	@Test
	void testShufflePlaylist() {
	    libMod.createPlaylist("playlist1");
	    libMod.addSongToPlaylist("playlist1", song1.getArtist(), song1.getTitle());
	    libMod.addSongToPlaylist("playlist1", song2.getArtist(), song2.getTitle());
	    libMod.addSongToPlaylist("playlist1", song3.getArtist(), song3.getTitle());
	    String originalPlaylist = libMod.getPlaylist("playlist1");
	    libMod.shufflePlaylist("playlist1");
	    String shuffledPlaylist = libMod.getPlaylist("playlist1");
	    assertNotEquals(originalPlaylist, shuffledPlaylist);
	}
	
	@Test
	void testGetAlbumFromSong() {
	    Song song = new Song("Fight For Your Mind", "Ben Harper", "Fight For Your Mind", "Folk", "2008");
	    ArrayList<Song> songs = new ArrayList<Song>();
	    songs.add(song);
	    // Add this song to an album
	    Album album = new Album("Fight For Your Mind", "Ben Harper", "Folk", "1995", songs);

	    libMod.addAlbumToLib(album);
	    String expected = "Album: Fight for Your Mind; Artist: Ben Harper; Year: 1995; Genre: Alternative; Songs: \n"
	    		+ "Oppression\n"
	    		+ "Ground on Down\n"
	    		+ "Another Lonely Day\n"
	    		+ "Gold to Me\n"
	    		+ "Burn One Down\n"
	    		+ "Excuse Me Mr.\n"
	    		+ "People Lead\n"
	    		+ "Fight for Your Mind\n"
	    		+ "Give a Man a Home\n"
	    		+ "By My Side\n"
	    		+ "Power of the Gospel\n"
	    		+ "God Fearing Man\n"
	    		+ "One Road to Freedom\n"
	    		+ "Album is in Library";
	    String result = libMod.getAlbumFromSong(song);
	    assertEquals(expected, result);
	}
	
	@Test
	void testRemoveAlbum() {
	    Album album = new Album("Ben Harper", "Fight For Your Mind", "Folk", "1995", new ArrayList<Song>());
	    libMod.addAlbumToLib(album);
	    assertEquals(libMod.getLibAlbumByTitle("Fight For Your Mind"), "Album: Fight for Your Mind; Artist: Ben Harper; Year: 1995; Genre: Alternative; Songs: Fight for Your Mind,Ben Harper,Fight for Your Mind,Alternative,1995\n"
	    		+ "Album: Fight For Your Mind; Artist: Ben Harper; Year: 1995; Genre: Folk; Songs: \n");
	    libMod.removeAlbum(album.getTitle());
	}

}
