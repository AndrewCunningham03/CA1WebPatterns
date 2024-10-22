package CA1.business;

import java.util.ArrayList;
import java.util.Objects;

public class Playlist {

    private int privateID;
    private String playlistName;
    private String username;
    private boolean statusPrivate;
    private ArrayList<Song> playlist;

    public Playlist(int privateID, String playlistName, String username, boolean statusPrivate, ArrayList<Song> playlist) {
        this.privateID = privateID;
        this.playlistName = playlistName;
        this.username = username;
        this.statusPrivate = statusPrivate;
        this.playlist = playlist;
    }


    public int getPrivateID() {
        return privateID;
    }

    public void setPrivateID(int privateID) {
        this.privateID = privateID;
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
        return privateID == playlist1.privateID && statusPrivate == playlist1.statusPrivate && Objects.equals(playlistName, playlist1.playlistName) && Objects.equals(username, playlist1.username) && Objects.equals(playlist, playlist1.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privateID, playlistName, username, statusPrivate, playlist);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "privateID=" + privateID +
                ", playlistName='" + playlistName + '\'' +
                ", username='" + username + '\'' +
                ", statusPrivate=" + statusPrivate +
                ", playlist=" + playlist +
                '}';
    }
}
