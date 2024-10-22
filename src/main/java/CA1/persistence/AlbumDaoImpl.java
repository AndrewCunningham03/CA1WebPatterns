package CA1.persistence;

import CA1.business.Album;
import CA1.business.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl extends MySQLDao implements AlbumDao{

    public AlbumDaoImpl(String databaseName){
        super(databaseName);
    }

    public AlbumDaoImpl(){
        super();
    }

    @Override
    public List<Album> viewAllAlbumsFromArtist(int artistID){

        List<Album> album = new ArrayList<>();

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("SELECT * from album where artistID = ?")){

            ps.setInt(1, artistID);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){

                    Album a = mapRow(rs);
                    album.add(a);
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

        return album;

    }


    // getAlbumBasedOnAlbumID

    @Override
    public Album findAlbumById(int albumID){

        Album album = null;

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // TRY to get a statement from the connection
        // When you are parameterizing the query, remember that you need
        // to use the ? notation (so you can fill in the blanks later)
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM album where albumID = ?")) {

            // Fill in the blanks, i.e. parameterize the query
            ps.setInt(1, albumID);

            // TRY to execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Extract the information from the result set
                // Use extraction method to avoid code repetition!
                if(rs.next()){

                    album = mapRow(rs);
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
        return album;
    }


    // getAllAlbums

    @Override
    public ArrayList<Album> getAllAlbums() {
        // Create variable to hold the customer info from the database
        ArrayList<Album> albums = new ArrayList<>();

        // Get a connection using the superclass
        Connection conn = super.getConnection();
        // Get a statement from the connection
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM album")) {
            // Execute the query
            try (ResultSet rs = ps.executeQuery()) {
                // Repeatedly try to get a customer from the resultset
                while(rs.next()){
                    Album a = mapRow(rs);
                    albums.add(a);
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

        return albums;
    }


    private Album mapRow(ResultSet rs)throws SQLException{

        Album a = new Album(

                rs.getInt("albumID"),
                rs.getString("albumName"),
                rs.getInt("artistID"),
                rs.getInt("numberOfSongs"),
                rs.getDate("releaseDate"),
                rs.getTime("albumLength")
        );
        return a;
    }
}
