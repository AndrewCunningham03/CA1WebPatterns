package CA1.persistence;

import CA1.business.Playlist;
import CA1.business.PlaylistSong;
import CA1.business.User;

import java.util.ArrayList;

public interface PlaylistSongDao {

    public boolean addNewSongToPlaylist(PlaylistSong song);

    public boolean addNewSongToPlaylistUser(User user);
    public boolean removingSongFromPlayList(PlaylistSong song);
    public boolean removeSongFromPlaylistUser(User user);
    public ArrayList<PlaylistSong> getPlaylistsByID(int playlistID);
    public ArrayList<PlaylistSong> getAllPlaylistSongs();

}
