package music;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestSongList {

	SongList sList = new SongList();
	Song song1 = new Song("Darkness", "Leonard Cohen", "Old Ideas", "Singer/Songwriter", "2012");
	Song song2 = new Song("Going Home", "Leonard Cohen", "Old Ideas", "Singer/Songwriter", "2012");
	Song song3 = new Song("Little Lion Man", "Mumford & Sons", "Sigh No More", "Alternative", "2009");
	Song song4 = new Song("Lullaby", "Leonard Cohen", "Old Ideas", "Singer/Songwriter", "2012");
	Song song5 = new Song("Lullaby", "OneRepublic", "Waking Up", "Rock", "2009");
	
	@Test
	void testAddSong() {
		assertEquals(0, sList.getSongs().size());
		sList.addSong(song1);
		assertEquals(1, sList.getSongs().size());
		sList.addSong(song1);
		assertEquals(1, sList.getSongs().size());
	}
	
	@Test
	void testRemoveSong() {
		sList.addSong(song3);
		sList.removeSong(song1);
		assertEquals(sList.getSongs().size(), 1);
		sList.removeSong(song3);
		assertFalse(sList.getSongs().contains(song3));
	}
	
	@Test
	void testGetSongByTitle() {
		sList.addSong(song1);
		sList.addSong(song3);
		assertTrue(sList.getSongByTitle("Darkness").equals(song1.toString()));
		assertEquals(sList.getSongByTitle("Fake Song Title"), "Not found");
	}
	
	@Test
	void testGetSongObjectByTitle() {
		sList.addSong(song1);
		assertTrue(sList.getSongObjectByTitle("Darkness").get(0).equals(song1));
		assertTrue(sList.getSongObjectByTitle("DARKNESS").get(0).equals(song1));
		assertEquals(sList.getSongObjectByTitle("nothing").size(), 0);
	}
	
	@Test
	void testGetSongByArtist() {
		sList.addSong(song1);
		sList.addSong(song2);
		sList.addSong(song3);
		assertTrue(sList.getSongByArtist("Leonard Cohen").equals(song1.toString()+"\n"
		+ song2.toString()));
		assertEquals(sList.getSongByArtist("adele"), "Not found");
	}
	
	@Test
	void testGetSongObjectByArtist() {
		sList.addSong(song1);
		assertTrue(sList.getSongObjectByArtist("Leonard Cohen").get(0).equals(song1));
		sList.addSong(song2);
		assertEquals(sList.getSongObjectByArtist("Leonard Cohen").size(), 2);
		assertTrue(sList.getSongObjectByArtist("LEONARD cohen").get(0).equals(song1));
		assertEquals(sList.getSongObjectByArtist("nothing").size(), 0);
	}
	
	@Test
	void testGetSongByTitleAndArtist() {
		sList.addSong(song5);
		assertTrue(sList.getSongByTitleAndArtist("OneRepublic", "Lullaby").equals(song5));
		assertTrue(sList.getSongByTitleAndArtist("ONEREpublic", "Lullaby").equals(song5));
		assertNull(sList.getSongByTitleAndArtist("nobody", "no title"));
	}
	
	@Test
	void testGetSongs() {
		sList.addSong(song2);
		sList.addSong(song3);
		assertTrue(sList.getSongs().get(0).equals(song2));
		assertTrue(sList.getSongs().get(1).equals(song3));
	}
	
	@Test
	void testContains() {
		sList.addSong(song2);
		assertTrue(sList.containsSong(song2));
		assertFalse(sList.containsSong(song1));
	}
	
	@Test
	void testToString() {
		sList.addSong(song1);
		sList.addSong(song2);
		assertEquals(sList.toString(), "Playlist : \n"
				+ "Darkness\n"
				+ "Going Home\n");
		sList.setPlaylistName("name");
		String str = "Playlist " + sList.getPlaylistName() + ": \n" +
		song1.getTitle() + "\n" + song2.getTitle() + "\n";
		assertEquals(sList.toString(), "Playlist name: \n"
				+ "Darkness\n"
				+ "Going Home\n");
	}
	

	
	@Test
	void testGetPlaylistName() {
		sList.setPlaylistName("PName");
		assertEquals(sList.getPlaylistName(), "PName");
	}
	
	@Test
	void testEquals() {
	    assertTrue(song1.equals(new Song("Darkness", "Leonard Cohen", "Old Ideas", "Singer/Songwriter", "2012")));
	    assertFalse(song1.equals(song2));
	    assertFalse(song1.equals(song3));
	    sList.addSong(song1);
	    sList.addSong(song2);
	    sList.addSong(song4);
	    assertFalse(song1.equals(null));
	    assertFalse(song1.equals(new SongList()));
	    assertTrue(song1.equals(song1));
	    assertFalse(song4.equals(song5));
	}
	
	@Test
	void testSortedSongsByTitle() {
	    sList.addSong(song1);
	    sList.addSong(song2);
	    sList.addSong(song3);
	    sList.addSong(song4);
	    sList.addSong(song5);
	    ArrayList<Song> sortedSongs = sList.sortedSongsByTitle();
	    assertEquals(song1, sortedSongs.get(0));
	    assertEquals(song2, sortedSongs.get(1));
	    assertEquals(song3, sortedSongs.get(2));
	    assertEquals(song4, sortedSongs.get(3));
	    assertEquals(song5, sortedSongs.get(4));
	}
	
	@Test
	void testSortedSongsByArtist() {
	    sList.addSong(song1);
	    sList.addSong(song2);
	    sList.addSong(song3);
	    sList.addSong(song4);
	    sList.addSong(song5);

	    ArrayList<Song> sortedSongs = sList.sortedSongsByArtist();

	    assertEquals(song1, sortedSongs.get(0));
	    assertEquals(song2, sortedSongs.get(1));
	    assertEquals(song4, sortedSongs.get(2));
	    assertEquals(song3, sortedSongs.get(3));
	    assertEquals(song5, sortedSongs.get(4));
	}


}
