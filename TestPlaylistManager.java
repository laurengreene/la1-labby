package music;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestPlaylistManager {
	MusicStore mStore;
	LibraryModel libMod;
	Song song1;
	Song song2;
	Song song3;
	Album album1;
	Album album2;
	Album album3;
	PlaylistManager plistm;

    @BeforeEach
    void setUp() throws FileNotFoundException {
    	 mStore = new MusicStore();
		 libMod = new LibraryModel();
		 song1 = mStore.getStoreSongByTandA("Fight for Your Mind", "Ben Harper");
		 song2 = mStore.getStoreSongByTandA("Lullaby", "Leonard Cohen");
		 song3 = mStore.getStoreSongByTandA("Lullaby", "OneRepublic");
		 libMod.addSongToLib(song1);
		 libMod.addSongToLib(song2);
		 libMod.addSongToLib(song3);
		 album1 = mStore.searchStoreAlbumByArtist("Adele").get(0);
		 album2 = mStore.searchStoreAlbumByArtist("Adele").get(1);
		 album3 = mStore.searchStoreAlbumByArtist("Dolly Parton").get(0);
		 libMod.addAlbumToLib(album1);
		 libMod.createPlaylist("playlist1");
		 plistm = new PlaylistManager(libMod);
    }

    @Test
    void testGetRatedSongs() {
        plistm.setRatingOfSong(song1, 5);
        HashMap<Song, Rating> ratedSongs1 = plistm.getRatedSongs();
        assertEquals(Rating.FIVE, ratedSongs1.get(song1));
    }

    @Test
    void testAddToGenres() {
        plistm.addToGenres(song1);
        plistm.addToGenres(song3);
        assertEquals("Alternative\nRock\n", plistm.getAllGenres());
    }

    @Test
    void testGetPlaylists() {
        plistm.createPlaylist("p");
        assertTrue(plistm.getPlaylists().contains("p"));
    }

    @Test
    void testSortedSongsByRatingThreeThroughFive() {
        plistm.setRatingOfSong(song1, 5);
        plistm.setRatingOfSong(song2, 4);
        plistm.setRatingOfSong(song3, 3);
        String ratings = "No Songs Rated 1\nNo Songs Rated 2\nSongs Rated 3: \n" + 
        song3.toString() + "\nSongs Rated 4: \n" + song2.toString() + "\nSongs Rated 5: \n"
        		+ song1.toString() + "\n";
        assertEquals(ratings, plistm.sortedSongsByRating());
    }
    
    @Test
    void testSortedSongsByRatingOneThroughTwo() {
    	plistm.setRatingOfSong(song1, 1);
        plistm.setRatingOfSong(song2, 2);
        String ratings = "Songs Rated 1: \n" + song1.toString() + "\nSongs Rated 2: \n" + 
        song2.toString() + "\nNo Songs Rated 3\nNo Songs Rated 4\nNo Songs Rated 5\n";
        assertEquals(ratings, plistm.sortedSongsByRating());
    }

    @Test
    void testShufflePlaylist() {
        plistm.createPlaylist("s");
        String result = plistm.shufflePlaylist("s");
        assertEquals("Playlist Shuffled", result);
        String none = plistm.shufflePlaylist("no playlist");
        assertEquals("Playlist Not Found", none);
    }
    
    @Test
    void testGetSongsByGenre() {
    	SongList altGenre = plistm.getSongsByGenre("alternative");
    	Song song = altGenre.getSongs().getFirst();
    	assertEquals(song, song1);
    }

    @Test
    void testGetPlaylist() {
        plistm.createPlaylist("My Playlist");
        assertEquals("Not Found", plistm.getPlaylist("not a playlist"));
        assertTrue(plistm.getPlaylist("My Playlist").contains("My Playlist"));
    }
    
    @Test
    void testAddSongToPlaylist() {
    	assertEquals("Playlist not found", plistm.addSongToPlaylist("not a playlist", song1));
    	plistm.createPlaylist("playlist1");
    	plistm.createPlaylist("playlist");
    	assertEquals("Song added", plistm.addSongToPlaylist("playlist", song1));
    }
    
    @Test
    void testRemoveSongFromPlaylist() {
    	plistm.createPlaylist("playlist1");
    	assertEquals(plistm.removeSongFromPlaylist("not a playlist", song1), "Playlist not found");
    	assertEquals(plistm.removeSongFromPlaylist("playlist1", song1), "Song Not Found");
    	plistm.addSongToPlaylist("playlist1", song1);
    	assertEquals(plistm.removeSongFromPlaylist("playlist1", song1), "Song Removed");
    }
    
    @Test
    void testMostPlayed() {
        plistm.playSong(song1);
        plistm.playSong(song1);
        plistm.playSong(song2);
        ArrayList<Song> mostPlayed = plistm.mostPlayed();
        assertEquals(song1, mostPlayed.get(0));
        assertEquals(song2, mostPlayed.get(1));
    }
    
    @Test
    void testGetRecents() {
    	plistm.playSong(song1);
        plistm.playSong(song2);
        ArrayList<Song> recents = plistm.getRecents();
        assertEquals(song1, recents.get(0));
        assertEquals(song2, recents.get(1));
    }
   
    @Test
    void testGetUserPlaylists() {
        plistm.createPlaylist("playlist1");
        plistm.createPlaylist("playlist2");
        plistm.addSongToPlaylist("playlist2", song1);
        ArrayList<SongList> playlists = plistm.getUserPlaylists();
        assertEquals("playlist1", playlists.get(0).getPlaylistName());
        assertEquals("playlist2", playlists.get(1).getPlaylistName());
    }
    
    @Test
    void testGetFavoritePlaylist() {
    	plistm.setRatingOfSong(song1, 5);
    	plistm.setRatingOfSong(song2, 1);
        SongList favorites1 = plistm.getFavoritePlaylist();
        ArrayList<Song> favorites2 = favorites1.getSongs();
        assertEquals(song1, favorites2.get(0));
    }
    
    @Test
    void testGetTopRatedSongs() {
        // Set ratings for songs
        plistm.setRatingOfSong(song1, 5);
        plistm.setRatingOfSong(song2, 3); 
        plistm.setRatingOfSong(song3, 4);
        SongList topRatedPlaylist = plistm.getTopRatedSongs();
        assertTrue(topRatedPlaylist.getSongs().contains(song1));
        assertTrue(topRatedPlaylist.getSongs().contains(song3));
        assertFalse(topRatedPlaylist.getSongs().contains(song2));
    }
    
    
    
}