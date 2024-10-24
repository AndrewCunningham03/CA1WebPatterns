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

    Scanner keyboard = new Scanner(System.in);

    SongDaoImpl songDao = new SongDaoImpl("database.properties");

    PlaylistDaoImpl playlistDao = new PlaylistDaoImpl("database.properties");
    public PlaylistSongDaoImpl(String databaseName){
        super(databaseName);
    }

    public PlaylistSongDaoImpl(){
        super();
    }

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
    public boolean addNewSongToPlaylistUser(User user){

        PlaylistDao playlistDao1 = new PlaylistDaoImpl("database.properties");

        ArrayList<Playlist> playlists = playlistDao1.getAllPlaylists();

        int playlistID = 0;
        boolean found2 = false;
        while(!found2) {

            try {
                System.out.println("Enter Playlist ID");
                playlistID = keyboard.nextInt();

                for (int i = 0; i < playlists.size();i++){
                    if (playlists.get(i).getPlaylistID() == playlistID){

                        if (playlists.get(i).isStatusPrivate() == false || playlists.get(i).getUsername().equals(user.getUsername())){
                            found2 = true;
                        }

                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("Playlist id has to be a number");
                keyboard.next();
                found2 = false;
            }
        }

        SongDao songDao1 = new SongDaoImpl("database.properties");

        ArrayList<Song> song = songDao1.getAllSongs();
        int songID = 0;
        boolean found = false;
        while(!found) {
            try {
                System.out.println("Enter Song ID");
                songID = keyboard.nextInt();


                for (int i = 0; i < song.size();i++){
                    if (song.get(i).getSongID() == songID){

                        found = true;
                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("SongID has to be a number");
                keyboard.next();
                found = false;
            }
        }


        PlaylistSong playlistSong = new PlaylistSong(playlistID,songID);

        boolean done = addNewSongToPlaylist(playlistSong);
        if(done){
            System.out.println("SongId: "+songID+" has been add to playlistID: "+ playlistID);
        }
        return done;
    }
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
    public boolean removeSongFromPlaylistUser(User user){

        PlaylistDao playlistDao1 = new PlaylistDaoImpl("database.properties");

        ArrayList<Playlist> playlists = playlistDao1.getAllPlaylists();

        int playlistID = 0;
        boolean found2 = false;
        while(!found2) {

            try {
                System.out.println("Enter Playlist ID");
                playlistID = keyboard.nextInt();

                for (int i = 0; i < playlists.size();i++){
                    if (playlists.get(i).getPlaylistID() == playlistID){

                        if (playlists.get(i).isStatusPrivate() == false || playlists.get(i).getUsername().equals(user.getUsername())){
                            found2 = true;
                        }

                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("Playlist id has to be a number");
                keyboard.next();
                found2 = false;
            }
        }

        ArrayList<PlaylistSong> playlistSongs = getPlaylistsByID(playlistID);
        int songID = 0;
        boolean found = false;
        while(!found) {
            try {
                System.out.println("Enter Song ID");
                songID = keyboard.nextInt();


                for (int i = 0; i < playlistSongs.size();i++){
                    if (playlistSongs.get(i).getSongID() == songID){

                        found = true;
                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("SongID has to be a number");
                keyboard.next();
                found = false;
            }
        }


        PlaylistSong playlistSong = new PlaylistSong(playlistID,songID);

        boolean done = removingSongFromPlayList(playlistSong);
        if(done){
            System.out.println("SongId: "+songID+" has been removed from playlistID: "+ playlistID);
        }
        return done;
    }

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

    private PlaylistSong mapRow(ResultSet rs)throws SQLException{

        PlaylistSong a = new PlaylistSong(
                rs.getInt("playlistID"),
                rs.getInt("songID")
        );
        return a;
    }





}
