package CA1.persistence;

import CA1.business.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Toby
 * @author Andrew
 */
public interface AlbumDao {
    public List<Album> viewAllAlbumsFromArtist(int artistName);
    public Album findAlbumById(int albumID);

    public ArrayList<Album> getAllAlbums();

}
