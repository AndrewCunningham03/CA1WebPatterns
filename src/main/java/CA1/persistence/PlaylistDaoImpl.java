package CA1.persistence;


import CA1.business.Playlist;
import CA1.business.Song;
import CA1.business.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaylistDaoImpl extends MySQLDao implements PlaylistDao{

    Scanner keyboard = new Scanner(System.in);
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

    public boolean updatePlaylistNameUser(User user){
        ArrayList<Playlist> playlists = new ArrayList<>();
        playlists = getAllPlaylists();
        String playlistname = null;
        boolean found2 = false;
        while(!found2) {

                System.out.println("Enter Playlist name of playlist you want to change");
                playlistname = keyboard.nextLine();

                for (int i = 0; i < playlists.size();i++){
                    if (playlists.get(i).getPlaylistName().equalsIgnoreCase(playlistname)){

                        if (playlists.get(i).isStatusPrivate() == false || playlists.get(i).getUsername().equals(user.getUsername())){
                            found2 = true;
                        }else{
                            System.out.println("Name given not found please re-enter");
                            keyboard.nextLine();
                            found2 =false;
                        }

                    }
                }
        }
        System.out.println("Enter the new name you would like to call it");
        String newName = keyboard.next();
        boolean done = updatePlaylistName(playlistname,newName);
        if(done){
            System.out.println("Playlist name has been change to "+newName);
        }
        return done;

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
