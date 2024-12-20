package CA1.business;

import java.util.Objects;

/**
 * @author Toby
 */
public class User {

    private String username;
    private String email;
    private String password;
    private int userType;

    public User(String username, String email, String password, int userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User(User userByEmail){
        this.username = userByEmail.getUsername();
        this.email = userByEmail.getEmail();
        this.password = userByEmail.getPassword();
        this.userType = userByEmail.getUserType();
    }
    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }


    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return Objects.equals(username, users.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}


