package CA1.persistence;

import CA1.business.Playlist;
import CA1.business.User;

import java.util.ArrayList;

public interface PlaylistDao  {
    public ArrayList<Playlist> getAllPlaylists();

    public boolean createNewPlaylist(User user);

}
