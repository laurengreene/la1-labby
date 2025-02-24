package music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSongList {
	SongList sList = new SongList();
	Song song1 = new Song("Darkness", "Leonard Cohen", "Old Ideas");
	Song song2 = new Song("Going Home", "Leonard Cohen", "Old Ideas");
	Song song3 = new Song("Little Lion Man", "Mumford & Sons", "Sigh No More");
	
	@Test
	void testGetSongByTitle() {
		sList.addSong(song1);
		sList.addSong(song3);
		assertTrue(sList.getSongByTitle("Darkness").get(0).equals(song1));
	}
	
	@Test
	void testGetSongByArtist() {
		sList.addSong(song1);
		sList.addSong(song2);
		sList.addSong(song3);
		assertTrue(sList.getSongByArtist("Leonard Cohen").get(0).equals(song1));
		assertTrue(sList.getSongByArtist("Leonard Cohen").get(1).equals(song2));
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
		String str = song1.getTitle() + "\n" + song2.getTitle() + "\n";
		assertEquals(sList.toString(), str);
	}

}
