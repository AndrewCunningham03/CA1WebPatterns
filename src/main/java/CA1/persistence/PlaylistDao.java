package CA1.persistence;

import CA1.business.Playlist;
import CA1.business.User;

import java.util.ArrayList;

/**
 * @author Andrew Cunningham
 */
public interface PlaylistDao  {
    public ArrayList<Playlist> getAllPlaylists();
    public int updatePlaylistName(String playlistName, String name);
    public int numberOfPlaylists();
    public int insertNewPlaylists(Playlist newPlaylist);
    public Playlist getPlaylistsByID(int playlistID);
}
