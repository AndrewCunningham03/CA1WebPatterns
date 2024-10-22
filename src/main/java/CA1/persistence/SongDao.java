package CA1.persistence;

import CA1.business.Song;

public interface SongDao {

    public Song findSongById(int songID);
}
