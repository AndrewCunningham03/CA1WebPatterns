package CA1.business;

import java.sql.Time;
import java.util.Objects;

public class Songs {

    private int songID;
    private String songName;
    private int albumID;
    private int artistID;
    private Time songLength;

    public Songs(int songID, String songName, int albumID, int artistID, Time songLength) {
        this.songID = songID;
        this.songName = songName;
        this.albumID = albumID;
        this.artistID = artistID;
        this.songLength = songLength;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public Time getSongLength() {
        return songLength;
    }

    public void setSongLength(Time songLength) {
        this.songLength = songLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Songs songs = (Songs) o;
        return songID == songs.songID && albumID == songs.albumID && artistID == songs.artistID && Objects.equals(songName, songs.songName) && Objects.equals(songLength, songs.songLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songID, songName, albumID, artistID, songLength);
    }

    @Override
    public String toString() {
        return "Songs{" +
                "songID=" + songID +
                ", songName='" + songName + '\'' +
                ", albumID=" + albumID +
                ", artistID=" + artistID +
                ", songLength=" + songLength +
                '}';
    }
}
