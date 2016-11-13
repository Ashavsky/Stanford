package user;

/**
 * Created by Alex on 3/5/2016.
 */
public class HashedPassword
{
    public String hashedPass;
    public String hashedSalt;
    public HashedPassword(String pass, String salt)
    {
        hashedPass = pass;
        hashedSalt = salt;
    }
}
