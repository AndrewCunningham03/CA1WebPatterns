package CA1.persistence;


import CA1.business.Playlist;
import CA1.business.PlaylistSong;
import CA1.business.Song;
import CA1.business.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaylistSongDaoImpl extends MySQLDao implements PlaylistSongDao{
    public PlaylistSongDaoImpl(String databaseName){
        super(databaseName);
    }
    public PlaylistSongDaoImpl(Connection conn){
        super(conn);
    }
    public PlaylistSongDaoImpl(){
        super();
    }

    /**
     * Adds a song to playlistsong
     * @param song song we will be adding
     * @return true if it has been added, false if it hasn't been added.
     */
    @Override
    public boolean addNewSongToPlaylist(PlaylistSong song){
        int rowsAffected = 0;

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("INSERT INTO playlistsong VALUES (?, ?)")){
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

    /**
     * Removes a song from playlistsongs
     * @param playlistSong the playlist song we will be deleting
     * @return true if it has been removed, false if it hasn't been removed
     */
    @Override
    public boolean removingSongFromPlayList(PlaylistSong playlistSong){
        int rowsAffected = 0;

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("DELETE from PlaylistSong where playlistID = ? and songID = ?")){
            ps.setInt(1,playlistSong.getPlaylistID());
            ps.setInt(2,playlistSong.getSongID());
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

    /**
     * Getting all the playlist songs for given playlist id
     * @param playlistID the playlist id we will be searching with
     * @return arraylist of playlistsongs for the given playlist id
     */
    @Override
    public ArrayList<PlaylistSong> getPlaylistsByID(int playlistID){
        ArrayList<PlaylistSong> playlistSongs = new ArrayList<>();

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("SELECT * from playlistsong where playlistID = ?")){
            ps.setInt(1,playlistID);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){

                    PlaylistSong p = mapRow(rs);
                    playlistSongs.add(p);
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

        return playlistSongs;
    }

    /**
     * Gets all the playlistsongs in the database
     * @return arraylist of playlistsongs
     */
    @Override
    public ArrayList<PlaylistSong> getAllPlaylistSongs(){
        ArrayList<PlaylistSong> playlists = new ArrayList<>();

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("SELECT * from playlistsong")){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){

                    PlaylistSong a = mapRow(rs);
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

    /**
     * Maps the current row of the ResultSet to a PlaylistSong object.
     * @param rs the ResultSet to map from
     * @return a PlaylistSong object containing data from the current ResultSet row
     * @throws SQLException if a database access error occurs
     */
    private PlaylistSong mapRow(ResultSet rs)throws SQLException{

        PlaylistSong a = new PlaylistSong(
                rs.getInt("playlistID"),
                rs.getInt("songID")
        );
        return a;
    }





}
