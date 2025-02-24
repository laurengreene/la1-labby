package music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAlbumList {
	AlbumList aList = new AlbumList();
	Album album1 = new Album("Mumford & Sons", "Sigh No More", "Alternative", "2009");
	Album album2 = new Album("Mumford & Sons", "Babel", "Alternative", "2012");
	Album album3 = new Album("One Republic", "Waking Up", "Rock", "2009");

	@Test
	void testGetAlbumByTitle() {
		aList.addAlbum(album1);
		aList.addAlbum(album3);
		assertTrue(aList.getAlbumByTitle("Sigh No More").get(0).equals(album1));
	}
	
	@Test
	void testGetAlbumByArtist() {
		aList.addAlbum(album1);
		aList.addAlbum(album2);
		aList.addAlbum(album3);
		assertTrue(aList.getAlbumByArtist("Mumford & Sons").get(0).equals(album1));
		assertTrue(aList.getAlbumByArtist("Mumford & Sons").get(1).equals(album2));
	}
	
	@Test
	void testToString() {
		aList.addAlbum(album1);
		aList.addAlbum(album3);
		String str = album1.getTitle() + "\n" + album3.getTitle() + "\n";
		assertTrue(aList.toString().endsWith(str));
	}

}
