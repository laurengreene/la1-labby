package music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSong {
	Song s = new Song("Hello", "Adele", "25");

	@Test
	void testGetTitle() {
		assertEquals("Hello", s.getTitle());
	}
	
	@Test
	void testGetArtist() {
		assertEquals("Adele", s.getArtist());
	}
	
	@Test
	void testGetAlbumTitle() {
		assertEquals("25", s.getAlbumTitle());
	}
	
	@Test
	void testToString() {
		String result = "Song: " + s.getTitle() + "; Artist: " + s.getArtist()
		+ "; Album: " + s.getAlbumTitle();
		assertEquals(result, s.toString());
	}
	
	@Test
	void testEquals() {
		Song e = new Song("Hello", "Adele", "25");
		assertTrue(s.equals(e));
	}
	
	@Test
	void testNotEquals() {
		Song difTitle = new Song("Chasing Pavements", "Adele", "25");
		assertFalse(s.equals(difTitle));
		Song difArtist = new Song("Hello", "Wallows", "25");
		assertFalse(s.equals(difArtist));
		Song difAlbum = new Song("Hello", "Adele", "20");
		assertFalse(s.equals(difAlbum));
	}

}
