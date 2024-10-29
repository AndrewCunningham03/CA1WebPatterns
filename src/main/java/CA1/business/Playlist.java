package CA1.business;

import java.util.ArrayList;
import java.util.Objects;
/**
 * @author Toby
 * @author Andrew
 */
public class Playlist {

    private int playlistID;
    private String playlistName;
    private String username;
    private boolean statusPrivate;


    public Playlist(int playlistID, String playlistName, String username, boolean statusPrivate) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.username = username;
        this.statusPrivate = statusPrivate;

    }


    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int privateID) {
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist1 = (Playlist) o;
        return playlistID == playlist1.playlistID && statusPrivate == playlist1.statusPrivate && Objects.equals(playlistName, playlist1.playlistName) && Objects.equals(username, playlist1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistID, playlistName, username, statusPrivate);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "privateID=" + playlistID +
                ", playlistName='" + playlistName + '\'' +
                ", username='" + username + '\'' +
                ", statusPrivate=" + statusPrivate +
                '}';
    }
}
