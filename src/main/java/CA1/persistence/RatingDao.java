package CA1.persistence;

import CA1.business.Rating;
import CA1.business.Song;

import java.util.ArrayList;

/**
 * @author Toby
 */
public interface RatingDao {


    //public boolean implementRatingSong(Rating rating);

    public int implementRatingSong(Rating rating);

    public ArrayList<Rating> getAllRatings();

    public Song getTopRatedSong();
    public Song getLowestRatedSong();

    public Song getMostPopularSong();
    public Rating findRatingByUsernameAndSongID(String username, int songID);

    public ArrayList<Rating> getUserRatingFromUsername(String username);


}
