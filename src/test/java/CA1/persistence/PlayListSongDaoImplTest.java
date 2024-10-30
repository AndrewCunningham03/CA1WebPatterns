package CA1.persistence;

import CA1.business.Playlist;
import CA1.business.PlaylistSong;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andrew Cunningham
 */
public class PlayListSongDaoImplTest {

    private MySQLDao connectionSource = new MySQLDao("database_test.properties");

    /**
     * Insert new song to playlist success
     * @throws SQLException will give you database error
     */
    @Test
    void addNewSongToPlaylist() throws SQLException {
        System.out.println("Test for successful insertion of a new playlist");

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistSongDao playlistSongDao = new PlaylistSongDaoImpl(conn);

        PlaylistSong newSong = new PlaylistSong(1, 2);
        int result = playlistSongDao.addNewSongToPlaylist(newSong);

        assertEquals(1,result);
        ArrayList<PlaylistSong> listOfSongs = playlistSongDao.getPlaylistsByID(1);
        boolean resultTwo = listOfSongs.contains(newSong);
        assertTrue(resultTwo);
        conn.rollback();
    }
    /**
     * Insert song into playlist that already exists
     * @throws SQLException will give you database error
     */
    @Test
    void addNewSongToPlaylistAlreadyExists() throws SQLException {
        System.out.println("Test for song that already exists");

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistSongDao playlistSongDao = new PlaylistSongDaoImpl(conn);

        PlaylistSong newSong = new PlaylistSong(1, 3);
        int result = playlistSongDao.addNewSongToPlaylist(newSong);

        assertNotEquals(1,result);
        conn.rollback();
    }
    /**
     * Insert song into playlist that already exists
     * @throws SQLException will give you database error
     */
    @Test
    void addNewSongToPlaylistSongDoesntExist() throws SQLException {
        System.out.println("Test for song that doesnt exist");

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistSongDao playlistSongDao = new PlaylistSongDaoImpl(conn);

        PlaylistSong newSong = new PlaylistSong(1, 123);
        int result = playlistSongDao.addNewSongToPlaylist(newSong);

        assertNotEquals(1,result);
        conn.rollback();
    }

    /**
     * Removing successfully
     * @throws SQLException will give you database error
     */
    @Test
    void removingSongFromPlayList() throws SQLException {
        System.out.println("Test for removing a song from a playlist");

        PlaylistSong songToRemove = new PlaylistSong(1, 3);

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistSongDao playlistSongDao = new PlaylistSongDaoImpl(conn);

        int result = playlistSongDao.removingSongFromPlayList(songToRemove);
        assertEquals(1,result);
        ArrayList<PlaylistSong> songsInPlaylist = playlistSongDao.getPlaylistsByID(songToRemove.getPlaylistID());
        assertFalse(songsInPlaylist.contains(songToRemove));
        conn.rollback();
    }

    /**
     * Trying to remove a song not there
     * @throws SQLException will give you database error
     */
    @Test
    void removingSongFromPlayListThatIsNotOnPlaylist() throws SQLException {
        System.out.println("Test for removing a song that doesn't exist from a playlist");

        PlaylistSong songToRemove = new PlaylistSong(123, 3);

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistSongDao playlistSongDao = new PlaylistSongDaoImpl(conn);

        int result = playlistSongDao.removingSongFromPlayList(songToRemove);
        assertNotEquals(1,result);
        ArrayList<PlaylistSong> songsInPlaylist = playlistSongDao.getPlaylistsByID(songToRemove.getPlaylistID());
        assertFalse(songsInPlaylist.contains(songToRemove));
        conn.rollback();
    }

    /**
     * Searching for all songs
     * @throws SQLException will give you database error
     */
    @Test
    void GetAllPlaylistSongs() throws SQLException {
        System.out.println("Testing getting all the playlist songs");
        ArrayList<PlaylistSong> expectedSongs = new ArrayList<>();

        expectedSongs.add(new PlaylistSong(1, 3));
        expectedSongs.add(new PlaylistSong(2, 5));
        expectedSongs.add(new PlaylistSong(3, 4));
        expectedSongs.add(new PlaylistSong(4, 1));
        expectedSongs.add(new PlaylistSong(4, 2));
        expectedSongs.add(new PlaylistSong(5,1));

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistSongDao playlistSongDao = new PlaylistSongDaoImpl(conn);

        ArrayList<PlaylistSong> result = playlistSongDao.getAllPlaylistSongs();

        assertEquals(expectedSongs.size(), result.size());

        for (int i = 0; i < expectedSongs.size(); i++) {
            assertEquals(expectedSongs.get(i), result.get(i));
        }
        conn.rollback();
    }

    /**
     *  getting all songs by playlist id
     * @throws SQLException will give you database error
     */
    @Test
    void GetPlaylistsByID() throws SQLException {
        System.out.println("Testing getting playlists by ID");

        ArrayList<PlaylistSong> expectedSongs = new ArrayList<>();
        expectedSongs.add(new PlaylistSong(1, 3));

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistSongDao playlistSongDao = new PlaylistSongDaoImpl(conn);

        ArrayList<PlaylistSong> result = playlistSongDao.getPlaylistsByID(1);

        assertEquals(expectedSongs.size(), result.size());

        for (int i = 0; i < expectedSongs.size(); i++) {
            assertEquals(expectedSongs.get(i), result.get(i), "Each song in the playlist should match expected values");
        }
        conn.rollback();
    }


}
