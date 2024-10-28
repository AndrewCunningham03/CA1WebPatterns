package CA1.persistence;

import CA1.business.Artist;

import java.util.ArrayList;
/**
 * @author Toby
 * @author Andrew
 */
public interface ArtistDao {

    public ArrayList<Artist> getAllArtist();
    public Artist findArtistById(int artistID);
}
