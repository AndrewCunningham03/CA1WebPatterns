package CA1.business;

import java.util.ArrayList;
import java.util.Objects;

public class Playlist {

    private int playlistID;
    private String playlistName;
    private String username;
    private boolean statusPrivate;
    private ArrayList<Song> playlist;

    public Playlist(int privateID, String playlistName, String username, boolean statusPrivate, ArrayList<Song> playlist) {
        this.playlistID = privateID;
        this.playlistName = playlistName;
        this.username = username;
        this.statusPrivate = statusPrivate;
        this.playlist = playlist;
    }


    public int getPrivateID() {
        return playlistID;
    }

    public void setPrivateID(int privateID) {
        this.playlistID = privateID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatusPrivate() {
        return statusPrivate;
    }

    public void setStatusPrivate(boolean statusPrivate) {
        this.statusPrivate = statusPrivate;
    }

    public ArrayList<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Song> playlist) {
        this.playlist = playlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist1 = (Playlist) o;
        return playlistID == playlist1.playlistID && statusPrivate == playlist1.statusPrivate && Objects.equals(playlistName, playlist1.playlistName) && Objects.equals(username, playlist1.username) && Objects.equals(playlist, playlist1.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistID, playlistName, username, statusPrivate, playlist);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "privateID=" + playlistID +
                ", playlistName='" + playlistName + '\'' +
                ", username='" + username + '\'' +
                ", statusPrivate=" + statusPrivate +
                ", playlist=" + playlist +
                '}';
    }
}
