package CA1.persistence;

import CA1.business.Album;
import CA1.business.Rating;
import CA1.business.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongDaoImpl extends MySQLDao implements SongDao{

    public SongDaoImpl(String databaseName){
        super(databaseName);
    }

    public SongDaoImpl(){
        super();
    }



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
