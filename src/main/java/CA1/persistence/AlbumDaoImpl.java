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
    public List<Album> viewAllAlbumsFromArtist(String artistName){

        List<Album> album = new ArrayList<>();

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("SELECT * from album where artistName = ?")){

            ps.setString(1, artistName);

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
