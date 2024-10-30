package CA1.persistence;

import CA1.business.Playlist;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Andrew Cunningham
 */
public class PlayListDaoImplTest {
    private MySQLDao connectionSource = new MySQLDao("database_test.properties");

    /**
     * View all playlists
     */
    @Test
    void getAllPlaylistsTest() {
        System.out.println("Test for retrieving all playlists");
        System.out.println("");

        PlaylistDao playlistDao = new PlaylistDaoImpl("database_test.properties");

        // Expected list of playlists
        List<Playlist> expected = new ArrayList<>();
        expected.add(new Playlist(1, "Rap Cavier", "Toby",false));
        expected.add(new Playlist(2, "best rnb playlist", "Sam",true));
        expected.add(new Playlist(3, "Happy Place", "John",true));
        expected.add(new Playlist(4, "Rap World", "Andrew",false));
        expected.add(new Playlist(5, "Rock Life", "Toby",false));

        List<Playlist> result = playlistDao.getAllPlaylists();

        // Test size
        assertEquals(expected.size(), result.size());

        assertFalse(result.isEmpty());

        // Test if all elements in the list are the same
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

    /**
     * No matching elements
     */
    @Test
    void getAllPlaylistsTestNoMatching() {
        System.out.println("Test for retrieving all playlists but no match");
        System.out.println("");

        PlaylistDao playlistDao = new PlaylistDaoImpl("database_test.properties");

        // Expected list of playlists, populated with known values
        List<Playlist> expected = new ArrayList<>();
        expected.add(new Playlist(1, "Rap ", "toby b",true));
        expected.add(new Playlist(2, "best ", "sam b",false));
        expected.add(new Playlist(3, "Happy ", "John b",false));
        expected.add(new Playlist(4, "Rap ", "Andrew b",true));
        expected.add(new Playlist(5, "Rock ", "Toby b",true));
        // Add more expected playlists as needed based on test data

        List<Playlist> result = playlistDao.getAllPlaylists();

        // Test size
        assertEquals(expected.size(), result.size());

        assertFalse(result.isEmpty());

        // Test if all elements in the list are the same
        for (int i = 0; i < result.size(); i++) {
            assertNotEquals(expected.get(i), result.get(i));
        }
    }
    /**
     * get playlist by id
     */
    @Test
    void getAllPlaylistsByID() {
        System.out.println("Test for retrieving all playlists by id");
        System.out.println("");

        PlaylistDao playlistDao = new PlaylistDaoImpl("database_test.properties");


        Playlist expected = new Playlist(1, "Rap Cavier", "Toby",false);

        int id = 1;

        Playlist result = playlistDao.getPlaylistsByID(id);

        assertEquals(expected,result);
        }
    /**
     * get playlist by an id that doesn't exist
     */
    @Test
    void getAllPlaylistsByIDIncorrectIDBy() {
        System.out.println("Test for retrieving all playlists by an id that doesn't exist");
        System.out.println("");

        PlaylistDao playlistDao = new PlaylistDaoImpl("database_test.properties");


        Playlist expected = new Playlist(1, "Rap Cavier", "Toby",false);

        int id = -1;

        Playlist result = playlistDao.getPlaylistsByID(id);

        assertNotEquals(expected,result);
    }
    /**
     * Getting number of playlists
     */
    @Test
    void numberOfPlaylists() {
        System.out.println("Test for number of playlists");
        System.out.println("");

        PlaylistDao playlistDao = new PlaylistDaoImpl("database_test.properties");

        // Expected list of playlists, populated with known values
        List<Playlist> expected = new ArrayList<>();
        expected.add(new Playlist(1, "Rap ", "toby b",true));
        expected.add(new Playlist(2, "best ", "sam b",false));
        expected.add(new Playlist(3, "Happy ", "John b",false));
        expected.add(new Playlist(4, "Rap ", "Andrew b",true));
        expected.add(new Playlist(5, "Rock ", "Toby b",true));
        // Add more expected playlists as needed based on test data

        List<Playlist> result = playlistDao.getAllPlaylists();

        // Test size
        assertEquals(expected.size(), result.size());

        assertFalse(result.isEmpty());
    }
    /**
     * Getting number of playlists incorrect size
     */
    @Test
    void numberOfPlaylistsIncorrectSize() {
        System.out.println("Test for number of playlists incorrect size");
        System.out.println("");

        PlaylistDao playlistDao = new PlaylistDaoImpl("database_test.properties");

        // Expected list of playlists, populated with known values
        List<Playlist> expected = new ArrayList<>();
        expected.add(new Playlist(1, "Rap ", "toby b",true));
        expected.add(new Playlist(2, "best ", "sam b",false));
        expected.add(new Playlist(3, "Happy ", "John b",false));
        expected.add(new Playlist(4, "Rap ", "Andrew b",true));
        // Add more expected playlists as needed based on test data

        List<Playlist> result = playlistDao.getAllPlaylists();

        // Test size
        assertNotEquals(expected.size(), result.size());

        assertFalse(result.isEmpty());
    }

    /**
     * Insert new playlist success
     * @throws SQLException will give you database error
     */
    @Test
    void insertNewPlaylists() throws SQLException {
        System.out.println("Test for successful insertion of a new playlist");

        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistDao playlistDao = new PlaylistDaoImpl(conn);

        Playlist newPlaylist = new Playlist(6, "Jazz Vibes", "andrew", true);
        int result = playlistDao.insertNewPlaylists(newPlaylist);

        assertEquals(1,result);

        int numberOfPlaylists = playlistDao.numberOfPlaylists();

        assertEquals(6,numberOfPlaylists);

        Playlist playlist = playlistDao.getPlaylistsByID(6);
        assertEquals(playlist,newPlaylist);
    }
    /**
     * Insert new playlist that id is already taken
     * @throws SQLException will give you database error
     */
    @Test
    void insertNewPlaylistsThatIDIsAlreadyTaken() throws SQLException {
        System.out.println("Test for successful insertion of a new playlist");
        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistDao playlistDao = new PlaylistDaoImpl(conn);

        Playlist newPlaylist = new Playlist(1, "Jazz Vibes", "andrew", true);
        int result = playlistDao.insertNewPlaylists(newPlaylist);

        assertNotEquals(1,result);
        int numberOfPlaylists = playlistDao.numberOfPlaylists();

        assertNotEquals(newPlaylist.getPlaylistID(),numberOfPlaylists);

        Playlist playlist = playlistDao.getPlaylistsByID(1);
        assertNotEquals(playlist,newPlaylist);
    }
    /**
     * Insert new playlist invalid input null
     * @throws SQLException will give you database error
     */
    @Test
    void insertNewPlaylistsInvalidNullInput() throws SQLException {
        System.out.println("Test for successful insertion of a new playlist");
        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistDao playlistDao = new PlaylistDaoImpl(conn);

        Playlist newPlaylist = new Playlist(1, null, "andrew", true);
        int result = playlistDao.insertNewPlaylists(newPlaylist);

        assertNotEquals(1,result);
    }

    /**
     * Successfully updating playlist name
     * @throws SQLException will give you database error
     */
    @Test
    void updatePlaylistNameSuccessfulUpdateTest() throws SQLException {
        System.out.println("Test for successful playlist name update");
        Connection conn = connectionSource.getConnection();
        conn.setAutoCommit(false);
        PlaylistDao playlistDao = new PlaylistDaoImpl(conn);

        int result = playlistDao.updatePlaylistName("Rap Cavier", "testing");
        assertEquals(1,result);

        Playlist updatedPlaylist = playlistDao.getPlaylistsByID(1);

        assertEquals("testing", updatedPlaylist.getPlaylistName());
    }


}
