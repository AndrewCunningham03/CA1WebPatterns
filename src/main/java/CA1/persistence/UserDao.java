package CA1.persistence;

import CA1.business.Users;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserDao {

    public Users createUserRegister() throws InvalidKeySpecException, NoSuchAlgorithmException;

    public boolean registerUser(Users newUser);

    public boolean loginUser(String email, String password);
}
