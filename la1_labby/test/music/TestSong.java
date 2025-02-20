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
		String result = "Song: " + s.getTitle() + "\nArtist: " + s.getArtist()
		+ "\nAlbum: " + s.getAlbumTitle();
		assertEquals(result, s.toString());
	}

}
