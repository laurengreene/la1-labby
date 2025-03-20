package music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSong {
	Song s = new Song("Daydreamer", "Adele", "19", "Pop", "2008");

	@Test
	void testGetTitle() {
		assertEquals("Daydreamer", s.getTitle());
	}
	
	@Test
	void testGetYear() {
		assertEquals("2008", s.getYear());
	}
	
	@Test
	void testGetArtist() {
		assertEquals("Adele", s.getArtist());
	}
	
	@Test
	void testGetAlbumTitle() {
		assertEquals("19", s.getAlbumTitle());
	}
	
	@Test
	void testGetGenre() {
		assertEquals("Pop", s.getGenre());
	}
	
	
	@Test
	void testToString() {
		String result = s.getTitle() + "," + s.getArtist() + "," + 
	s.getAlbumTitle() + "," + s.getGenre() + "," + s.getYear();
		assertEquals(result, s.toString());
	}
	
	@Test
	void testEquals() {
		Song e = new Song("Daydreamer", "Adele", "19", "Pop", "2008");
		assertTrue(s.equals(e));
	}
	
	@Test
	void testNotEquals() {
		assertFalse(s.equals(null));
		assertFalse(s.equals(new SongList()));
		Song difTitle = new Song("Chasing Pavements", "Adele", "19", "Pop", "2008");
		assertFalse(s.equals(difTitle));
		Song difArtist = new Song("Daydreamer", "Wallows", "19", "Pop", "2008");
		assertFalse(s.equals(difArtist));
		Song difAlbum = new Song("Daydreamer", "Adele", "20", "Pop", "2008");
		assertFalse(s.equals(difAlbum));
		Song difGenre = new Song("Daydreamer", "Adele", "19", "Rock", "2008");
		assertFalse(s.equals(difGenre));
		Song difYear = new Song("Daydreamer", "Adele", "19", "Pop", "2009");
		assertFalse(s.equals(difYear));
	}

}
