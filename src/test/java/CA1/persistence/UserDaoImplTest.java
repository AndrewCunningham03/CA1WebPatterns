package CA1.persistence;

import CA1.business.Rating;
import CA1.business.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    private MySQLDao connectionSource = new MySQLDao("database_test.properties");


    @Test
    void registerUser() throws SQLException {

        System.out.println("Test for Register a user");
        User tester = new User("Roanldo","ronaldo@gmail.com","house",1);

        boolean incorrectResult = false;

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        UserDao userDao = new UserDaoImpl(conn);

        boolean result = userDao.registerUser(tester);
        assertNotEquals(incorrectResult, result);

        User inserted = userDao.findUserByUsername(tester.getUsername());
        assertNotNull(inserted);

        assertUserEquals(tester, inserted);
    }

    @Test
    void registerUserButNull() throws SQLException {

        System.out.println("Test for Register a user but null");
        User tester = null;


        UserDao userDao = new UserDaoImpl("database_test.properties");


        assertThrows(NullPointerException.class, () -> {

            userDao.registerUser(tester);
        });
    }

    @Test
    void loginUser() {

        System.out.println("Test for Login a user");

        String email = "toby@gmail.com";
        String password = "ron";

        boolean expected = true;

        UserDao userDao = new UserDaoImpl("database_test.properties");

        boolean result = userDao.loginUser(email,password);

        assertEquals(expected, result);
    }

    @Test
    void loginUserButNotCorrectEmail() {

        System.out.println("Test for Login a user but nopt correct Email");

        String email = "toby345@gmail.com";
        String password = "ron";

        boolean expected = false;

        UserDao userDao = new UserDaoImpl("database_test.properties");

        boolean result = userDao.loginUser(email,password);

        assertEquals(expected, result);
    }

    @Test
    void loginUserButNotCorrectPassword() {

        System.out.println("Test for Login a user but nopt correct Email");

        String email = "toby@gmail.com";
        String password = "ron345423";

        boolean expected = false;

        UserDao userDao = new UserDaoImpl("database_test.properties");

        boolean result = userDao.loginUser(email,password);

        assertEquals(expected, result);
    }


    @Test
    void loginUserButEmailIsNull() {

        System.out.println("Test for Login a user but Email is null");

        String email = null;
        String password = "ron";

        boolean expected = false;

        UserDao userDao = new UserDaoImpl("database_test.properties");

        boolean result = userDao.loginUser(email,password);

        assertEquals(expected, result);
    }

    @Test
    void loginUserButPasswordIsNull() {

        System.out.println("Test for Login a user but password is null");

        String email = "toby@gmail.com";
        String password = null;

        boolean expected = false;

        UserDao userDao = new UserDaoImpl("database_test.properties");

        boolean result = userDao.loginUser(email,password);

        assertEquals(expected, result);
    }

    @Test
    void findUserByUsername() {
        System.out.println("Test find user by username");

        User expected = new User("Toby","toby@gmail.com","ron",1);

        UserDao userDao = new UserDaoImpl("database_test.properties");

        User result = userDao.findUserByUsername(expected.getUsername());

        assertUserEquals(expected, result);
    }

    @Test
    void findUserByUsernameButNoMatch() {
        System.out.println("Test find user by username but No Match");

        User expected = new User("yyyy","toby@gmail.com","ron",1);

        UserDao userDao = new UserDaoImpl("database_test.properties");

        User result = userDao.findUserByUsername(expected.getUsername());

        assertNull(result);
    }

    @Test
    void getUserRatingFromUsernameAndSongID(){

        System.out.println("Test get userRating from username and songID");

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        String username = "Toby";
        int songID = 5;

        double expected = 3.9;

        double result = ratingDao.getUserRatingFromUsernameAndSongID(username, songID);

        assertEquals(expected, result);
    }

    @Test
    void getUserRatingFromUsernameAndSongIDWhereUserNameIsDifferent(){

        System.out.println("Test get userRating from username and songID where userName is different");

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        String username = "Sam";
        int songID = 5;

        double expected = 3.9;

        double result = ratingDao.getUserRatingFromUsernameAndSongID(username, songID);

        assertNotEquals(expected, result);
    }


    @Test
    void getUserRatingFromUsernameAndSongIDWhereSongIDIsDifferent(){

        System.out.println("Test get userRating from username and songID where songID is different");

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        String username = "Toby";
        int songID = 1;

        double expected = 3.9;

        double result = ratingDao.getUserRatingFromUsernameAndSongID(username, songID);

        assertNotEquals(expected, result);
    }

    @Test
    void getUserRatingFromUsernameAndSongIDWhereUsernameIsNull(){

        System.out.println("Test get userRating from username and songID where username is null");

        RatingDao ratingDao = new RatingDaoImpl("database_test.properties");

        String username = null;
        int songID = 5;

        double expected = 3.9;

        double result = ratingDao.getUserRatingFromUsernameAndSongID(username, songID);

        assertNotEquals(expected, result);
    }



    private void assertUserEquals(User u1, User u2){
        assertEquals(u1.getUsername(), u2.getUsername());
        assertEquals(u1.getEmail(), u2.getEmail());
        assertEquals(u1.getPassword(), u2.getPassword());
        assertEquals(u1.getUserType(), u2.getUserType());
    }

}