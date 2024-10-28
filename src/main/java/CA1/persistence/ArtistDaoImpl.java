package CA1.persistence;

import CA1.business.Album;
import CA1.business.Artist;

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
public class ArtistDaoImpl extends MySQLDao implements ArtistDao {


    /**
     * Get the database information from a particular database
     * @param databaseName is the database being searched
     */
    public ArtistDaoImpl(String databaseName){
        super(databaseName);
    }

    public ArtistDaoImpl(){
        super();
    }

    /**
     * get all artist and there information
     * @return an arraylist of all artist
     */
    @Override
    public ArrayList<Artist>getAllArtist(){
        ArrayList<Artist> artist = new ArrayList<>();

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("SELECT * from artist")){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){

                    Artist a = mapRow(rs);
                    artist.add(a);
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

        return artist;
    }

    // get artist based on artist id

    /**
     * Get the artist from a particular artistID
     * @param artistID is the artist being searched
     * @return the artist that is being searched
     */
    @Override
    public Artist findArtistById(int artistID){

        Artist artist = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM artist where artistID = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, artistID);

            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if(rs.next()){

                    artist = mapRow(rs);
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
        return artist;
    }

    /**
     * Search through each row in the artist
     * @param rs is the artist query being searched
     * @return the artist information
     * @throws SQLException if artistId isn't unique
     */
    private Artist mapRow(ResultSet rs)throws SQLException{

        Artist a = new Artist(

                rs.getInt("artistID"),
                rs.getString("artistName"),
                rs.getString("genre"),
                rs.getString("hometown"),
                rs.getDate("dateOfBirth")
        );
        return a;
    }
}
