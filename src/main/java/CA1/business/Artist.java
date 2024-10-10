package CA1.business;

import java.util.Date;
import java.util.Objects;

public class Artist {

    private String artistName;
    private String genre;
    private String hometown;
    private Date dateOfBirth;

    /// constructors
    public Artist(String artistName, String genre, String hometown, Date dateOfBirth) {
        this.artistName = artistName;
        this.genre = genre;
        this.hometown = hometown;
        this.dateOfBirth = dateOfBirth;
    }

    /// setters and getters
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


    /// equals and hash


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(artistName, artist.artistName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistName);
    }

    /// toString


    @Override
    public String toString() {
        return "Artist{" +
                "artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
                ", hometown='" + hometown + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
