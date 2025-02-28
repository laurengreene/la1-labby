package music;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMusicStore {	
	
	private MusicStore mStore;
	
	@BeforeEach
	void setUp() throws FileNotFoundException {
		mStore = new MusicStore();
	}
	
	@Test
	void testGetStoreSongByTandA() {
		// get store song from title and artist, returns a string
		Song song1 = new Song("So Far Away", "Carol King", "Tapestry");
		assertTrue(mStore.getStoreSongByTandA("so far away", "carol king").equals(song1));
	}
	
	@Test
	void testSearchStoreSongByTitle() {
		assertEquals(mStore.searchStoreSongByTitle("So Far Away").get(0).toString(), "Song: So Far Away; Artist: "
				+ "Carol King; Album: Tapestry");
		assertEquals(mStore.searchStoreSongByTitle("polITIK").get(0).toString(), "Song: Politik; "
				+ "Artist: Coldplay; Album: A Rush of Blood to the Head");
	}
	
	@Test
	void testSearchStoreSongByArtistNotFound(){
		assertEquals(mStore.searchStoreSongByArtist("random person").size(), 0);	
	}
	
	@Test
	void testSearchStoreByArtistTest() {
		assertEquals(mStore.searchStoreSongByArtist("the HEAVY").get(0).toString(),
				"Song: Heavy for You; Artist: The Heavy; Album: Sons");
	}
	
	@Test
	void searchStoreAlbumByTitleTest() {
		assertEquals(mStore.searchStoreAlbumByTitle("19").get(0).toString(),"Album: 19; Artist: Adele; Year: 2008; Genre: Pop; Songs: \n"
				+ "Daydreamer\n"
				+ "Best for Last\n"
				+ "Chasing Pavements\n"
				+ "Cold Shoulder\n"
				+ "Crazy for You\n"
				+ "Melt My Heart to Stone\n"
				+ "First Love\n"
				+ "Right as Rain\n"
				+ "Make You Feel My Love\n"
				+ "My Same\n"
				+ "Tired\n"
				+ "Hometown Glory");
		assertEquals(mStore.searchStoreAlbumByTitle("nothing").size(), 0);
	}
	
	@Test
	void searchStoreAlbumByArtistTest() {
		assertEquals(mStore.searchStoreAlbumByArtist("Carol King").get(0).toString(), "Album: Tapestry; Artist: Carol King; Year: 1971; Genre: Rock; Songs: \n"
				+ "I Feel The Earth Move\n"
				+ "So Far Away\n"
				+ "Home Again\n"
				+ "Beautiful\n"
				+ "Way Over Yonder\n"
				+ "You've Got A Friend\n"
				+ "Where You Lead\n"
				+ "Will You Love Me Tomorrow?\n"
				+ "Tapestry\n"
				+ "(You Make Me Feel Like) A Natural Woman");
		assertEquals(mStore.searchStoreAlbumByArtist("ADELE").size(), 2);
		assertEquals(mStore.searchStoreAlbumByArtist("nobody").size(), 0);
	}

}
