package CA1.persistence;

import CA1.business.Rating;

import java.util.ArrayList;

public interface RatingDao {

    public Rating rateSong();
    public boolean implementRatingSong(Rating rating);

    public ArrayList<Rating> getAllRatedSongsAndRating();

    public int getTopRatedSong();

    public int getMostPopularSong();
}
