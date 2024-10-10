package CA1.business;

import java.sql.Time;
import java.util.Objects;

public class Songs {
    private String songName;
    private String albumName;
    private String artistName;
    private Time songLength;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Time getSongLength() {
        return songLength;
    }

    public void setSongLength(Time songLength) {
        this.songLength = songLength;
    }

    public Songs(String songName, String albumName, String artistName, Time songLength) {
        this.songName = songName;
        this.albumName = albumName;
        this.artistName = artistName;
        this.songLength = songLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Songs songs)) return false;
        return Objects.equals(songName, songs.songName) && Objects.equals(albumName, songs.albumName) && Objects.equals(artistName, songs.artistName) && Objects.equals(songLength, songs.songLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songName, albumName, artistName, songLength);
    }

    @Override
    public String toString() {
        return "Songs{" +
                "songName='" + songName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", songLength=" + songLength +
                '}';
    }
}
