package CA1.persistence;

import CA1.business.Album;
import CA1.business.Rating;
import CA1.business.Song;
import CA1.business.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RatingDaoImplTest {
    private MySQLDao connectionSource = new MySQLDao("database_test.properties");

    /// ADDING SONG METHOD

    /**
     * Test for Adding a rating to the database
     * @throws SQLException if username or songID doesn't exist
     */
    @Test
    void implementRatingSong() throws SQLException {
        System.out.println(" Test for Adding a rating to the database");

        Rating tester = new Rating("Sam",4,5);

        int incorrectResult = -1;

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        RatingDao ratingDao = new RatingDaoImpl(conn);

        int result = ratingDao.implementRatingSong(tester);
        assertNotEquals(incorrectResult, result);

        Rating inserted = ratingDao.findRatingByUsernameAndSongID(tester.getUsername(), tester.getSongID());
        assertNotNull(inserted);

        assertRatingEquals(tester, inserted);


    }

    /**
     * Test for adding a rating to the database but its null
     */
    @Test
    void implementRatingSongButNull() {

        System.out.println("Test for adding a rating to the database but its null");
        Rating tester = null;


        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");


        assertThrows(NullPointerException.class, () -> {

            ratingDao.implementRatingSong(tester);
        });
    }

    /**
     * test for adding a rating but the username doesn't match
     * @throws SQLException if username or songID doesn't exist
     */
    @Test
    void implementRatingSongButUserNameDoesntMatch() throws SQLException {
        System.out.println("test for adding a rating but the username doesnt match");

        Rating tester = new Rating("You",4,5);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        int incorrectResult = -1;
        int result = ratingDao.implementRatingSong(tester);
        assertEquals(incorrectResult, result);

    }

    /**
     * test for adding a rating but the songID doesn't match
     * @throws SQLException if username or songID doesn't exist
     */
    @Test
    void implementRatingSongButSongIDDoesntMatch() throws SQLException {
        System.out.println("test for adding a rating but the songID doesnt match");

        Rating tester = new Rating("Toby",44,5);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        int incorrectResult = -1;
        int result = ratingDao.implementRatingSong(tester);
        assertEquals(incorrectResult, result);

    }



//// GET ALL RATINGS

    /**
     * Test for get all ratings
     */
    @Test
    void getAllRatings() {

        System.out.println("Test for get all ratings ");
        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        List<Rating> expected = generateAllResults();
        List<Rating> result = ratingDao.getAllRatings();

        // test size
        assertEquals(expected.size(),result.size());

        assertEquals(expected,result);

        // test if all elements in list are the same
        for (int i = 0; i < result.size();i++){
            assertRatingEquals(expected.get(i), result.get(i));
        }
    }



    ///TOP RATED SONG METHOD

    /**
     * Test for get the song with the highest rating average
     */
    @Test
    void getTopRatedSong() {

        System.out.println("Test for get the song with the highest rating average");

        Time t1 = Time.valueOf("00:04:34");
        Song expected = new Song(2,"Wesleys Theory",5, 2, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertEquals(expected, result);


    }


    /**
     * Test for get the song with the highest rating average when the expected song isn't the top-rated song
     */
    @Test
    void getTopRatedSongWhenTheExpectedResultIsNotTheTopRatedSong() {

        System.out.println("Test for get the song with the highest rating average when the expected song isn't the top rated song");

        Time t1 = Time.valueOf("00:02:23");
        Song expected = new Song(3,"Meh",4, 3, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);

    }

    /**
     * Test for get the song with the highest rating average when song is null
     */
    @Test
    void getTopRatedSongWhenSongIsNull() {

        System.out.println("Test for get the song with the highest rating average when song is null");


        Song expected = null;

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);
    }



    /// MOST POPULAR SONG METHOD

    /**
     * test for get which song is the most popular song to be rated by users
     */
    @Test
    void getMostPopularSong() {

        System.out.println("test for get which song is the most popular song to be rated by users");

        Time t1 = Time.valueOf("00:04:59");

        Song expected = new Song(1, "Come on", 1, 1, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertEquals(expected, result);

    }

    /**
     * test for get which song is the most popular song to be rated by users when song doesnt match
     */
    @Test
    void getMostPopularSongWhenMostPopularSongDoesntMatch() {

        System.out.println("test for get which song is the most popular song to be rated by users when song doesnt match");

        Time t1 = Time.valueOf("00:02:23");

        Song expected = new Song(3, "Meh", 4, 3, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertNotEquals(expected, result);

    }

    /**
     * test for get which song is the most popular song to be rated by users when song is Null
     */
    @Test
    void getMostPopularSongWhenNull() {

        System.out.println("test for get which song is the most popular song to be rated by users when song is Null");

        Song expected = null;

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertNotEquals(expected, result);

    }


    // GET RATING BY USERNAME AND SONGID METHOD

    /**
     * test get  rating by username and songID
     */
    @Test
    void findRatingByUsernameAndSongID(){

        System.out.println("test get  rating by username and songID");

        Rating expected = new Rating("Toby", 1, 1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Rating result = ratingDao.findRatingByUsernameAndSongID(expected.getUsername(), expected.getSongID());

        assertRatingEquals(expected,result);
    }

    /**
     * test get rating by username and songID when no match
     */
    @Test
    void findRatingByUsernameAndSongIDForNoMatch(){

        System.out.println("test get rating by username and songID when no match");

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Rating result = ratingDao.findRatingByUsernameAndSongID("yyyy", 500);

       assertNull(result);
    }


    /// GET ALL USER RATING FROM USERNAME

    /**
     * Test for get all user ratings from username
     */
    @Test
    void getUserRatingFromUsername(){

        System.out.println("Test for get all user ratings from username");

        Rating r = new Rating("Toby", 1, 1);

        ArrayList<Rating> expected = new ArrayList<>();
        expected.add(new Rating("Toby", 1, 1));
        expected.add(new Rating("Toby", 2, 4.8));
        expected.add(new Rating("Toby", 3, 4));
        expected.add(new Rating("Toby", 5, 3.9));

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        ArrayList<Rating> result = ratingDao.getUserRatingFromUsername(r.getUsername());


        for (int i = 0; i < expected.size();i++){

            assertEquals(expected.get(i), result.get(i));
        }
    }

    /**
     * Test for get all user ratings from username but username doesn't match
     */
    @Test
    void getUserRatingFromUsernameWhenDoesntMatch(){

        System.out.println("Test for get all user ratings from username but username doesnt match ");

        Rating r = new Rating("Toby", 1, 1);

        ArrayList<Rating> expected = new ArrayList<>();
        expected.add(new Rating("Robert", 1, 1));
        expected.add(new Rating("Robert", 2, 4.8));
        expected.add(new Rating("Robert", 3, 4));
        expected.add(new Rating("Robert", 5, 3.9));

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        ArrayList<Rating> result = ratingDao.getUserRatingFromUsername(r.getUsername());


        for (int i = 0; i < expected.size();i++){

            assertNotEquals(expected.get(i), result.get(i));
        }
    }

    /// Lowest rated Song

    /**
     * Test for get the song with the lowest average rating
     */
    @Test
    void getLowestRatedSong() {
        System.out.println("Test for get the song with the lowest average rating");

        Time t1 = Time.valueOf("00:04:59");

        Song expected = new Song(1,"Come on", 1, 1, t1 );

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getLowestRatedSong();

        assertEquals(expected,result);
    }

    /**
     * Test for get the song with the lowest average rating when song doesn't match the expected value
     */
    @Test
    void getLowestRatedSongWhenSongDoesntMatchExpectedValue() {
        System.out.println("Test for get the song with the lowest average rating when song doesnt match the expected value");

        Time t1 = Time.valueOf("00:02:23");

        Song expected = new Song(3, "Meh", 4, 3, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getLowestRatedSong();

        assertNotEquals(expected,result);
    }

    /**
     * Check if two ratings are the same
     * @param r1 is the rating being searched
     * @param r2 is the rating being searched
     */
    private void assertRatingEquals(Rating r1, Rating r2){
        assertEquals(r1.getUsername(),r2.getUsername());
        assertEquals(r1.getSongID(), r2.getSongID());
        assertEquals(r1.getUserRating(), r2.getUserRating());
    }


    /**
     * Adding all ratings to a list
     * @return list of all ratings
     */
    List<Rating> generateAllResults(){
        List<Rating> results = new ArrayList<Rating>();

        Rating r1 = new Rating("Andrew",1,3.2);
        Rating r2 = new Rating("Andrew",4,3.4);
        Rating r3 = new Rating("John",1,5);
        Rating r4 = new Rating("Sam",2,4.6);
        Rating r5 = new Rating("Toby",1,1);
        Rating r6 = new Rating("Toby",2,4.8);
        Rating r7 = new Rating("Toby",3,4);
        Rating r8 = new Rating("Toby",5,3.9);


        results.add(r1);
        results.add(r2);
        results.add(r3);
        results.add(r4);
        results.add(r5);
        results.add(r6);
        results.add(r7);
        results.add(r8);

        return results;
    }


}