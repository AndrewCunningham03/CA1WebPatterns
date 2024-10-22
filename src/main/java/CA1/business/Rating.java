package CA1.business;

import java.util.Objects;

public class Rating {
    private String username;
    private int songID;
    private double userRating ;

    public Rating(String username, int songID, double userRating) {
        this.username = username;
        this.songID = songID;
        this.userRating = userRating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return songID == rating.songID && Double.compare(userRating, rating.userRating) == 0 && Objects.equals(username, rating.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, songID, userRating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "username='" + username + '\'' +
                ", songID=" + songID +
                ", userRating=" + userRating +
                '}';
    }
}
