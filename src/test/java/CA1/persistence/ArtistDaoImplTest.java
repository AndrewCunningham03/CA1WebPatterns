package CA1.persistence;

import CA1.business.Album;
import CA1.business.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistDaoImplTest {

    /**
     * Get all artist
     */
    @Test
    void getAllArtist() {
        System.out.println("Get all the artist");

        ArtistDao artistDao = new ArtistDaoImpl("database.properties");

        List<Artist> expected = generateAllResults();
        List<Artist> result  = artistDao.getAllArtist();

        // test size

        assertEquals(5,result.size());

        // test if everything in both list is the same

        for (int i = 0; i < result.size();i++){

            assertEquals(expected.get(i), result.get(i));
        }

    }


    void assertArtistEquals(Artist expected, Artist result){
        assertEquals(expected.getArtistID(), result.getArtistID());
        assertEquals(expected.getArtistName(),result.getArtistName());
        assertEquals(expected.getGenre(),result.getGenre());
        assertEquals(expected.getHometown(), result.getHometown());
        assertEquals(expected.getDateOfBirth(), result.getDateOfBirth());
    }
    List<Artist> generateAllResults(){

        List<Artist> result = new ArrayList<>();

        Artist a1 = new Artist(1, "Jimi Hendrix", "Rock", "Seattle Washington", new Date("1942/09/27"));
        Artist a2 = new Artist(2, "Kendrick Lamar", "Hip Hop", "Compton California", new Date("1987/06/17"));
        Artist a3 = new Artist(3, "Playboi Carti", "Hip Hop", "Riverdale Georgia", new Date("1995/09/13"));
        Artist a4 = new Artist(4,"Erykah Badu","RnB/Soul", "Dallas Texas", new Date("1971/02/26"));
        Artist a5 = new Artist(5, "Prince","RnB/Soul","Minneapolis, Minnesota",new Date("1958/06/07"));

        result.add(a1);
       result.add(a2);
        result.add(a3);
        result.add(a4);
        result.add(a5);

        return result;

    }
}