package CA1.persistence;

import CA1.business.Artist;
import CA1.business.Rating;
import CA1.business.Song;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RatingDaoImpl extends MySQLDao implements RatingDao{

    public RatingDaoImpl(String databaseName){
        super(databaseName);
    }


    public RatingDaoImpl(Connection conn){
        super(conn);
    }
    public RatingDaoImpl(){
        super();
    }


     private static final Scanner keyboard = new Scanner(System.in);

    @Override
    public Rating rateSong(){

        System.out.println("Enter username: ");
        String username = keyboard.nextLine();

        System.out.println("Enter songID");
        int songID = keyboard.nextInt();


        double rating = 0;

        boolean repeat = false;

        while(!repeat){

            System.out.println("Enter song rating: ");
            rating = keyboard.nextDouble();

            if (rating >=1 && rating <= 5){

                System.out.println("Thank you for the rating ");
                repeat = true;
            }else{
                System.out.println("Number has to be between 1 - 5: ");
                repeat = false;
            }
        }


        return new Rating(username,songID,rating);
    }

@Override
    public int implementRatingSong(Rating rating){
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
        try(PreparedStatement ps = conn.prepareStatement("insert into rating values(?, ?, " +
                "?)")) {
            // Fill in the blanks, i.e. parameterize the update
            ps.setString(1, rating.getUsername());
            ps.setInt(2, rating.getSongID());
            ps.setDouble(3, rating.getUserRating());

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




    @Override
    public ArrayList<Rating> getAllRatings(){

        ArrayList<Rating> rating = new ArrayList<>();

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("SELECT * from rating")){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){

                    Rating r = mapRow(rs);
                    rating.add(r);
                }
            }catch(SQLException e){
                System.out.println(LocalDateTime.now() + ": An SQLException  occurred while running the query" +
                        " or processing the result.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }catch(SQLException e){
            System.out.println(LocalDateTime.now() + ": An SQLException  occurred while preparing the SQL " +
                    "statement.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            // Close the connection using the superclass method
            super.freeConnection(conn);
        }

        return rating;
    }

    @Override
    public Song getTopRatedSong(){


        SongDao songDao = new SongDaoImpl("database.properties");

        int rating = 0;


        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT songID from rating GROUP BY songID ORDER BY AVG(userRating) DESC LIMIT 1")) {
            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!

                if (rs.next()) {
                    rating = mapRow1(rs);
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


        return songDao.findSongById(rating);
    }


    @Override
    public Song getMostPopularSong(){

        SongDao songDao = new SongDaoImpl("database.properties");
        int rating = 0;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT songID from rating GROUP BY songID ORDER BY COUNT(*) DESC LIMIT 1")) {
            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if (rs.next()) {
                    rating = mapRow1(rs);
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
        return songDao.findSongById(rating);
    }


    @Override
    public Rating findRatingByUsernameAndSongID(String username, int songID){

        Rating rating = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM rating where username = ? AND songID = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, username);
            ps.setInt(2, songID);

            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if(rs.next()){

                    rating = mapRow(rs);
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
        return rating;
    }

    @Override
    public ArrayList<Rating> getUserRatingFromUsername(String username){

        ArrayList<Rating> rating = new ArrayList<>();


        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM rating where username = ? ")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, username);


            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                while(rs.next()){
                    Rating a = mapRow(rs);
                    rating.add(a);

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
        return rating;
    }






    private Rating mapRow(ResultSet rs)throws SQLException {

        Rating r = new Rating(
                rs.getString("username"),
                rs.getInt("songID"),
                rs.getDouble("userRating")
        );
        return r;
    }

    private int mapRow1(ResultSet rs)throws SQLException {

        int r =  rs.getInt("songID");
        return r;
    }

    private double mapRowRating(ResultSet rs)throws SQLException {

        double r =  rs.getDouble("userRating");
        return r;
    }

    private Song mapRowSong(ResultSet rs)throws SQLException {

        Song s = new Song(

                rs.getInt("songID"),
                rs.getString("songName"),
                rs.getInt("albumID"),
                rs.getInt("artistID"),
                rs.getTime("songLength")
        );
        return s;
    }



}



//// refernce, Find most frequent value in SQL column - stackoverflow
/// How to validate Visa Card number using Regular Expression - geeksforgeeks