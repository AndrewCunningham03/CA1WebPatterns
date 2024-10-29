package CA1.persistence;


import CA1.business.Playlist;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlaylistDaoImpl extends MySQLDao implements PlaylistDao{


    public PlaylistDaoImpl(String databaseName){
        super(databaseName);
    }

    public PlaylistDaoImpl(Connection conn){
        super(conn);
    }
    public PlaylistDaoImpl(){
        super();
    }

    /**
     * Gets all the playlists
     * @return Arraylist of Playlists
     */
    @Override
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

    /**
     * Returns the playlist with the playlist id that was given
      * @param playlistID searching using this playlist id
     * @return Playlist that matches the playlist id given
     */
    @Override
    public Playlist getPlaylistsByID(int playlistID){
        Playlist playlist = null;

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("SELECT * from playlist where playlistID = ?")){
            ps.setInt(1,playlistID);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){

                    playlist = mapRow(rs);
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

        return playlist;
    }

    /**
     * Gets the number of playlists
     * @return number of playlists (integer)
     */
    @Override
    public int numberOfPlaylists(){
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

        return playlists.size();
    }

    /**
     * Adds a new playlist to the database
     * @param newPlaylist the playlist we will be adding
     * @return true if it has been added or false if it has not been added
     */
    @Override
    public boolean insertNewPlaylists(Playlist newPlaylist){
        int rowsAffected = 0;

        Connection conn = super.getConnection();

        try(PreparedStatement ps = conn.prepareStatement("INSERT INTO playlist VALUES (?, ?, ?, ?)")){
            ps.setInt(1,newPlaylist.getPlaylistID());
            ps.setString(2, newPlaylist.getPlaylistName());
            ps.setString(3, newPlaylist.getUsername());
            ps.setBoolean(4,newPlaylist.isStatusPrivate());
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
     * Renames a current playlistname to a new name
     * @param playlistName the playlist name we will be renaming
     * @param name new playlist name
     * @return true if name was change or false if it wasn't changed
     * @throws RuntimeException if multiple rows are unexpectedly affected by the update
     */
    public boolean updatePlaylistName(String playlistName, String name) throws RuntimeException {
        int rowsAffected = 0;

        Connection conn = super.getConnection();
        try (PreparedStatement ps =
                     conn.prepareStatement("UPDATE playlist SET playlistName = ? WHERE playlistName = ?")) {
            ps.setString(1, name);
            ps.setString(2, playlistName);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException  occurred while preparing the SQL " +
                    "statement.");
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
     * Maps the current row of the ResultSet to a Playlist object.
     * @param rs the ResultSet to map from
     * @return a Playlist object containing data from the current ResultSet row
     * @throws SQLException if a database access error occurs
     */
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
