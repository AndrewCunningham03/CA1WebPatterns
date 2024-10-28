package CA1.persistence;

import CA1.business.Song;

import java.util.ArrayList;

/**
 * @author Toby
 * @author Andrew
 */
public interface SongDao {

    public Song findSongById(int songID);
    public ArrayList<Song> getAllSongs();
    public ArrayList<Song> getSongByTitle(String songTitle);
    public ArrayList<Song> getSongByArtist(int artistID);
    public ArrayList<Song> getSongByAlbum(int albumID);
}
