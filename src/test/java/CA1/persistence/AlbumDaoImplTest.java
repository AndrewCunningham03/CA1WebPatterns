package CA1.persistence;

import CA1.business.Album;
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
class AlbumDaoImplTest {

    /**
     * Test for Viewing all albums from a particular artist
     */
    @Test
    void viewAllAlbumsFromArtist() {

        System.out.println("Test for Viewing all albums from a particular artist");
        System.out.println("");
        AlbumDao albumDao = new AlbumDaoImpl("database_test.properties");

        List<Album> expected = generateAllResults();
        List<Album> result = albumDao.viewAllAlbumsFromArtist(1);

        // test size
        assertEquals(expected.size(),result.size());


        // test if all elements in list are the same
        for (int i = 0; i < result.size();i++){
            assertAlbumEquals(expected.get(i), result.get(i));
        }

    }


    void assertAlbumEquals(Album expected, Album result){
        assertEquals(expected.getAlbumID(), result.getAlbumID());
        assertEquals(expected.getAlbumName(), result.getAlbumName());
        assertEquals(expected.getArtistID(), result.getArtistID());
        assertEquals(expected.getNumberOfSongs(), result.getNumberOfSongs());
        assertEquals(expected.getReleaseDate(), result.getReleaseDate());
        assertEquals(expected.getAlbumLength(), result.getAlbumLength());
    }


    List<Album> generateAllResults(){

        List<Album> results = new ArrayList<>();

        Time t1 = Time.valueOf("01:28:00");
        Album a1 = new Album(1,"Electric Ladyland", 1, 16,  new Date("1968/10/16"), t1 );

        results.add(a1);

        return results;

    }
}