package CA1.business;

public class Rating {
    private String username;
    private String songName;
    private int rating;

    public Rating(String username, String songName, int rating) {
        this.username = username;
        this.songName = songName;
        this.rating = rating;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "userName='" + username + '\'' +
                ", songName='" + songName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
