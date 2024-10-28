package CA1.business;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * @author Toby
 * @author Andrew
 */
public class Album {
    private int albumID;
    private String albumName;
    private int artistID;
    private int numberOfSongs;
    private Date releaseDate;
    private Time albumLength;

    public Album(int albumID, String albumName, int artistID, int numberOfSongs, Date releaseDate, Time albumLength) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.artistID = artistID;
        this.numberOfSongs = numberOfSongs;
        this.releaseDate = releaseDate;
        this.albumLength = albumLength;
    }


    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
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
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return albumID == album.albumID && artistID == album.artistID && numberOfSongs == album.numberOfSongs && Objects.equals(albumName, album.albumName) && Objects.equals(releaseDate, album.releaseDate) && Objects.equals(albumLength, album.albumLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumID, albumName, artistID, numberOfSongs, releaseDate, albumLength);
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumID=" + albumID +
                ", albumName='" + albumName + '\'' +
                ", artistID=" + artistID +
                ", numberOfSongs=" + numberOfSongs +
                ", releaseDate=" + releaseDate +
                ", albumLength=" + albumLength +
                '}';
    }
}
