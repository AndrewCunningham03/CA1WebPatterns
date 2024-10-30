package CA1.persistence;

import CA1.business.Playlist;
import CA1.business.PlaylistSong;
import CA1.business.User;

import java.util.ArrayList;
/**
 * @author Andrew Cunningham
 */
public interface PlaylistSongDao {

    public int addNewSongToPlaylist(PlaylistSong song);
    public int removingSongFromPlayList(PlaylistSong song);
    public ArrayList<PlaylistSong> getPlaylistsByID(int playlistID);
    public ArrayList<PlaylistSong> getAllPlaylistSongs();

}
