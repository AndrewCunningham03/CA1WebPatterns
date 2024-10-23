package CA1.applications;

import CA1.business.*;
import CA1.persistence.*;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Scanner keyboard = new Scanner(System.in);


        System.out.println();

        UserDao userDao = new UserDaoImpl("database.properties");

        /// login and register

        int num = 0;

        boolean result = false;

        while(!result){


            String [] array = new String[2];

            array[0] = "1. Register: ";
            array[1] = "2. Login: ";
            System.out.println("");

            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }


            System.out.println("Enter Number: ");
            num = keyboard.nextInt();



            switch (num){


                case 1:

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

                    System.out.println(userDao.registerUser(userDao.createUserRegister()));

                    break;

                case 2:

                    String email;
                    System.out.println("Enter email: ");
                    email = keyboard.next();

                    String password;
                    System.out.println("Enter password: ");
                    password = keyboard.next();

                    System.out.println(result = userDao.loginUser(email,password));
                    break;

            }

        }












        int num2 = 0;


        //   AlbumDao albumDao = new AlbumDaoImpl("database.properties");

        while(num2 != 11){
            String [] array2 = new String[11];

            array2[0] = "1. View all artist in the library";
            array2[1] = "2. Search for artist by artistID";
            array2[2] = "3. View all albums for an artist";
            array2[3] = "4. Search for album by albumID";
            array2[4] = "5. View all albums in the library";
            array2[5] = "6. View all songs in an album";
            array2[6] = "7. Search for song by songID";
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

            switch (num2){

                case 1:

                    ArtistDao artistDao = new ArtistDaoImpl("database.properties");

                    ArrayList<Artist> artists = artistDao.getAllArtist();

                    for (Artist a: artists){
                        System.out.println("Artist: " +a);
                    }


                    break;
                case 2 :

                    ArtistDao artistDao1 = new ArtistDaoImpl("database.properties");

                    Boolean repeat5 = false;

                    while(!repeat5) {

                        try {
                            int artistID2;
                            System.out.println("Enter artistID");
                            artistID2 = keyboard.nextInt();

                            System.out.println(artistDao1.findArtistById(artistID2));
                            repeat5 = true;
                        }catch (InputMismatchException ex){
                            System.out.println("ArtistID has to be a number");
                            keyboard.next();

                            repeat5 = false;
                        }
                    }

                    break;
                case 3:

                    AlbumDao albumDao = new AlbumDaoImpl("database.properties");

                    Boolean repeat4 = false;
                    while(!repeat4) {

                        try {
                            int artistID;
                            System.out.println("Enter artistID: ");
                            artistID = keyboard.nextInt();
                            System.out.println(artistID);

                            List<Album> albums = albumDao.viewAllAlbumsFromArtist(artistID);

                            System.out.println(albums);
                            repeat4 = true;
                        }catch (InputMismatchException ex){

                            System.out.println("ArtistID has to be a number");
                            keyboard.next();

                            repeat4 = false;
                        }
                    }

                    break;
                case 4:

                    AlbumDao albumDao3 = new AlbumDaoImpl("database.properties");


                    Boolean repeat7 = false;

                    while(!repeat7) {

                        try {
                            int albumID;
                            System.out.println("Enter albumID: ");
                            albumID = keyboard.nextInt();

                            System.out.println(albumDao3.findAlbumById(albumID));
                            repeat7 = true;
                        }catch (InputMismatchException ex){
                            System.out.println("Album ID has to be a number");
                            keyboard.next();
                            repeat7 = false;
                        }
                    }

                    break;
                case 5:

                    AlbumDao albumDao4 = new AlbumDaoImpl("database.properties");

                    ArrayList<Album> albums4 = albumDao4.getAllAlbums();

                    for (Album a: albums4){
                        System.out.println("Artist: " +a);
                    }

                    break;
                case 6:

                    SongDao songDao = new SongDaoImpl("database.properties");

                    Boolean repeat8 = false;

                    while(!repeat8) {
                        try {
                            int songID;
                            System.out.println("Enter songID: ");
                            songID = keyboard.nextInt();

                            System.out.println(songDao.findSongById(songID));
                            repeat8 = true;
                        }catch(InputMismatchException ex){
                            System.out.println("SongID has to be a number ");
                            keyboard.next();
                            repeat8 = false;
                        }
                    }
                    break;
                case 7:
                    System.out.println("");
                    break;
                case 8:
                    System.out.println("");
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
        }










        int num3 = 0;

        while(num3 != 5){
            String [] array3 = new String[5];

            array3[0] = "1. Rate a song from 1-5";
            array3[1] = "2. View all songs and what song a particular user has rated with the song and rating";
            array3[2] = "3. Get top rated song";
            array3[3] = "4. Get the most popular song";
            array3[4] = "5. Exit";

            for (int i = 0; i < array3.length; i++) {
                System.out.println(array3[i]);
            }

            System.out.println("Enter number: ");
            num3 = keyboard.nextInt();

            if (num3 <= 0 || num3 > array3.length) {
                System.out.println("Number is not on menu");
            }

            switch (num3){

                case 1:

                    RatingDao  ratingDao2 = new RatingDaoImpl("database.properties");


                    System.out.println(ratingDao2.implementRatingSong(ratingDao2.rateSong()));

                    break;
                case 2 :

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
                            String username;
                            System.out.println("Enter username: ");
                            username = keyboard.next();

                            int songID = 0;
                            System.out.println("Enter songID: ");
                            songID = keyboard.nextInt();


                            for (int i = 0; i < rating.size(); i++) {

                                if (rating.get(i).getUsername().equals(username) && rating.get(i).getSongID() == songID) {

                                    Song song = songDao.findSongById(songID);

                                    System.out.println(song + ", Song Rating: " + ratingDao1.getUserRatingFromUsernameAndSongID(username, songID));

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

                    break;
                case 3:
                    RatingDao  ratingDao3 = new RatingDaoImpl("database.properties");

                    System.out.println(ratingDao3.getTopRatedSong());
                    break;
                case 4:

                    RatingDao  ratingDao4 = new RatingDaoImpl("database.properties");

                    System.out.println(ratingDao4.getMostPopularSong());
                    break;
                case 5:
                    break;

            }
        }

        PlaylistDao playlistDao = new PlaylistDaoImpl("database.properties");
        int num4 =0;
        while(num4 != 5){
            String [] array4 = new String[5];
            array4[0] = "1. View all Playlists";
            array4[1] = "2. Create new Playlist";
            array4[2] = "3. ";
            array4[3] = "4. ";
            array4[4] = "5. Exit";
            for (int i = 0; i < array4.length; i++) {
                System.out.println(array4[i]);
            }
            System.out.println("Enter number: ");
            num4 = keyboard.nextInt();
            if (num4 <= 0 || num4 > array4.length) {
                System.out.println("Number is not on menu");
            }
            switch (num4){
                case 1:
                    ArrayList<Playlist> allPlaylists = playlistDao.getAllPlaylists();
                    for (Playlist p: allPlaylists){
                        System.out.println("Playlist: " +p);
                    }
                    break;
                case 2 :
                    //playlistDao.createNewPlaylist();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }



    }
}
