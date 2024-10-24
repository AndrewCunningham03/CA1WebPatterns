package CA1.persistence;


import CA1.business.Playlist;
import CA1.business.Song;
import CA1.business.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

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

        public boolean createPlaylist(User user){

            Scanner keyboard = new Scanner(System.in);

            System.out.println("Enter playlist name");
            String playlistName = keyboard.next();

            boolean status = false;
            System.out.println("Would you like playlist to be public? yes or no");
            String answer = keyboard.next();
            if (answer.equalsIgnoreCase("no")){
                status=true;
            }

            String userName = user.getUsername();
            int ID = numberOfPlaylists()+1;

            Playlist playlist = new Playlist(ID,playlistName,userName,status);

            boolean complete = insertNewPlaylists(playlist);

            if(!complete){
                System.out.println("Playlist couldn't be created please try again");
                return complete;
            }

            System.out.println("Playlist: "+playlistName+" has been created");
            return complete;
        }

    public boolean updatePlaylistName(int playlistID, String name) throws RuntimeException {
        int rowsAffected = 0;

        Connection conn = super.getConnection();
        try (PreparedStatement ps =
                     conn.prepareStatement("UPDATE Employees SET playlistName = ? WHERE playlistID = ?")) {
            ps.setString(1, name);
            ps.setInt(2, playlistID);

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
