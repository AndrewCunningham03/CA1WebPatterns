package CA1.persistence;

import CA1.business.Album;

import java.util.ArrayList;
import java.util.List;

public interface AlbumDao {
    public List<Album> viewAllAlbumsFromArtist(String artistName);
}
