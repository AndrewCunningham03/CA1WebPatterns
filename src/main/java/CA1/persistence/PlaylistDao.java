package CA1.persistence;

import CA1.business.Playlist;
import CA1.business.User;

import java.util.ArrayList;

public interface PlaylistDao  {
    public ArrayList<Playlist> getAllPlaylists();

    public int numberOfPlaylists();
    public boolean insertNewPlaylists(Playlist newPlaylist);

    public boolean createPlaylist(User user);
    public Playlist getPlaylistsByID(int playlistID);

}
