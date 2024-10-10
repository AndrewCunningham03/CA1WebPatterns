package CA1.applications;

import CA1.persistence.UserDao;
import CA1.persistence.UserDaoImpl;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

    }
}
