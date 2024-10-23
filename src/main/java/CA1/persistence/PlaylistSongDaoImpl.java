package CA1.persistence;

public class PlaylistSongDaoImpl extends MySQLDao {
    public PlaylistSongDaoImpl(String databaseName){
        super(databaseName);
    }

    public PlaylistSongDaoImpl(){
        super();
    }

}
