package music;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAlbum {
	private Album album;
	private Song s1;
	private ArrayList<Song> slist;
	
	@BeforeEach
	void SetUp() {
		slist = new ArrayList<Song>();
		s1 = new Song("Rolling in the Deep", "Adele", "21");
		slist.add(s1);
		album = new Album("Adele", "21", "Pop", "2011", slist);
	}


	@Test
	void testGetTitle() {
		assertEquals(album.getTitle(), "21");
	}
	
	@Test
	void testGetArtist() {
		assertEquals(album.getArtist(), "Adele");
	}
	
	@Test
	void testGetYear() {
		assertEquals(album.getYear(), "2011");
	}
	
	@Test
	void testGetGenre() {
		assertEquals(album.getGenre(), "Pop");
	}
	
//	@Test
//	void getSongList() {
//		assertTrue(album.getSongList().equals(slist));
//	}
	
	@Test
	void testMakeCopyAlbum() {
		album.addToSongList(s1);
		Album cAlbum = album.makeCopyAlbum();
		assertNotSame(album, cAlbum);
		assertTrue(album.equals(cAlbum));
	}
	
	@Test
	void testEquals() {
		Album eAlbum = new Album("Adele", "21", "Pop", "2011", slist);
		assertTrue(album.equals(eAlbum));
		
	}
	
	@Test
	void testNotEquals() {
		Album difYearAlbum = new Album("Adele", "21", "Pop", "2014", slist);
		assertFalse(album.equals(difYearAlbum));
		Album difGenreAlbum = new Album("Adele", "21", "Rap", "2011", slist);
		assertFalse(album.equals(difGenreAlbum));
		Album difTitleAlbum = new Album("Adele", "26", "Pop", "2011", slist);
		assertFalse(album.equals(difTitleAlbum));
		Album difArtistAlbum = new Album("Coldplay", "21", "Pop", "2011", slist);
		assertFalse(album.equals(difArtistAlbum));
	}
	
	@Test
	void testToString() {
		String str = "Album: 21; Artist: Adele; Year: 2011; Genre: Pop; Songs: \n"
				+ "Rolling in the Deep";
		assertEquals(str, album.toString());
	}
}
