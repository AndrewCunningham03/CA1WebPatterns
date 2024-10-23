package CA1.persistence;

import CA1.business.Song;
import CA1.business.User;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDaoImpl extends MySQLDao implements UserDao{


    public UserDaoImpl(String databaseName){
        super(databaseName);
    }

    public UserDaoImpl(Connection conn){
        super(conn);
    }
    public UserDaoImpl(){
        super();
    }

    private static final Scanner input = new Scanner(System.in);
    public User createUserRegister() throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println("Please enter the username:");
        String userName = input.nextLine();

        String email = null;

        boolean repeat = false;

        while(!repeat){

             System.out.println("Please enter the email with any letter or number before the @ symbol and after the @symbol :");
             email = input.nextLine();

            Pattern pattern = Pattern.compile("^(.+)@(.+)$");
            Matcher match = pattern.matcher(email);
            boolean matchfound = match.find();

            if (matchfound){
                System.out.println("Correct email details");
                repeat = true;
            }else{

                System.out.println("Enter email details again ");
                repeat = false;
            }
        }

        System.out.println("Please enter the password:");
        String password = input.nextLine();

        System.out.println("Please enter the userType");
        int userType = input.nextInt();


        return new User(userName, email, hashPassword(password), userType);
    }


    public boolean registerUser(User newUser){

        int rowsAffected = 0;

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("insert into users values(?, ?, ?, " +
                "?)")) {
            // Fill in the blanks, i.e. parameterize the update
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getEmail());
            ps.setString(3, newUser.getPassword());
            ps.setInt(4, newUser.getUserType());


            rowsAffected = ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("SQL Exception occurred when attempting to prepare/execute SQL");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        if(rowsAffected > 1){
            throw new RuntimeException(LocalDateTime.now() + " ERROR: Multiple rows affected on primary key selection" +
                    ".");
        }
        else if(rowsAffected == 0){
            return false;
        }else{
            return true;
        }
    }



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


    private User mapRow(ResultSet rs)throws SQLException {

        User u = new User(

                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("userType")
        );
        return u;
    }


    //refernce

    // HowToDoItInJava - Java Email Validation using Regex - https://howtodoinjava.com/java/regex/java-regex-validate-email-address/

}
