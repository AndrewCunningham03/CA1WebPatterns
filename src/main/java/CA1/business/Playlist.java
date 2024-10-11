package CA1.business;

import java.util.ArrayList;

public class Playlist {

    private String playlistName;
    private int numberOfSongs;
    private boolean statusPrivate;
    private ArrayList<Songs> playlist;

    public Playlist(String playlistName, int numberOfSongs, boolean statusPrivate) {
        this.playlistName = playlistName;
        this.numberOfSongs = numberOfSongs;
        this.statusPrivate = statusPrivate;
        playlist = new ArrayList<Songs>();
    }

    public ArrayList<Songs> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Songs> playlist) {
        this.playlist = playlist;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public boolean isStatusPrivate() {
        return statusPrivate;
    }

    public void setStatusPrivate(boolean statusPrivate) {
        this.statusPrivate = statusPrivate;
    }

    public boolean addSong(Songs song){
        if(!playlist.contains(song)){
            playlist.add(song);
            numberOfSongs++;
            return true;
        }
        return false;
    }
    public boolean deleteSong(String songName){
        for(Songs song : playlist){
            if(song.getSongName().equalsIgnoreCase(songName)){
                playlist.remove(song);
                numberOfSongs--;
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "Playlist{" +
                "playlistName='" + playlistName + '\'' +
                ", numberOfSongs=" + numberOfSongs +
                ", statusPrivate=" + statusPrivate +
                '}';
    }
}
