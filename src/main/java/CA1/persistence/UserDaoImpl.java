package CA1.persistence;

import CA1.business.Rating;
import CA1.business.Song;
import CA1.business.User;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Toby
 */
public class UserDaoImpl extends MySQLDao implements UserDao{


    /**
     * get the database information from a particular database
     * @param databaseName is the database being searched
     */
    public UserDaoImpl(String databaseName){
        super(databaseName);
    }

    public UserDaoImpl(Connection conn){
        super(conn);
    }
    public UserDaoImpl(){
        super();
    }



    /**
     * Add a new user to the database
     * @param newUser is the user being added
     * @return 1 is user was added and -1 if not added
     */
    @Override
    public int registerUser(User newUser){
        // DATABASE CODE
        //
        // Create variable to hold the result of the operation
        // Remember, where you are NOT doing a select, you will only ever get
        // a number indicating how many things were changed/affected
        int rowsAffected = 0;


        Connection conn = super.getConnection();

        // TRY to prepare a statement from the connection
        // When you are parameterizing the update, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try(PreparedStatement ps = conn.prepareStatement("insert into users values(?, ?, ?, " +
                "?)")) {
            // Fill in the blanks, i.e. parameterize the update
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getEmail());
            ps.setString(3, newUser.getPassword());
            ps.setInt(4, newUser.getUserType());

            // Execute the update and store how many rows were affected/changed
            // when inserting, this number indicates if the row was
            // added to the database (>0 means it was added)
            rowsAffected = ps.executeUpdate();
        }// Add an extra exception handling block for where there is already an entry
        // with the primary key specified
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Constraint Exception occurred: " + e.getMessage());
            // Set the rowsAffected to -1, this can be used as a flag for the display section
            rowsAffected = -1;
        }catch(SQLException e){
            System.out.println("SQL Exception occurred when attempting to prepare/execute SQL");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsAffected;
    }

    /**
     * Login into the system based on the email and password information matching what's in the database
     * @param email is the email being searched
     * @param password is the password being searched
     * @return true if login was successful and false if not
     */
    public boolean loginUser(String email, String password)  {



        Connection conn = super.getConnection();
        ArrayList<User> user = new ArrayList<>();


        try (PreparedStatement ps = conn.prepareStatement("SELECT * from users WHERE email = ? AND password = ?")) {

            ps.setString(1,email);
            ps.setString(2,password);


            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User p = new User(
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("userType")
                    );
                    return true;
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": An SQLException  occurred while running the query" +
                        " or processing the result.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException  occurred while preparing the SQL " +
                    "statement.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            // Close the connection using the superclass method
            super.freeConnection(conn);
        }



        return false;

    }

    /**
     * Get a particular user based on the username
     * @param username is the user being searched
     * @return the user from that particular username
     */
    @Override
    public User findUserByUsername(String username){

        User user = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where username = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, username);

            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if(rs.next()){

                    user = mapRow(rs);
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when executing SQL or processing results.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            // Close the connection using the superclass method
            super.freeConnection(conn);
        }
        return user;
    }

    /**
     * Get the user of a particular email
     * @param email is the email being searched
     * @return the user that has the particular email
     */
    @Override
    public User findUserByThereEmail(String email){

        User user = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where email = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, email);


            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if(rs.next()){

                    user = mapRow(rs);
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred when executing SQL or processing results.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to prepare SQL for execution");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            // Close the connection using the superclass method
            super.freeConnection(conn);
        }
        return user;
    }


    /**
     * Turn the password into a hashed password
     * @param password is the password to be hashed
     * @return the hashed password
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public String hashPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        char[]passwordChars = password.toCharArray();
        byte [] saltBytes = "NotSoSecretSalt".getBytes();
        int iterations = 65536;
        int keySize = 256;

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

        PBEKeySpec spec = new PBEKeySpec(passwordChars,saltBytes,iterations,keySize);

        SecretKey key = factory.generateSecret(spec);

        String keyAsString = Base64.getEncoder().encodeToString(key.getEncoded());

        return keyAsString;



    }

    /**
     * Search through each row in the user
     * @param rs is the query for user to be searched
     * @return the user information
     * @throws SQLException is username and email isn't unique
     */
    private User mapRow(ResultSet rs)throws SQLException {

        User u = new User(

                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("userType")
        );
        return u;
    }


    //reference

    // HowToDoItInJava - Java Email Validation using Regex - https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
    /// How to validate Visa Card number using Regular Expression - geeksforgeeks - https://www.geeksforgeeks.org/how-to-validate-visa-card-number-using-regular-expression/
}
