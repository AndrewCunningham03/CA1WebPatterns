package CA1.persistence;

import CA1.business.PlaylistSong;
import CA1.business.User;

public interface PlaylistSongDao {

    public boolean addNewSongToPlaylist(PlaylistSong song);

    public boolean addNewSongToPlaylistUser(User user);
    public boolean removingSongFromPlayList(int playListName, int songID);

}
