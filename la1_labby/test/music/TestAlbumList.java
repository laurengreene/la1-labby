package music;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestAlbumList {
	AlbumList aList = new AlbumList();
	ArrayList<Song> songList = new ArrayList<Song>();
	Album album1 = new Album("Mumford & Sons", "Sigh No More", "Alternative", "2009", songList);
	Album album2 = new Album("Mumford & Sons", "Babel", "Alternative", "2012", songList);
	Album album3 = new Album("One Republic", "Waking Up", "Rock", "2009", songList);

	@Test
	void testAddAlbum() {
		assertEquals(aList.toString().length(),0);
		aList.addAlbum(album1);
		assertTrue(aList.toString().length() > 0);
	}
	
	@Test
	void testGetAlbumByTitleAndArtist() {
		// make a list of albums
		aList.addAlbum(album1);
		assertEquals(album1, aList.getAlbumByTitleAndArtist("Sigh No More", "Mumford & Sons"));
		assertNotEquals(null, aList.getAlbumByTitleAndArtist("Sigh No More", "Mumford & Sons"));
	}
	
	@Test
	void testGetAlbumByTitle() {
		assertEquals(aList.getAlbumByTitle("Sigh No More"), "Not found");
		aList.addAlbum(album1);
		aList.addAlbum(album3);
		assertEquals(aList.getAlbumByTitle("Sigh No More"), album1.toString());
	}
	
	@Test
	void testGetAlbumObjectByTitle() {
		aList.addAlbum(album1);
		assertEquals(album1, aList.getAlbumObjectByTitle("Sigh No More"));
	}
	
	@Test
	void testGetAlbumObjectsByTitle() {
		aList.addAlbum(album1);
		assertTrue(aList.getAlbumObjectsByTitle("Sigh No More").get(0).equals(album1));
		aList.addAlbum(album2);
		assertTrue(aList.getAlbumObjectsByTitle("SIGH NO MORE").get(0).equals(album1));
	}
	
	@Test
	void testGetAlbumByArtist() {
		aList.addAlbum(album1);
		aList.addAlbum(album2);
		assertEquals(aList.getAlbumByArtist("Mumford & Sons"), album1.toString() + "\n\n" + album2.toString());
	}
	
	@Test
	void testGetAlbumObjectsByArtist() {
		aList.addAlbum(album1);
		assertTrue(aList.getAlbumObjectsByArtist("Mumford & Sons").get(0).equals(album1));
		aList.addAlbum(album2);
		aList.addAlbum(album3);
		assertEquals(aList.getAlbumObjectsByTitle("nothing").size(), 0);
		assertTrue(aList.getAlbumObjectsByArtist("Mumford & Sons").size() == 2);
	}
	
	@Test
	void testAlbumInList() {
		assertFalse(aList.albumInList(album1));
		aList.addAlbum(album1);
		assertTrue(aList.albumInList(album1));
	}
	
	@Test
	void testRemoveAlbum() {
		aList.addAlbum(album1);
		assertTrue(aList.toString().length() > 0);
		aList.removeAlbum(album1);
		assertTrue(aList.toString().length() == 0);
	}
	
	@Test
	void testToString() {
		aList.addAlbum(album1);
		aList.addAlbum(album3);
		String str = album1.getTitle() + "\n" + album3.getTitle() + "\n";
		assertTrue(aList.toString().endsWith(str));
	}

}
