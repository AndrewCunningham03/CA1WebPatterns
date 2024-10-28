package CA1.persistence;

import CA1.business.Artist;
import CA1.business.Rating;
import CA1.business.Song;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RatingDaoImpl extends MySQLDao implements RatingDao{

    /**
     * Get the database information
     * @param databaseName is the database being searched
     */
    public RatingDaoImpl(String databaseName){
        super(databaseName);
    }


    public RatingDaoImpl(Connection conn){
        super(conn);
    }
    public RatingDaoImpl(){
        super();
    }


    /**
     * Add a rating to the database
     * @param rating is the rating beinmg added
     * @return 1 if rating was added and -1 if rating was not added
     */
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


    /**
     * Get all the ratings in the database
     * @return an arraylist of all the ratings
     */

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

    /**
     * Get the Song that has the highest average rating
     * @return the song with the highest average rating
     */
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


    // LowestRatedSong

    /**
     * Get the Song that has the lowest average rating
     * @return the song with the lowest average rating
     */
    @Override
    public Song getLowestRatedSong(){


        SongDao songDao = new SongDaoImpl("database.properties");

        int rating = 0;


        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT songID from rating GROUP BY songID ORDER BY AVG(userRating) ASC LIMIT 1")) {
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

    /**
     * Get the song that is rated the most
     * @return the song that is rated the most
     */
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

    /**
     * Get a rating from a particular username and SongID
     * @param username is the username being searched
     * @param songID is the songID being searched
     * @return the rating that matches the username and songID
     */
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

    /**
     * Get an arraylist of all the ratings from a particular user
     * @param username is the username being searched
     * @return an arraylist of ratings
     */
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


    /**
     * Search through each row in the rating
     * @param rs is the rating query being searched
     * @return the rating information
     * @throws SQLException is username and songID doesnt exist
     */

    private Rating mapRow(ResultSet rs)throws SQLException {

        Rating r = new Rating(
                rs.getString("username"),
                rs.getInt("songID"),
                rs.getDouble("userRating")
        );
        return r;
    }

    /**
     * Search through the songID
     * @param rs is the query being searched
     * @return the songID information
     * @throws SQLException is songID doesnt exist
     */
    private int mapRow1(ResultSet rs)throws SQLException {

        int r =  rs.getInt("songID");
        return r;
    }



}



//// refernce,
// Find most frequent value in SQL column - stackoverflow - https://stackoverflow.com/questions/12235595/find-most-frequent-value-in-sql-column
