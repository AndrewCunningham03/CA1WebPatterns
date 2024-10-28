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
}