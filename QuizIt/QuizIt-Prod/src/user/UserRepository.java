package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.*;

import db.QuizPersistence;

/**
 * Created by scottparsons on 2/25/16.
 */
public class UserRepository implements IUserRepository
{
    // Instance variables
    private final static int MAX_RECENT_CREATED_QUIZZES = 5;
    private User _currentUser = null;

    public UserRepository() {
    }

    public boolean createNewUser(String username, String email, String password, Boolean isAdmin)
    {
        if(userExists(username))
        {
            return false;
        }
        else
        {
            byte[] salt = generateSalt();
            byte[] hashSaltPassword = generateHashValue(hexToString(salt), password);
            db.UserPersistence.InsertUserDetail(username, email, hexToString(hashSaltPassword), hexToString(salt), isAdmin);
            PopulateCurrentUser(username);
            return true;
        }
    }

    public void DeleteUser(String userName)
    {
        if(userExists(userName))
            db.UserPersistence.DeleteUserDetail(userName);
    }

    public void PopulateCurrentUser(String userName)
    {
        if(userExists(userName))
            _currentUser = db.UserPersistence.GetUser(userName);
    }

    public void removeCurrentUser() {
        _currentUser = null;
    }

    public String getUsername() { return _currentUser.userName; }
    public int getUserId() { return _currentUser.userId; }
    
    public int usernameToId(String username) { 
    	return db.UserPersistence.usernameToId(username); 
    }
    
    public String idToUsername(int userId) { 
    	return db.UserPersistence.idToUsername(userId); 
    }

    public boolean isCorrectLogin(String username, String password)
    {
        if (userExists(username))
        {
            HashedPassword passwordInfo = db.UserPersistence.GetPasswordInfo(username);
            String dbPass = passwordInfo.hashedPass;
            String dbSalt = passwordInfo.hashedSalt;

            String hashPassword = hexToString(generateHashValue(dbSalt, password));
            boolean isPasswordCorrect = hashPassword.equals(dbPass);
            return isPasswordCorrect;
        }
        else
            return false;
    }

    /**
     * Changes a user from a regular to admin user
     * @param userToPromote Username to promote to admin
     */
    public void promoteToAdmin(String userToPromote) {
        db.UserPersistence.PromoteUserToAdmin(userToPromote);
        //_currentUser.isAdmin = true;
    }

    public boolean isAdmin(String username)
    {
        return db.UserPersistence.isAdmin(username);
    }

    public boolean isAdmin() {
        return db.UserPersistence.isAdmin(_currentUser.userName);
    }

    public void addFriend(int friendUserId)
    {
        if(!FriendshipExists(friendUserId))
            db.UserPersistence.InsertUserFriend(_currentUser.userId, friendUserId);
    }

    public void removeFriend(int friendUserId)
    {
        if(FriendshipExists(friendUserId))
            db.UserPersistence.DeleteUserFriendship(_currentUser.userId, friendUserId);
    }



    public void addAchievement(String achievementName, String achievementDesc)
    {
        if(!AchievementExists(achievementName))
            db.UserPersistence.InsertAchievement(getUserId(), achievementName, achievementDesc);
    }

    public void removeAchievement(String achievementName)
    {
        if(AchievementExists(achievementName))
            db.UserPersistence.DeleteAchievement(getUserId(), achievementName);
    }

    public Integer getNumberOfUsers()
    {
        return db.DatabaseTasks.GetCountRecordsFromTable("UserDetail");
    }

    public List<User> getAllUsers()
    {
        return db.UserPersistence.GetUsers();
    }

    public boolean  AchievementExists(String achievementName)
    {
        return db.DatabaseTasks.CheckIfRecordExistsWithParametersIntString("UserAchievements", "UserId",  Integer.toString(getUserId()), "AchievementName", achievementName);
    }

    public boolean FriendshipExists(int userId)
    {
        return db.DatabaseTasks.CheckIfRecordExistsWithParametersIntInt("UserFriends", "UserId",  Integer.toString(getUserId()), "FriendId", Integer.toString(userId));
    }

    public boolean userExists(String username)
    {
        return db.DatabaseTasks.CheckIfRecordExistsWithParameterString("UserDetail", "UserName", username);
    }

    // Generates the SHA hex hash value using the MessageDigest
    private static byte[] generateHashValue(String salt, String password)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] combined = new byte[salt.getBytes().length + password.getBytes().length];
            System.arraycopy(salt.getBytes(), 0, combined, 0, salt.getBytes().length);
            System.arraycopy(password.getBytes(), 0, combined, salt.getBytes().length, password.getBytes().length);
            byte[] hash = md.digest(combined);
            return hash;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] generateSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        return salt;
    }

    /*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
    private static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val<16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }



    public double getQuizHighScore(int quizId) {
        return QuizPersistence.getQuizHighScore(getUsername(), quizId);
    }
}