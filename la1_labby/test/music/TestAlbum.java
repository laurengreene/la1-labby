package music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAlbum {
	Album album = new Album("Adele", "25", "Pop", "2015");
	Song s = new Song("Chasing Pavements", album.getArtist(),
			album.getTitle());

	@Test
	void testGetTitle() {
		assertEquals(album.getTitle(), "25");
	}
	
	@Test
	void testGetArtist() {
		assertEquals(album.getArtist(), "Adele");
	}
	
	@Test
	void testGetYear() {
		assertEquals(album.getYear(), "2015");
	}
	
	@Test
	void testGetGenre() {
		assertEquals(album.getGenre(), "Pop");
	}
	
	@Test
	void testAddToSongList() {
		album.addToSongList(s);
		assertTrue(album.getSongList().containsSong(s));
	}

}
