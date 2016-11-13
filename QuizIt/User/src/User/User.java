package User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by scottparsons on 2/25/16.
 */
public class User
{
    public int userId;
    public String userName;
    public boolean isAdmin;
    public String email;
    public String dateCreated;

    public User()
    {

    }
}
