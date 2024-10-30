package CA1.persistence;

import CA1.business.Album;
import CA1.business.Artist;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Toby
 * @author Andrew
 */
class AlbumDaoImplTest {

    /**
     * Test for Viewing all albums from a particular artist
     */
    @Test
    void viewAllAlbumsFromArtist() {

        System.out.println("Test for Viewing all albums from a particular artist");
        System.out.println("");
        AlbumDao albumDao = new AlbumDaoImpl("database_test.properties");


        Time t1 = Time.valueOf("01:28:00");
        List<Album> expected = new ArrayList<>();
        expected.add(new Album(1,"Electric Ladyland", 1, 16,  new Date("1968/10/16"), t1));


        List<Album> result = albumDao.viewAllAlbumsFromArtist(1);

        // test size
        assertEquals(expected.size(),result.size());


        // test if all elements in list are the same
        for (int i = 0; i < result.size();i++){
            assertAlbumEquals(expected.get(i), result.get(i));
        }

    }


    /**
     * Test for Viewing all albums from a particular artist but no match
     */
    @Test
    void viewAllAlbumsFromArtistButNoMatch() {

        System.out.println("Test for Viewing all albums from a particular artist but no match");
        System.out.println("");
        AlbumDao albumDao = new AlbumDaoImpl("database_test.properties");


        Time t1 = Time.valueOf("01:28:00");
        List<Album> expected = new ArrayList<>();
        expected.add(new Album(1,"Electric Ladyland", 1, 16,  new Date("1968/10/16"), t1));


        List<Album> result = albumDao.viewAllAlbumsFromArtist(400);

        // test size
       assertNotEquals(expected.size(),result.size());

        // test If List are the same
        assertNotEquals(expected, result);

    }

    /**
     * Test for find an Album by its ID
     */
    @Test
    void findAlbumById() {
        System.out.println("Test for for find Album by ID");

        Time t1 = Time.valueOf("01:03:00");
        Album expected = new Album(4,"Whole Lotta Red", 3, 24, new Date("2020/12/25"), t1);


        AlbumDao albumDao = new AlbumDaoImpl("database_test.properties");

        Album result = albumDao.findAlbumById(expected.getAlbumID());

        assertEquals(expected, result);
    }

    /**
     * Test for find Album by ID but no match of its ID
     */
    @Test
    void findAlbumByIdButNoMatch() {
        System.out.println("Test for for find Album by ID but no match of its ID");

        AlbumDao albumDao = new AlbumDaoImpl("database_test.properties");

        Album result = albumDao.findAlbumById(400);

        assertNull(result);
    }


    /**
     * Get all the albums
     */
    @Test
    void getAllAlbums() {

        System.out.println("test for Get all the albums");

        AlbumDao albumDao = new AlbumDaoImpl("database_test.properties");

        List<Album> expected = generateAllResults();
        List<Album> result  = albumDao.getAllAlbums();

        // test size

        assertEquals(expected.size(),result.size());

        // test if everything in both list is the same

        for (int i = 0; i < result.size();i++){

            assertAlbumEquals(expected.get(i), result.get(i));
        }
    }

    /**
     * Checking if two albums have the same values in it
     * @param expected is the album being searched
     * @param result is the album being searched
     */
    void assertAlbumEquals(Album expected, Album result){
        assertEquals(expected.getAlbumID(), result.getAlbumID());
        assertEquals(expected.getAlbumName(), result.getAlbumName());
        assertEquals(expected.getArtistID(), result.getArtistID());
        assertEquals(expected.getNumberOfSongs(), result.getNumberOfSongs());
        assertEquals(expected.getReleaseDate(), result.getReleaseDate());
        assertEquals(expected.getAlbumLength(), result.getAlbumLength());
    }


    /**
     * Adding all albums to a list
     * @return list of all available albums
     */
    List<Album> generateAllResults(){

        List<Album> results = new ArrayList<>();

        Time t1 = Time.valueOf("01:28:00");
        Album a1 = new Album(1,"Electric Ladyland", 1, 16,  new Date("1968/10/16"), t1 );

        Time t2 = Time.valueOf("01:20:00");
        Album a2 = new Album(2,"Sign O The Times", 5, 16,  new Date("1987/03/30"), t2 );

        Time t3 = Time.valueOf("01:16:00");
        Album a3 = new Album(3,"Mamas Gun", 4, 16,  new Date("2000/09/21"), t3 );

        Time t4 = Time.valueOf("01:03:00");
        Album a4 = new Album(4,"Whole Lotta Red", 3, 24,  new Date("2020/12/25"), t4 );

        Time t5 = Time.valueOf("01:19:00");
        Album a5 = new Album(5,"To Pimp A Butterfly", 2, 16,  new Date("2015/03/15"), t5 );

        results.add(a1);
        results.add(a2);
        results.add(a3);
        results.add(a4);
        results.add(a5);

        return results;

    }


}