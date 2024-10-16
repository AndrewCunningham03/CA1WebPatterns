package CA1.applications;

import CA1.business.Album;
import CA1.business.Artist;
import CA1.persistence.*;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Scanner keyboard = new Scanner(System.in);



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







/*
        int num2 = 0;

        ArtistDao artistDao = new ArtistDaoImpl("database.properties");
        AlbumDao albumDao = new AlbumDaoImpl("database.properties");

        while(num2 != 7){
            String [] array2 = new String[7];

            array2[0] = "1. View all artist in the library";
            array2[1] = "2. View all albums for an artist";
            array2[2] = "3. View all songs in an album";
            array2[3] = "4. Search for song by title";
            array2[4] = "5. Search for song by artist";
            array2[5] = "6. Search for song by album";
            array2[6] = "7. Exit";

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

                    ArrayList<Artist> artists = artistDao.getAllArtist();

                    for (Artist a: artists){
                        System.out.println("Artist: " +a);
                    }

                    System.out.println("");
                    break;
                case 2 :

                    String artistName;
                    System.out.println("Enter artistName: ");


                    keyboard.nextLine();
                    artistName = keyboard.nextLine();
                    System.out.println(artistName);

                    List<Album> albums = albumDao.viewAllAlbumsFromArtist(artistName);

                    System.out.println(albums);

                    break;
                case 3:
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("");
                    break;
                case 5:
                    System.out.println("");
                    break;
                case 6:
                    break;
            }
        }



 */


        int num3 = 0;

        RatingDao  ratingDao = new RatingDaoImpl("database.properties");


        while(num3 != 5){
            String [] array3 = new String[5];

            array3[0] = "1. Rate a song from 1-5";
            array3[1] = "2. View all songs you have rated and their rating";
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

                    System.out.println(ratingDao.implementRatingSong(ratingDao.rateSong()));

                    break;
                case 2 :

                    System.out.println(ratingDao.getAllRatedSongsAndRating());
                    break;
                case 3:

                    System.out.println(ratingDao.getTopRatedSong());
                    break;
                case 4:

                    System.out.println(ratingDao.getMostPopularSong());
                    break;
                case 5:
                    break;

            }
        }



    }
}
