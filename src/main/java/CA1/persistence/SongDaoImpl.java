package CA1.persistence;

import CA1.business.Album;
import CA1.business.Playlist;
import CA1.business.Rating;
import CA1.business.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Toby
 * @author Andrew
 */
public class SongDaoImpl extends MySQLDao implements SongDao{

    /**
     * Get the information from the database
     * @param databaseName is the database being searched
     */
    public SongDaoImpl(String databaseName){
        super(databaseName);
    }

    public SongDaoImpl(){
        super();
    }


    /**
     * Get the Song from a particular songID
     * @param songID is the songID being searched
     * @return the song from the particular songID
     */
    @Override
    public Song findSongById(int songID){

        Song song = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs where songID = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, songID);

            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if(rs.next()){

                    song = mapRow(rs);
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
        return song;
    }

@Override
    public ArrayList<Song> getAllSongs(){
        ArrayList<Song> songs = new ArrayList<>();

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("SELECT * from songs")){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){

                    Song s = mapRow(rs);
                    songs.add(s);
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
        }

        return songs;
    }


    /// song title

    @Override
    public ArrayList<Song> getSongByTitle(String songTitle){

        ArrayList<Song> song = new ArrayList<>();

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs where songName = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, songTitle);

            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                while(rs.next()){

                    Song s = mapRow(rs);
                    song.add(s);
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
        return song;
    }
    //by artist
    @Override
    public ArrayList<Song> getSongByArtist(int artistID){

        ArrayList<Song> song = new ArrayList<>();

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs where artistID = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, artistID);

            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                while(rs.next()){

                    Song s = mapRow(rs);
                    song.add(s);
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
        return song;
    }
    //by artist
    @Override
    public ArrayList<Song> getSongByAlbum(int albumID){

        ArrayList<Song> song = new ArrayList<>();

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs where albumID = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, albumID);

            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                while(rs.next()){

                    Song s = mapRow(rs);
                    song.add(s);
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
        return song;
    }

    /**
     * Search through each row in the song
     * @param rs is the song query being searched
     * @return the song information
     * @throws SQLException if songID isn't unique or if albumID and artistID doesnt exist
     */
    private Song mapRow(ResultSet rs)throws SQLException {

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
