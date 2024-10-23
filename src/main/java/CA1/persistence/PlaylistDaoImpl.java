package CA1.persistence;


import CA1.business.Playlist;
import CA1.business.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlaylistDaoImpl extends MySQLDao implements PlaylistDao{
    public PlaylistDaoImpl(String databaseName){
        super(databaseName);
    }

    public PlaylistDaoImpl(){
        super();
    }

    public ArrayList<Playlist> getAllPlaylists(){
            ArrayList<Playlist> playlists = new ArrayList<>();

            Connection conn = super.getConnection();

            try(PreparedStatement ps = conn.prepareStatement("SELECT * from playlist")){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){

                        Playlist a = mapRow(rs);
                        playlists.add(a);
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

            return playlists;
        }

    private Playlist mapRow(ResultSet rs)throws SQLException{

        Playlist a = new Playlist(
                rs.getInt("playlistID"),
                rs.getString("playlistName"),
                rs.getString("username"),
                rs.getBoolean("statusPrivate")
        );
        return a;
    }

}
