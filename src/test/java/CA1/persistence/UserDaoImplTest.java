package CA1.persistence;

import CA1.business.Rating;
import CA1.business.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Toby
 */
class UserDaoImplTest {

    private MySQLDao connectionSource = new MySQLDao("database_test.properties");



    /// TEST FOR REGISTER

    /**
     * Test for Register a user
     * @throws SQLException is username or email isn't unique
     */
    @Test
    void registerUser() throws SQLException {

        System.out.println("Test for Register a user");
        User tester = new User("Roanldo","ronaldo@gmail.com","house",1);

        int incorrectResult = -1;

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        UserDao userDao = new UserDaoImpl(conn);

        int result = userDao.registerUser(tester);
        assertNotEquals(incorrectResult, result);

        User inserted = userDao.findUserByUsername(tester.getUsername());
        assertNotNull(inserted);

        assertUserEquals(tester, inserted);

    }

    /**
     * Test for Register a user but user already exist
     */
    @Test
    void registerUserButUserNameAlreadyExist(){
        System.out.println("Test for Register a user but userName already exist");
        User tester = new User("Toby","ronaldo@gmail.com","house",1);

        UserDao userDao = new UserDaoImpl("database_test.properties");

        int incorrectResult = -1;
        int result = userDao.registerUser(tester);
        assertEquals(incorrectResult, result);

    }

    /**
     * Test for Register a user but email isn't unique
     */
    @Test
    void registerUserButEmailMatch(){
        System.out.println("Test for Register a user but email isn't unique");
        User tester = new User("Jennifer","toby@gmail.com","house",1);

        UserDao userDao = new UserDaoImpl("database_test.properties");

        int incorrectResult = -1;
        int result = userDao.registerUser(tester);
        assertEquals(incorrectResult, result);
    }

    /**
     * Test for Register a user but null
     */
    @Test
    void registerUserButNull() {

        System.out.println("Test for Register a user but null");
        User tester = null;


        UserDao userDao = new UserDaoImpl("database_test.properties");


        assertThrows(NullPointerException.class, () -> {

            userDao.registerUser(tester);
        });
    }

    //// LOGIN TEST

    /**
     * Test for Login a user
     */
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

    /**
     * Test for Login a user but not correct Email
     */
    @Test
    void loginUserButNotCorrectEmail() {

        System.out.println("Test for Login a user but not correct Email");

        String email = "toby345@gmail.com";
        String password = "ron";

        boolean expected = false;

        UserDao userDao = new UserDaoImpl("database_test.properties");

        boolean result = userDao.loginUser(email,password);

        assertEquals(expected, result);
    }

    /**
     * est for Login a user but not correct Password
     */
    @Test
    void loginUserButNotCorrectPassword() {

        System.out.println("Test for Login a user but not correct Password");

        String email = "toby@gmail.com";
        String password = "ron345423";

        boolean expected = false;

        UserDao userDao = new UserDaoImpl("database_test.properties");

        boolean result = userDao.loginUser(email,password);

        assertEquals(expected, result);
    }


    /**
     * Test for Login a user but Email is null
     */
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


    /**
     * Test for Login a user but password is null
     */
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


    // FIND BY USERNAME TEST

    /**
     * Test find user by username
     */
    @Test
    void findUserByUsername() {
        System.out.println("Test find user by username");

        User expected = new User("Toby","toby@gmail.com","ron",1);

        UserDao userDao = new UserDaoImpl("database_test.properties");

        User result = userDao.findUserByUsername(expected.getUsername());

        assertUserEquals(expected, result);
    }

    /**
     * Test find user by username but No Match
     */
    @Test
    void findUserByUsernameButNoMatch() {
        System.out.println("Test find user by username but No Match");

        User expected = new User("yyyy","toby@gmail.com","ron",1);

        UserDao userDao = new UserDaoImpl("database_test.properties");

        User result = userDao.findUserByUsername(expected.getUsername());

        assertNull(result);
    }



    //// TEST EMAILS

    /**
     * Test for Find user by there email
     */
    @Test
    void findUserByThereEmail(){

        System.out.println("Test for Find user by there email");

        User expected = new User("Toby","toby@gmail.com","ron",1);


        UserDao userDao = new UserDaoImpl("database_test.properties");

        User result = userDao.findUserByThereEmail(expected.getEmail());

        assertEquals(expected,result);

    }

    /**
     * Test for Find user by there email when email is not there
     */
    @Test
    void findUserByThereEmailWhenEmailIsNotThere(){

        System.out.println("Test for Find user by there email when email is not there");

        User expected = new User("Toby","yyyy@gmail.com","ron",1);


        UserDao userDao = new UserDaoImpl("database_test.properties");

        User result = userDao.findUserByThereEmail(expected.getEmail());

        assertNotEquals(expected,result);

    }

    /**
     * test for Find user by there email when email is null
     */
    @Test
    void findUserByThereEmailWhenEmailIsNull(){

        System.out.println("test for Find user by there email when email is null");

        User expected = new User("Toby",null,"ron",1);


        UserDao userDao = new UserDaoImpl("database_test.properties");

        User result = userDao.findUserByThereEmail(expected.getEmail());

        assertNotEquals(expected,result);

    }

    /**
     * Check if two users have the same values
     * @param u1 is the user being searched
     * @param u2 is the user being searched
     */
    private void assertUserEquals(User u1, User u2){
        assertEquals(u1.getUsername(), u2.getUsername());
        assertEquals(u1.getEmail(), u2.getEmail());
        assertEquals(u1.getPassword(), u2.getPassword());
        assertEquals(u1.getUserType(), u2.getUserType());
    }

}