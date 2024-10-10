package CA1.business;

public class PlaylistSong {
//playlistName varchar(50) NOT NULL,
//    songName     varchar(50) NOT NULL,
    private String playlistName;
    private String songName;

    public PlaylistSong(String playlistName, String songName) {
        this.playlistName = playlistName;
        this.songName = songName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "PlaylistSong{" +
                "playlistName='" + playlistName + '\'' +
                ", songName='" + songName + '\'' +
                '}';
    }
}
