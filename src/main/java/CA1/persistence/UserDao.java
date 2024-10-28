package CA1.persistence;

import CA1.business.Rating;
import CA1.business.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Toby
 */
public interface UserDao {

  //  public boolean registerUser(User newUser);

    public boolean loginUser(String email, String password);

    public User findUserByUsername(String username);

    public int registerUser(User newUser);

    public User findUserByThereEmail(String email);
}
