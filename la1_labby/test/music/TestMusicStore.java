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
	void searchStoreSongByTitleTest() {
		assertEquals(mStore.searchStoreSongByTitle("Hold On"), "Song: "
				+ "Hold On; Artist: Alabama Shakes; Album: Boys & Girls");
	}
	
	@Test
	void searchStoreSongByTitleCaseTest() {
		assertEquals(mStore.searchStoreSongByTitle("polITIK"), "Song: Politik; "
				+ "Artist: Coldplay; Album: A Rush of Blood to the Head");
	}
	
	@Test
	void searchStoreSongByArtistNotFoundTest(){
		assertEquals(mStore.searchStoreSongByArtist("random person"), "Not found");
		
	}
	
	@Test
	void searchStoreByArtistTest() {
		assertEquals(mStore.searchStoreSongByArtist("the HEAVY"), "Song: Heavy for You; Artist: The Heavy; Album: Sons\n"
				+ "Song: The Thief; Artist: The Heavy; Album: Sons\n"
				+ "Song: Better as One; Artist: The Heavy; Album: Sons\n"
				+ "Song: Fire; Artist: The Heavy; Album: Sons\n"
				+ "Song: Fighting for the Same Thing; Artist: The Heavy; Album: Sons\n"
				+ "Song: Hurt Interlude; Artist: The Heavy; Album: Sons\n"
				+ "Song: Put the Hurt on Me; Artist: The Heavy; Album: Sons\n"
				+ "Song: Simple Things; Artist: The Heavy; Album: Sons\n"
				+ "Song: A Whole Lot of Love; Artist: The Heavy; Album: Sons\n"
				+ "Song: What Don't Kill You; Artist: The Heavy; Album: Sons\n"
				+ "Song: Burn Bright; Artist: The Heavy; Album: Sons");
	}
	
	@Test
	void searchStoreAlbumByTitleTest() {
		assertEquals(mStore.searchStoreAlbumByTitle("19"),"Album: 19; Artist: Adele; Year: 2008; Genre: Pop; Songs: \n"
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
	}
	
	@Test
	void searchStoreAlbumByArtistTest() {
		assertEquals(mStore.searchStoreAlbumByArtist("Carol King"), "Album: Tapestry; Artist: Carol King; Year: 1971; Genre: Rock; Songs: \n"
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
	}

}
