package CA1.business;

import java.util.ArrayList;
import java.util.Objects;

public class Playlist {

    private int privateID;
    private String playlistName;
    private String username;
    private int numberOfSongs;
    private boolean statusPrivate;
    private ArrayList<Songs> playlist;

    public Playlist(int privateID, String playlistName, String username, int numberOfSongs, boolean statusPrivate, ArrayList<Songs> playlist) {
        this.privateID = privateID;
        this.playlistName = playlistName;
        this.username = username;
        this.numberOfSongs = numberOfSongs;
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

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public boolean isStatusPrivate() {
        return statusPrivate;
    }

    public void setStatusPrivate(boolean statusPrivate) {
        this.statusPrivate = statusPrivate;
    }

    public ArrayList<Songs> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Songs> playlist) {
        this.playlist = playlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist1 = (Playlist) o;
        return privateID == playlist1.privateID && numberOfSongs == playlist1.numberOfSongs && statusPrivate == playlist1.statusPrivate && Objects.equals(playlistName, playlist1.playlistName) && Objects.equals(username, playlist1.username) && Objects.equals(playlist, playlist1.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privateID, playlistName, username, numberOfSongs, statusPrivate, playlist);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "privateID=" + privateID +
                ", playlistName='" + playlistName + '\'' +
                ", username='" + username + '\'' +
                ", numberOfSongs=" + numberOfSongs +
                ", statusPrivate=" + statusPrivate +
                ", playlist=" + playlist +
                '}';
    }
}
