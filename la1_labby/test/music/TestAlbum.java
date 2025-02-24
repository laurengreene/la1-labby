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
		assertTrue(album.getSongList().get(0).equals(s));
	}
	
	@Test
	void testMakeCopyAlbum() {
		album.addToSongList(s);
		Album cAlbum = album.makeCopyAlbum();
		assertNotSame(album, cAlbum);
		assertTrue(album.equals(cAlbum));
	}
	
	@Test
	void testEquals() {
		Album eAlbum = new Album("Adele", "25", "Pop", "2015");
		assertTrue(album.equals(eAlbum));
		
	}
	@Test
	void testNotEquals() {
		Album difYearAlbum = new Album("Adele", "25", "Pop", "2014");
		assertFalse(album.equals(difYearAlbum));
		Album difGenreAlbum = new Album("Adele", "25", "Rap", "2015");
		assertFalse(album.equals(difGenreAlbum));
		Album difTitleAlbum = new Album("Adele", "26", "Pop", "2015");
		assertFalse(album.equals(difTitleAlbum));
		Album difArtistAlbum = new Album("Coldplay", "25", "Pop", "2015");
		assertFalse(album.equals(difArtistAlbum));
	}
	
	@Test
	void testToString() {
		album.addToSongList(s);
		String str = "Album: " + album.getTitle() + "\nArtist: " + 
				album.getArtist() + "\nYear: " + album.getYear() + "\nGenre: " +
				album.getGenre() + "\nSongs: \n" + s.getTitle();
		assertEquals(str, album.toString());
	}
}
