package CA1.applications;

import CA1.business.Artist;
import CA1.persistence.ArtistDao;
import CA1.persistence.ArtistDaoImpl;
import CA1.persistence.UserDao;
import CA1.persistence.UserDaoImpl;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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

                    String creditCard;

                    System.out.println("Enter creditCard details for Visa Cards");
                    creditCard = keyboard.next();

                    Pattern pattern = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
                    Matcher match = pattern.matcher(creditCard);
                    boolean matchfound = match.find();

                    boolean repeat = false;

                    while(!repeat){

                        if (matchfound){
                            System.out.println("Correct card details");
                            repeat = false;
                        }else{

                            System.out.println("Enter card details again: ");
                            keyboard.next();
                            repeat = true;
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

        ArtistDao artistDao = new ArtistDaoImpl("database.properties");

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

                    System.out.println("");
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }

    }
}
