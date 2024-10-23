package CA1.persistence;

import CA1.business.Rating;
import CA1.business.Song;

import java.util.ArrayList;

public interface RatingDao {

    public Rating rateSong();
    public boolean implementRatingSong(Rating rating);

    public ArrayList<Rating> getAllRatings();

    public Song getTopRatedSong();

    public Song getMostPopularSong();

    public Rating findRatingByUsernameAndSongID(String username, int songID);

    public double getUserRatingFromUsernameAndSongID(String username, int songID);
}
