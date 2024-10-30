package CA1.persistence;

import CA1.business.Song;
import org.junit.jupiter.api.Test;

import java.awt.font.FontRenderContext;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Toby
 * @author Andrew
 */
class SongDaoImplTest {


    /**
     * Test for get the Song by its ID
     */
    @Test
    void findSongById() {
        System.out.println("Test for get the Song by its ID");

        Time t1 = Time.valueOf("00:02:23");
        Song expected = new Song(3, "Meh",4,3, t1);

        SongDao songDao = new SongDaoImpl("database_test.properties");

        Song result = songDao.findSongById(expected.getSongID());

        assertEquals(expected, result);
    }

    /**
     * Test for get the Song by its ID but no match
     */
    @Test
    void findSongByIdButNoMatch() {
        System.out.println("Test for get the Song by its ID but no match");


        SongDao songDao = new SongDaoImpl("database_test.properties");

        Song result = songDao.findSongById(400);

        assertNull(result);
    }


    /**
     * test for get song by its Title
     */
    @Test
    void getSongByTitle() {

        System.out.println("test for get song by its Title");

        ArrayList<Song> expected = new ArrayList<>();

        Time t1 = Time.valueOf("00:02:23");
        Song s1 = new Song(3, "Meh",4,3, t1);
        expected.add(s1);

        SongDao songDao = new SongDaoImpl("database_test.properties");

        ArrayList<Song> result = songDao.getSongByTitle(s1.getSongName());

        /// checking arraylist are the same

        for (int i = 0; i < expected.size();i++){

            assertEquals(expected, result);
        }


        // checking size is the same

        assertEquals(expected.size(), result.size());

    }


    /**
     * test for get song by its Title but no match
     */
    @Test
    void getSongByTitleButNoMatch() {

        System.out.println("test for get song by its Title but no match");

        ArrayList<Song> expected = new ArrayList<>();

        Time t1 = Time.valueOf("00:02:23");
        Song s1 = new Song(3, "Meh",4,3, t1);
        expected.add(s1);

        SongDao songDao = new SongDaoImpl("database_test.properties");

        ArrayList<Song> result = songDao.getSongByTitle("House");

        /// checking arraylist are the same

        for (int i = 0; i < expected.size();i++){

            assertNotEquals(expected, result);
        }


        // checking size is the same

        assertNotEquals(expected.size(), result.size());

    }

    /**
     * Get all songs
     */
    @Test
    void getAllSongs() {

        System.out.println("test for getting all songs");

        ArrayList<Song> expected = new ArrayList<>();
        Time t1 = Time.valueOf("00:04:59");
        expected.add(new Song(1,"Come on",1,1,t1));
        Time t2 = Time.valueOf("00:04:34");
        expected.add(new Song(2,"Wesleys Theory",5,2,t2));
        Time t3 = Time.valueOf("00:02:23");
        expected.add(new Song(3,"Meh",4,3,t3));
        Time t4 = Time.valueOf("00:03:32");
        expected.add(new Song(4,"Housequake",2,5,t4));
        Time t5 = Time.valueOf("00:05:23");
        expected.add(new Song(5,"A.D.2000",3,4,t5));


        SongDao songDao = new SongDaoImpl("database_test.properties");

        ArrayList<Song> result = songDao.getAllSongs();

        /// checking arraylist are the same

        for (int i = 0; i < expected.size();i++){

            assertEquals(expected.get(i), result.get(i));
        }


        // checking size is the same

        assertEquals(expected.size(), result.size());

    }

    /**
     * Test for retrieving all songs by a specific artist.
     */
    @Test
    void getSongByArtist() {
        System.out.println("Test for getting songs by a specific artist ID");

        ArrayList<Song> expected = new ArrayList<>();

        Time t1 = Time.valueOf("00:04:59");
        expected.add(new Song(1,"Come on",1,1,t1));


        SongDao songDao = new SongDaoImpl("database_test.properties");
        ArrayList<Song> result = songDao.getSongByArtist(1);

        /// checking arraylist are the same
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }

        // checking size is the same
        assertEquals(expected.size(), result.size());
    }
    /**
     * Test for retrieving all songs by a specific artist that doesn't exist.
     */
    @Test
    void getSongByArtistDoesntExist() {
        System.out.println("Test for getting songs by a specific artist ID that doesn't exist");

        ArrayList<Song> expected = new ArrayList<>();


        SongDao songDao = new SongDaoImpl("database_test.properties");
        ArrayList<Song> result = songDao.getSongByArtist(123);

        /// checking arraylist are the same
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }

        // checking size is the same
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test for retrieving all songs by a specific album.
     */
    @Test
    void getSongByAlbum() {
        System.out.println("Test for getting songs by a specific album ID");

        ArrayList<Song> expected = new ArrayList<>();

        Time t1 = Time.valueOf("00:04:59");
        expected.add(new Song(1,"Come on",1,1,t1));


        SongDao songDao = new SongDaoImpl("database_test.properties");
        ArrayList<Song> result = songDao.getSongByAlbum(1);

        /// checking arraylist are the same
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }

        // checking size is the same
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test for retrieving all songs by a specific album that doesn't exist.
     */
    @Test
    void getSongByAlbumDoesntExist() {
        System.out.println("Test for getting songs by a specific album ID that doesn't exist");

        ArrayList<Song> expected = new ArrayList<>();


        SongDao songDao = new SongDaoImpl("database_test.properties");
        ArrayList<Song> result = songDao.getSongByAlbum(123);

        /// checking arraylist are the same
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }

        // checking size is the same
        assertEquals(expected.size(), result.size());
    }

}