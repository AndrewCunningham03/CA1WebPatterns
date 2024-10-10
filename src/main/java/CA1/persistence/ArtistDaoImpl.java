package CA1.persistence;

import CA1.business.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ArtistDaoImpl extends MySQLDao implements ArtistDao {


    public ArtistDaoImpl(String databaseName){
        super(databaseName);
    }

    public ArtistDaoImpl(){
        super();
    }


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
        }

        return artist;
    }

    private Artist mapRow(ResultSet rs)throws SQLException{

        Artist a = new Artist(

                rs.getString("artistName"),
                rs.getString("genre"),
                rs.getString("hometown"),
                rs.getDate("dateOfBirth")
        );
        return a;
    }
}
