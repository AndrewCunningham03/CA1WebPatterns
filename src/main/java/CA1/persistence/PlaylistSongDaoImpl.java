package CA1.persistence;

import CA1.business.Playlist;
import CA1.business.PlaylistSong;
import CA1.business.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PlaylistSongDaoImpl extends MySQLDao {
    public PlaylistSongDaoImpl(String databaseName){
        super(databaseName);
    }

    public PlaylistSongDaoImpl(){
        super();
    }

    public boolean addNewSongToPlaylist(PlaylistSong song){
        int rowsAffected = 0;

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("INSERT INTO song VALUES (?, ?)")){
            ps.setInt(1,song.getPlaylistID());
            ps.setInt(2,song.getSongID());
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


}
