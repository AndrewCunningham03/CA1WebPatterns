package CA1.applications;

import CA1.business.*;
import CA1.persistence.*;


import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Toby
 * @author Andrew
 */
public class App {

    static User user = new User();

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Scanner keyboard = new Scanner(System.in);

        /// login and register

        boolean repeat = false;

        while(!repeat) {

            try {
                int num = 0;

                boolean result = false;

                while (!result) {


                    String[] array = new String[2];

                    array[0] = "1. Register: ";
                    array[1] = "2. Login: ";
                    System.out.println("");

                    for (int i = 0; i < array.length; i++) {
                        System.out.println(array[i]);
                    }

                    System.out.println("Enter Number: ");
                    num = keyboard.nextInt();

                    switch (num) {


                        case 1:
                            registerUser(keyboard);
                            break;

                        case 2:
                            result = loginForUser(keyboard);
                            break;

                    }

                    repeat = true;
                }
            }catch (InputMismatchException ex){
                System.out.println("A number has to be entered between 1 and 2");
                keyboard.nextLine();
                repeat = false;
            }


        }

        /// Artist, Album and Song

        boolean repeat1 = false;

        while(!repeat1) {

            try {

                int num2 = 0;


                while (num2 != 11) {
                    String[] array2 = new String[11];

                    array2[0] = "1. View all artist in the library";
                    array2[1] = "2. Search for artist by artistID";
                    array2[2] = "3. View all albums for an artist";
                    array2[3] = "4. Search for album by albumID";
                    array2[4] = "5. View all albums in the library";
                    array2[5] = "6. Search for Song by SongID";
                    array2[6] = "7. View all songs in an album";
                    array2[7] = "8. Search for song by artist";
                    array2[8] = "9. Search for song by album";
                    array2[9] = "10. Search for song by title";
                    array2[10] = "11. Exit";

                    for (int i = 0; i < array2.length; i++) {
                        System.out.println(array2[i]);
                    }

                    System.out.println("Enter number: ");
                    num2 = keyboard.nextInt();

                    if (num2 <= 0 || num2 > array2.length) {
                        System.out.println("Number is not on menu");
                    }

                    switch (num2) {

                        case 1:
                            getAllArtist();
                            break;
                        case 2:
                            getArtistByID(keyboard);
                            break;
                        case 3:
                            viewAlbumsFromArtist(keyboard);
                            break;
                        case 4:
                            getAlbumByID(keyboard);
                            break;
                        case 5:
                            getAllAlbums();
                            break;
                        case 6:

                            getSongByID(keyboard);
                            break;
                        case 7:
                            getAllSongsinAlbum();
                            break;
                        case 8:
                            getSongsByArtist();
                            break;
                        case 9:
                            System.out.println("");
                            break;
                        case 10:
                            System.out.println("");
                            break;
                        case 11:
                            break;
                    }

                    repeat1 = true;
                }
            }catch (InputMismatchException ex){
                System.out.println("A number has to be entered");
                keyboard.nextLine();
                repeat1 = false;
            }

        }





        //// Rating

        boolean repeat2 = false;

        while(!repeat2) {

            try {
                int num3 = 0;

                while (num3 != 8) {
                    String[] array3 = new String[8];

                    array3[0] = "1. Rate a song from 1-5";
                    array3[1] = "2. Select a particular song you have rated and see the rating with the song ";
                    array3[2] = "3. Get top rated song";
                    array3[3] = "4. Get the most popular song";
                    array3[4] = "5. View all songs you have rated and there rating";
                    array3[5] = "6. Get most popular song that are in playlist";
                    array3[6] = "7. Get the song with the lowest average rating";
                    array3[7] = "8. Exit";

                    for (int i = 0; i < array3.length; i++) {
                        System.out.println(array3[i]);
                    }

                    System.out.println("Enter number: ");
                    num3 = keyboard.nextInt();

                    if (num3 <= 0 || num3 > array3.length) {
                        System.out.println("Number is not on menu");
                    }

                    switch (num3) {

                        case 1:
                            userRatingSong();
                            break;
                        case 2:
                            selectSongToSeeRating(keyboard);
                            break;
                        case 3:
                            getTopRatedSong();
                            break;
                        case 4:
                            getMostPopularSong();
                            break;
                        case 5:
                            viewAllSongsYouHaveRatedAndThereRating();
                            break;
                        case 6:
                            getMostPopularSongInPlaylist();
                            break;
                        case 7:
                            LowestRatedSong();
                            break;
                        case 8:
                            break;

                    }

                    repeat2 = true;
                }
            }catch (InputMismatchException ex){
                System.out.println("A number has to be entered");
                keyboard.nextLine();
                repeat2 = false;
            }

        }


        /// Playlist and PlaylistSong


        boolean repeat3 = false;

        while(!repeat3) {

            try {

                PlaylistDao playlistDao = new PlaylistDaoImpl("database.properties");

                PlaylistSongDaoImpl playlistSongDao = new PlaylistSongDaoImpl("database.properties");

                int num4 = 0;
                while (num4 != 7) {
                    String[] array4 = new String[7];
                    array4[0] = "1. View all Playlists";
                    array4[1] = "2. Create new Playlist";
                    array4[2] = "3. Add a song to PlayList";
                    array4[3] = "4. Remove song from Playlist";
                    array4[4] = "5. Change playlist name";
                    array4[5] = "6. View all songs in playlist";
                    array4[6] = "7. Exit";
                    for (int i = 0; i < array4.length; i++) {
                        System.out.println(array4[i]);
                    }
                    System.out.println("Enter number: ");
                    num4 = keyboard.nextInt();
                    if (num4 <= 0 || num4 > array4.length) {
                        System.out.println("Number is not on menu");
                    }
                    switch (num4) {
                        case 1:
                            ArrayList<Playlist> allPlaylists = getAllPlaylistsUser();
                            for (Playlist p : allPlaylists) {
                                System.out.println("Playlist: " + p);
                            }
                            break;
                        case 2:
                            createPlaylist();
                            break;
                        case 3:
                            addNewSongToPlaylistUser();
                            break;
                        case 4:
                            removeSongFromPlaylistUser();
                            break;
                        case 5:
                            updatePlaylistNameUser();
                            break;
                        case 6:
                            viewSongsInPlaylist();
                            break;
                        case 7:
                            System.out.println("Exiting....");
                            break;
                    }

                    repeat3 = true;
                }
            }catch (InputMismatchException ex){
                System.out.println("A number has to be entered");
                keyboard.nextLine();
                repeat3 = false;
            }
        }


    }

    private static void LowestRatedSong() {
        RatingDao  ratingDao6 = new RatingDaoImpl("database.properties");

        System.out.println(ratingDao6.getLowestRatedSong());
    }

    private static void getMostPopularSongInPlaylist() {
        PlaylistSongDao playlistSongDao = new PlaylistSongDaoImpl("database.properties");
        SongDao songDao2 = new SongDaoImpl("database.properties");
        ArrayList<PlaylistSong> playlist = playlistSongDao.getAllPlaylistSongs();

        PlaylistSong p1 = playlist.get(0);
        int max = 0;


        for (int i = 0; i < playlist.size();i++){

            int count = 0;
          for (int j = 0; j < playlist.size();j++){

              if (playlist.get(i).getSongID() == playlist.get(j).getSongID()){

                 count++;

              }
          }

          if (count > max){
              max = count;
              p1 = playlist.get(i);
          }

        }

        System.out.println(songDao2.findSongById(p1.getSongID()));
    }

    private static void viewAllSongsYouHaveRatedAndThereRating() {
        RatingDao ratingDao5 = new RatingDaoImpl("database.properties");
        SongDao songDao1 = new SongDaoImpl("database.properties");

        ArrayList<Rating> list = ratingDao5.getUserRatingFromUsername(user.getUsername());

        System.out.println("Songs that " +user.getUsername() + " has rated");
        System.out.println("");
        for (int i = 0;i < list.size();i++){

            System.out.println("Song: " +songDao1.findSongById(list.get(i).getSongID()) + " ,Rating: " +list.get(i).getUserRating());
            System.out.println("------------------");
        }
    }

    private static void getMostPopularSong() {
        RatingDao  ratingDao4 = new RatingDaoImpl("database.properties");

        System.out.println(ratingDao4.getMostPopularSong());
    }

    private static void getTopRatedSong() {
        RatingDao  ratingDao3 = new RatingDaoImpl("database.properties");

        System.out.println(ratingDao3.getTopRatedSong());
    }

    private static void selectSongToSeeRating(Scanner keyboard) {
        RatingDao ratingDao1 = new RatingDaoImpl("database.properties");


        ArrayList<Rating> rating = ratingDao1.getAllRatings();

        for (int i = 0; i < rating.size();i++){

            System.out.println(rating.get(i));
            System.out.println("---------------------");
        }

        SongDao songDao = new SongDaoImpl("database.properties");

        Boolean repeat2 = false;

        while(!repeat2) {

            try {


                int songID = 0;
                System.out.println("Enter songID: ");
                songID = keyboard.nextInt();


                for (int i = 0; i < rating.size(); i++) {

                    if (rating.get(i).getUsername().equals(user.getUsername()) && rating.get(i).getSongID() == songID) {

                        Song song = songDao.findSongById(songID);

                        System.out.println(song + ", Song Rating: " + ratingDao1.findRatingByUsernameAndSongID(user.getUsername(), songID));

                        repeat2 = true;
                    } else {

                    }
                }
            }catch (InputMismatchException ex){

                System.out.println("UserId has to be a number. Enter it again ");
                System.out.println("");

                repeat2 = false;
            }


        }
    }

    private static void userRatingSong() {
        RatingDao  ratingDao2 = new RatingDaoImpl("database.properties");

        System.out.println(ratingDao2.implementRatingSong(rateSong()));
    }

    private static void getSongByID(Scanner keyboard) {
        SongDao songDao = new SongDaoImpl("database.properties");

        Boolean repeat8 = false;

        while(!repeat8) {
            try {

                ArrayList<Song> song = songDao.getAllSongs();
                System.out.println("Choose between these songIDs: ");

                for (int i = 0;i < song.size();i++){
                    System.out.println(song.get(i).getSongID());
                }

                int songID;
                System.out.println("Enter songID: ");
                songID = keyboard.nextInt();

                for (int i = 0; i < song.size();i++){
                    if (song.get(i).getSongID() == songID){
                        System.out.println(songDao.findSongById(songID));
                        repeat8 = true;
                    }
                }

            }catch(InputMismatchException ex){
                System.out.println("SongID has to be a number ");
                keyboard.next();
                repeat8 = false;
            }
        }
    }

    private static void getAllAlbums() {
        AlbumDao albumDao4 = new AlbumDaoImpl("database.properties");

        ArrayList<Album> albums4 = albumDao4.getAllAlbums();

        for (Album a: albums4){
            System.out.println("Artist: " +a);
        }
    }

    private static void getAlbumByID(Scanner keyboard) {
        AlbumDao albumDao3 = new AlbumDaoImpl("database.properties");


        Boolean repeat7 = false;

        while(!repeat7) {

            try {
                ArrayList<Album> album = albumDao3.getAllAlbums();

                System.out.println("Choose from these albumIDs: ");
                for (int i = 0; i < album.size();i++){
                    System.out.println(album.get(i).getAlbumID());
                }
                System.out.println("");

                int albumID;
                System.out.println("Enter albumID: ");
                albumID = keyboard.nextInt();

                for (int i = 0; i < album.size();i++){
                    if (album.get(i).getAlbumID() == albumID){
                        System.out.println(albumDao3.findAlbumById(albumID));
                        repeat7 = true;
                    }else{

                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("Album ID has to be a number");
                keyboard.next();
                repeat7 = false;
            }
        }
    }

    private static void viewAlbumsFromArtist(Scanner keyboard) {
        AlbumDao albumDao = new AlbumDaoImpl("database.properties");

        Boolean repeat4 = false;
        while(!repeat4) {

            try {

                ArtistDaoImpl artistDao2 = new ArtistDaoImpl("database.properties");

                System.out.println("ArtistID to choose from: ");
                ArrayList<Artist> artist = artistDao2.getAllArtist();

                for (int i = 0; i < artist.size();i++){
                    System.out.println(artist.get(i).getArtistID());
                }
                System.out.println("");

                int artistID;
                System.out.println("Enter artistID: ");
                artistID = keyboard.nextInt();

                ArrayList<Album> album = albumDao.getAllAlbums();

                for (int i = 0; i < album.size();i++){
                    if (album.get(i).getArtistID() == artistID){
                        List<Album> albums = albumDao.viewAllAlbumsFromArtist(artistID);
                        System.out.println("Artist Album: "+albums);

                        repeat4 = true;
                    }else{

                    }
                }
            }catch (InputMismatchException ex){

                System.out.println("ArtistID has to be a number");
                keyboard.next();

                repeat4 = false;
            }
        }
    }

    private static void getArtistByID(Scanner keyboard) {
        ArtistDao artistDao1 = new ArtistDaoImpl("database.properties");

        Boolean repeat5 = false;

        while(!repeat5) {

            try {

                System.out.println("ArtistID to choose from: ");
                ArrayList<Artist> artist = artistDao1.getAllArtist();

                for (int i = 0; i < artist.size();i++){
                    System.out.println(artist.get(i).getArtistID());
                }
                System.out.println("");

                int artistID2;
                System.out.println("Enter artistID");
                artistID2 = keyboard.nextInt();

                for (int i = 0; i < artist.size();i++){

                    if (artist.get(i).getArtistID() == artistID2){
                        System.out.println(artistDao1.findArtistById(artistID2));
                        repeat5 = true;
                    }else{

                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("ArtistID has to be a number");
                keyboard.next();

                repeat5 = false;
            }
        }
    }

    private static void getAllArtist() {
        ArtistDao artistDao = new ArtistDaoImpl("database.properties");

        ArrayList<Artist> artists = artistDao.getAllArtist();

        for (Artist a: artists){
            System.out.println("Artist: " +a);
        }
    }

    private static boolean loginForUser(Scanner keyboard) throws InvalidKeySpecException, NoSuchAlgorithmException {
        boolean result;
        UserDao userDao1 = new UserDaoImpl("database.properties");

        String email;
        System.out.println("Enter email: ");
        email = keyboard.next();

        String password;
        System.out.println("Enter password: ");
        password = keyboard.next();

        System.out.println(result = userDao1.loginUser(email,hashPassword(password)));
        if(result == true){

            User loggedInUser = new User(userDao1.findUserByThereEmail(email));

            user = loggedInUser;
        }
        return result;
    }

    private static void registerUser(Scanner keyboard) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserDao userDao = new UserDaoImpl("database.properties");

        boolean repeat = false;

        while(!repeat){

            String creditCard;

            System.out.println("Enter creditCard details for Visa Cards");
            creditCard = keyboard.next();

            Pattern pattern = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
            Matcher match = pattern.matcher(creditCard);
            boolean matchfound = match.find();

            if (matchfound){
                System.out.println("Correct card details");
                repeat = true;
            }else{

                System.out.println("Enter card details again");
                repeat = false;
            }
        }

        System.out.println(userDao.registerUser(createUserRegister()));
    }

    public static void viewSongsInPlaylist(){
        PlaylistDao playlistDao1 = new PlaylistDaoImpl("database.properties");

        PlaylistSongDao playlistSongDao =  new PlaylistSongDaoImpl("database.properties");

        ArrayList<Playlist> playlists = playlistDao1.getAllPlaylists();

        ArrayList<PlaylistSong> playlistSongs = new ArrayList<>();


        String playlistName = null;
        boolean found2 = false;
        while(!found2) {


                System.out.println("Enter Playlist name");
                playlistName = keyboard.nextLine();

                for (int i = 0; i < playlists.size();i++){
                    if (playlists.get(i).getPlaylistName().equalsIgnoreCase(playlistName)){

                        if (playlists.get(i).isStatusPrivate() == false || playlists.get(i).getUsername().equals(user.getUsername())){
                            int playlistID = playlists.get(i).getPlaylistID();
                        ArrayList<PlaylistSong> list =   playlistSongDao.getPlaylistsByID(playlistID);

                        for (int j = 0; j < list.size();j++){

                            playlistSongs.add(list.get(j));
                            found2=true;
                        }
                        }
                    }
                }
        }
        for(PlaylistSong p: playlistSongs){
            System.out.println("PlaylistSong:" + p);
        }
    }


    public static String hashPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        char[]passwordChars = password.toCharArray();
        byte [] saltBytes = "NotSoSecretSalt".getBytes();
        int iterations = 65536;
        int keySize = 256;

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

        PBEKeySpec spec = new PBEKeySpec(passwordChars,saltBytes,iterations,keySize);

        SecretKey key = factory.generateSecret(spec);

        String keyAsString = Base64.getEncoder().encodeToString(key.getEncoded());

        return keyAsString;

    }

    private static final Scanner keyboard = new Scanner(System.in);

    public static Rating rateSong(){

        SongDaoImpl songDao2 = new SongDaoImpl("database.properties");

        ArrayList<Song> list = songDao2.getAllSongs();

        System.out.println("Choose between these songIDs to rate: ");
        for (int i = 0; i < list.size();i++){
            System.out.println(list.get(i).getSongID());
        }
        System.out.println("");


        System.out.println("Enter songID");
        int songID = keyboard.nextInt();


        double rating = 0;

        boolean repeat = false;

        while(!repeat){

            System.out.println("Enter song rating: ");
            rating = keyboard.nextDouble();

            if (rating >=1 && rating <= 5){

                System.out.println("Thank you for the rating ");
                repeat = true;
            }else{
                System.out.println("Number has to be between 1 - 5: ");
                repeat = false;
            }
        }


        return new Rating(user.getUsername(),songID,rating);
    }

    private static final Scanner input = new Scanner(System.in);
    public static  User createUserRegister() throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println("Please enter the username:");
        String userName = input.nextLine();

        String email = null;

        boolean repeat = false;

        while(!repeat){

            System.out.println("Please enter the email with any letter or number before the @ symbol and after the @symbol :");
            email = input.nextLine();

            Pattern pattern = Pattern.compile("^(.+)@(.+)$");
            Matcher match = pattern.matcher(email);
            boolean matchfound = match.find();

            if (matchfound){
                System.out.println("Correct email details");
                repeat = true;
            }else{

                System.out.println("Enter email details again ");
                repeat = false;
            }
        }

        System.out.println("Please enter the password:");
        String password = input.nextLine();

        System.out.println("Please enter the userType");
        int userType = input.nextInt();


        return new User(userName, email, hashPassword(password), userType);
    }

    static void removeSongFromPlaylistUser(){

        PlaylistDao playlistDao1 = new PlaylistDaoImpl("database.properties");
        ArrayList<Playlist> playlists = playlistDao1.getAllPlaylists();

        PlaylistSongDao playlistSongDao1 = new PlaylistSongDaoImpl("database.properties");

        int playlistID = 0;
        boolean found2 = false;
        while(!found2) {

            try {
                System.out.println("Enter Playlist ID");
                playlistID = keyboard.nextInt();

                for (int i = 0; i < playlists.size();i++){
                    if (playlists.get(i).getPlaylistID() == playlistID){

                        if (playlists.get(i).isStatusPrivate() == false || playlists.get(i).getUsername().equals(user.getUsername())){
                            found2 = true;
                        }

                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("Playlist id has to be a number");
                keyboard.next();
                found2 = false;
            }
        }

        ArrayList<PlaylistSong> playlistSongs = playlistSongDao1.getPlaylistsByID(playlistID);
        int songID = 0;
        boolean found = false;
        while(!found) {
            try {
                System.out.println("Enter Song ID");
                songID = keyboard.nextInt();


                for (int i = 0; i < playlistSongs.size();i++){
                    if (playlistSongs.get(i).getSongID() == songID){

                        found = true;
                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("SongID has to be a number");
                keyboard.next();
                found = false;
            }
        }


        PlaylistSong playlistSong = new PlaylistSong(playlistID,songID);

        boolean done = playlistSongDao1.removingSongFromPlayList(playlistSong);
        if(done){
            System.out.println("SongId: "+songID+" has been removed from playlistID: "+ playlistID);
        }else{
            System.out.println("Song has not been removed please try again later.");
        }
    }


    static void addNewSongToPlaylistUser(){

        PlaylistSongDao playlistSongDao1 = new PlaylistSongDaoImpl("database.properties");

        PlaylistDao playlistDao1 = new PlaylistDaoImpl("database.properties");

        ArrayList<Playlist> playlists = playlistDao1.getAllPlaylists();

        int playlistID = 0;
        boolean found2 = false;
        while(!found2) {

            try {
                System.out.println("Enter Playlist ID");
                playlistID = keyboard.nextInt();

                for (int i = 0; i < playlists.size();i++){
                    if (playlists.get(i).getPlaylistID() == playlistID){

                        if (playlists.get(i).isStatusPrivate() == false || playlists.get(i).getUsername().equals(user.getUsername())){
                            found2 = true;
                        }

                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("Playlist id has to be a number");
                keyboard.next();
                found2 = false;
            }
        }

        SongDao songDao1 = new SongDaoImpl("database.properties");

        ArrayList<Song> song = songDao1.getAllSongs();
        int songID = 0;
        boolean found = false;
        while(!found) {
            try {
                System.out.println("Enter Song ID");
                songID = keyboard.nextInt();


                for (int i = 0; i < song.size();i++){
                    if (song.get(i).getSongID() == songID){

                        found = true;
                    }
                }
            }catch (InputMismatchException ex){
                System.out.println("SongID has to be a number");
                keyboard.next();
                found = false;
            }
        }


        PlaylistSong playlistSong = new PlaylistSong(playlistID,songID);

        boolean done = playlistSongDao1.addNewSongToPlaylist(playlistSong);
        if(done){
            System.out.println("SongId: "+songID+" has been add to playlistID: "+ playlistID);
        }else{
            System.out.println("Song has not been added. Try again later");
        }
    }
    static void createPlaylist(){
        PlaylistDao playlistDao1 = new PlaylistDaoImpl("database.properties");

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter playlist name");
        String playlistName = keyboard.next();

        boolean status = false;
        System.out.println("Would you like playlist to be public? yes or no");
        String answer = keyboard.next();
        if (answer.equalsIgnoreCase("no")){
            status=true;
        }

        String userName = user.getUsername();
        int ID = playlistDao1.numberOfPlaylists()+1;

        Playlist playlist = new Playlist(ID,playlistName,userName,status);

        boolean complete = playlistDao1.insertNewPlaylists(playlist);

        if(!complete){
            System.out.println("Playlist couldn't be created please try again");
        }else{
            System.out.println("Playlist couldnt be created please try again");
        }
    }
    static void updatePlaylistNameUser(){
        PlaylistDao playlistDao1 = new PlaylistDaoImpl("database.properties");
        ArrayList<Playlist> playlists = new ArrayList<>();
        playlists = playlistDao1.getAllPlaylists();
        String playlistname = null;
        boolean found2 = false;
        while(!found2) {

            System.out.println("Enter Playlist name of playlist you want to change");
            playlistname = keyboard.nextLine();

            for (int i = 0; i < playlists.size();i++){
                if (playlists.get(i).getPlaylistName().equalsIgnoreCase(playlistname)){

                    if (playlists.get(i).isStatusPrivate() == false || playlists.get(i).getUsername().equals(user.getUsername())){
                        found2 = true;
                    }else{
                        System.out.println("Name given not found please re-enter");
                        keyboard.nextLine();
                        found2 =false;
                    }

                }
            }
        }
        System.out.println("Enter the new name you would like to call it");
        String newName = keyboard.next();
        boolean done = playlistDao1.updatePlaylistName(playlistname,newName);
        if(done){
            System.out.println("Playlist name has been change to "+newName);
        }else{
            System.out.println("Playlist name couldnt be changed. Please try again later");
        }
    }
    static ArrayList<Playlist> getAllPlaylistsUser(){
        PlaylistDao playlistDao1 = new PlaylistDaoImpl("database.properties");
        ArrayList<Playlist> playlists = playlistDao1.getAllPlaylists();
        ArrayList<Playlist> playlistsForUser = new ArrayList<>();

        for(int i = 0; i<playlists.size();i++){
            if (playlists.get(i).isStatusPrivate() == false || playlists.get(i).getUsername().equals(user.getUsername())){
                playlistsForUser.add(playlists.get(i));
            }
        }
        return playlistsForUser;
    }

    static void getSongsByArtist(){
        SongDaoImpl songDao = new SongDaoImpl("Database.properties");
        int artistID = 0;
        try{
            System.out.println("Enter artist id you would like to search by");
            artistID = keyboard.nextInt();
        }catch(InputMismatchException e){
            System.out.println(LocalDateTime.now()+"Please enter a number"+e);
        }
        ArrayList<Song> songsByArtist = songDao.getSongByArtist(artistID);

        if(songsByArtist.isEmpty()){
            System.out.println("No songs found for this artist");
        }

        for (Song s: songsByArtist){
            System.out.println("Song: " +s);
        }

    }
    static void getAllSongsinAlbum(){
        SongDaoImpl songDao = new SongDaoImpl("Database.properties");
        int albumID = 0;
        try{
            System.out.println("Enter album id you would like to search by");
            albumID = keyboard.nextInt();
        }catch(InputMismatchException e){
            System.out.println(LocalDateTime.now()+"Please enter a number"+e);
        }
        ArrayList<Song> songsByAlbum = songDao.getSongByAlbum(albumID);

        if(songsByAlbum.isEmpty()){
            System.out.println("No songs found for this album");
        }

        for (Song s: songsByAlbum){
            System.out.println("Song: " +s);
        }

    }
}




/// References
/// TutorialsPoint - Get the Most Frequent Element in an Array in Java - https://www.tutorialspoint.com/get-the-most-frequent-element-in-an-array-in-java
/// DKIT Moodle - Security For Software Developers - https://2324-moodle.dkit.ie/my/
/// DKIT Moodle - Web Patterns - In Class Code - https://github.com/mgraham-dkit/InClass_WebPatterns2024
/// DKIT Moodle - Web Patterns - Slide Examples - https://github.com/mgraham-dkit/WebPatternsSlideExamples
/// DKIT Moodle - Web Patterns - Sample Spring Boot App - https://github.com/mgraham-dkit/SampleSpringApp