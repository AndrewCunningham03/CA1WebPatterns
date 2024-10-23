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

    @Test
    void rateSong() {
    }



    /// ADDING SONG METHOD
    @Test
    void implementRatingSong() throws SQLException {
        System.out.println("Adding a rating to the database");

        Rating tester = new Rating("Sam",4,5);

        boolean incorrectResult = false;

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        RatingDao ratingDao = new RatingDaoImpl(conn);

        boolean result = ratingDao.implementRatingSong(tester);
        assertNotEquals(incorrectResult, result);

        Rating inserted = ratingDao.findRatingByUsernameAndSongID(tester.getUsername(), tester.getSongID());
        assertNotNull(inserted);

        assertRatingEquals(tester, inserted);

    }

    @Test
    void implementRatingSongButNull() throws SQLException {

        System.out.println("Register a user but null");
        Rating tester = null;


        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");


        assertThrows(NullPointerException.class, () -> {

            ratingDao.implementRatingSong(tester);
        });
    }


    /*
    @Test
    void implementRatingSongButUserNameDoesntMatch() throws SQLException {
        System.out.println("username doesnt match when adding Rating");

        Rating tester = new Rating("You",4,5);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        boolean incorrectResult = false;
        boolean result = ratingDao.implementRatingSong(tester);
        assertEquals(incorrectResult, result);

    }

     */





    @Test
    void getAllRatings() {

        System.out.println("Test for get all rated songs and rating ");
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
    @Test
    void getTopRatedSong() {

        System.out.println("Test for get the song with the highest rating average");

        Time t1 = Time.valueOf("00:04:34");
        Song expected = new Song(2,"Wesleys Theory",5, 2, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertEquals(expected, result);


    }

    @Test
    void getTopRatedSongWhenTheExpectedResultIsNotTheTopRatedSong() {

        System.out.println("Test for get the song with the highest rating average when the expected song isn't the top rated song");

        Time t1 = Time.valueOf("00:02:23");
        Song expected = new Song(3,"Meh",4, 3, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);

    }

    @Test
    void getTopRatedSongWhenSongIdDontMatch() {

        System.out.println("Test for get the song with the highest rating average when songID doesnt match");

        Time t1 = Time.valueOf("00:04:34");
        Song expected = new Song(12,"Wesleys Theory",5, 2, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);


    }

    @Test
    void getTopRatedSongWhenSongNameDontMatch() {

        System.out.println("Test for get the song with the highest rating average when songName doesnt match");

        Time t1 = Time.valueOf("00:04:34");
        Song expected = new Song(2,"yyyy",5, 2, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);
    }

    @Test
    void getTopRatedSongWhenSongTimeDontMatch() {

        System.out.println("Test for get the song with the highest rating average when songTime doesnt match");

        Time t1 = Time.valueOf("00:14:34");
        Song expected = new Song(2,"Wesleys Theory",5, 2, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);
    }

    @Test
    void getTopRatedSongWhenAlbumIDDontMatch() {

        System.out.println("Test for get the song with the highest rating average when albumID doesnt match");

        Time t1 = Time.valueOf("00:04:34");
        Song expected = new Song(2,"Wesleys Theory",2, 2, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);
    }

    @Test
    void getTopRatedSongWhenArtistIDDontMatch() {

        System.out.println("Test for get the song with the highest rating average when artistID doesnt match");

        Time t1 = Time.valueOf("00:04:34");
        Song expected = new Song(2,"Wesleys Theory",5, 9, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);
    }

    @Test
    void getTopRatedSongWhenSongIsNull() {

        System.out.println("Test for get the song with the highest rating average when song is null");


        Song expected = null;

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getTopRatedSong();

        assertNotEquals(expected, result);
    }



    /// MOST POPULAR SONG METHOD

    @Test
    void getMostPopularSong() {

        System.out.println("test for which song is rated is the most popular song to be rated by users");

        Time t1 = Time.valueOf("00:04:59");

        Song expected = new Song(1, "Come on", 1, 1, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertEquals(expected, result);

    }


    @Test
    void getMostPopularSongWhenSongIdDoesntMatch() {

        System.out.println("test for which song is rated is the most popular song to be rated by users when songID doesnt match");

        Time t1 = Time.valueOf("00:04:59");

        Song expected = new Song(11, "Come on", 1, 1, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertNotEquals(expected, result);

    }

    @Test
    void getMostPopularSongWhenSongNameDoesntMatch() {

        System.out.println("test for which song is rated is the most popular song to be rated by users when songName doesnt match");

        Time t1 = Time.valueOf("00:04:59");

        Song expected = new Song(1, "Come on123", 1, 1, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertNotEquals(expected, result);

    }

    @Test
    void getMostPopularSongWhenAlbumIDDoesntMatch() {

        System.out.println("test for which song is rated is the most popular song to be rated by users when songName doesnt match");

        Time t1 = Time.valueOf("00:04:59");

        Song expected = new Song(1, "Come on", 11, 1, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertNotEquals(expected, result);

    }

    @Test
    void getMostPopularSongWhenArtistIDDoesntMatch() {

        System.out.println("test for which song is rated is the most popular song to be rated by users when songName doesnt match");

        Time t1 = Time.valueOf("00:04:59");

        Song expected = new Song(1, "Come on", 1, 11, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertNotEquals(expected, result);

    }

    @Test
    void getMostPopularSongWhenSongLengthDoesntMatch() {

        System.out.println("test for which song is rated is the most popular song to be rated by users when songName doesnt match");

        Time t1 = Time.valueOf("00:14:59");

        Song expected = new Song(1, "Come on", 1, 1, t1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertNotEquals(expected, result);

    }

    @Test
    void getMostPopularSongWhenNull() {

        System.out.println("test for which song is rated is the most popular song to be rated by users when song is Null");

        Song expected = null;

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Song result = ratingDao.getMostPopularSong();

        assertNotEquals(expected, result);

    }


    // GET RATING BY USERNAME AND SONGID METHOD

    @Test
    void findRatingByUsernameAndSongID(){

        System.out.println("test get  rating by username and songID");

        Rating expected = new Rating("Toby", 1, 1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Rating result = ratingDao.findRatingByUsernameAndSongID(expected.getUsername(), expected.getSongID());

        assertRatingEquals(expected,result);
    }


    @Test
    void findRatingByUsernameAndSongIDForNoMatchForUserName(){

        System.out.println("test get rating by username and songID when no match for username");

        Rating expected = new Rating("yyyy", 1, 1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Rating result = ratingDao.findRatingByUsernameAndSongID(expected.getUsername(), expected.getSongID());

       assertNull(result);
    }

    @Test
    void findRatingByUsernameAndSongIDForNoMatchForUserSongID(){

        System.out.println("test get rating by username and songID when no match for username");

        Rating expected = new Rating("Toby", 11, 1);

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        Rating result = ratingDao.findRatingByUsernameAndSongID(expected.getUsername(), expected.getSongID());

        assertNull(result);
    }


    private void assertRatingEquals(Rating r1, Rating r2){
        assertEquals(r1.getUsername(),r2.getUsername());
        assertEquals(r1.getSongID(), r2.getSongID());
        assertEquals(r1.getUserRating(), r2.getUserRating());
    }

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