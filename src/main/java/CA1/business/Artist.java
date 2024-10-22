package CA1.business;

import java.util.Date;
import java.util.Objects;

public class Artist {

    private int artistID;
    private String artistName;
    private String genre;
    private String hometown;
    private Date dateOfBirth;

    public Artist(int artistID, String artistName, String genre, String hometown, Date dateOfBirth) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.genre = genre;
        this.hometown = hometown;
        this.dateOfBirth = dateOfBirth;
    }


    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return artistID == artist.artistID && Objects.equals(artistName, artist.artistName) && Objects.equals(genre, artist.genre) && Objects.equals(hometown, artist.hometown) && Objects.equals(dateOfBirth, artist.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistID, artistName, genre, hometown, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistID=" + artistID +
                ", artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
                ", hometown='" + hometown + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
