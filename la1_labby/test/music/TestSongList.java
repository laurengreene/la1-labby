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
		assertTrue(sList.getSongByTitle("Darkness").equals(song1.toString()));
		assertEquals(sList.getSongByTitle("Fake Song Title"), "Not found");
	}
	
	@Test
	void testGetSongByArtist() {
		sList.addSong(song1);
		sList.addSong(song2);
		sList.addSong(song3);
		assertTrue(sList.getSongByArtist("Leonard Cohen").equals(song1.toString()+"\n"
		+ song2.toString()));
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
	
	@Test
	void testRemoveSong() {
		sList.addSong(song3);
		sList.removeSong(song1);
		assertEquals(sList.getSongs().size(), 1);
		sList.removeSong(song3);
		assertFalse(sList.getSongs().contains(song3));
	}
	
	@Test
	void testGetPlaylistName() {
		sList.setPlaylistName("PName");
		assertEquals(sList.getPlaylistName(), "PName");
	}

}
