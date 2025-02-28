package music;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

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
		assertEquals(libMod.getLibAlbumByTitle("19"), album1.toString());
		assertEquals(libMod.getLibAlbumByTitle("unknown"), "Not found");
	}
	
	@Test
	void testGetLibAlbumByArtist() {
		assertEquals(libMod.getLibAlbumByArtist("unknown"), "Not found");
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
		assertEquals(libMod.getLibArtists(), "\n");
	}
	
	@Test
	void testGetLibAlbums() {
		assertEquals(libMod.getLibAlbums(), "19\n");
	}
	
	@Test
	void testGetPlaylists() {
		assertEquals(libMod.getPlaylists(), "playlist1\n");		
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
		assertEquals(libMod.addSongToPlaylist("playlist1", "Adele",
				"Not a song"), "Song not found");
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

}
