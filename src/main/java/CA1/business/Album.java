package CA1.business;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Album {
    private String albumName;
    private String artistName;
    private int numberOfSongs;
    private Date releaseDate;
    private Time albumLength;

    public Album(String albumName, String artistName, int numberOfSongs, Date releaseDate, Time albumLength) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.numberOfSongs = numberOfSongs;
        this.releaseDate = releaseDate;
        this.albumLength = albumLength;
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

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Time getAlbumLength() {
        return albumLength;
    }

    public void setAlbumLength(Time albumLength) {
        this.albumLength = albumLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album album)) return false;
        return numberOfSongs == album.numberOfSongs && Objects.equals(albumName, album.albumName) && Objects.equals(artistName, album.artistName) && Objects.equals(releaseDate, album.releaseDate) && Objects.equals(albumLength, album.albumLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumName, artistName, numberOfSongs, releaseDate, albumLength);
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumName='" + albumName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", numberOfSongs=" + numberOfSongs +
                ", releaseDate=" + releaseDate +
                ", albumLength=" + albumLength +
                '}';
    }
}
