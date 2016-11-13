package User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by scottparsons on 2/25/16.
 */
public class UserRepository implements IUserRepository
{
    // Instance variables
    private final static int MAX_RECENT_CREATED_QUIZZES = 5;
    private User _currentUser = null;

    private Map<String, Boolean> dbUsersAdmin = new HashMap<String, Boolean>(); // REMOVE when DB exists
    private Map<String, Double> dbQuizHistory = new HashMap<String, Double>(); // REMOVE when DB exists
    private List<String> dbAchievements = new ArrayList<String>(); // REMOVE when DB exists
    private Map<String, List<String>> dbQuizzesCreated = new HashMap<String, List<String>>(); // REMOVE when DB exists

    // Constructor
    public UserRepository() {
    }

    /**
     * Creates a new user account and stores it in the database
     * @param username New username to create
     * @param password New password to create
     * @return true if successful, false if not
     */
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
            DatabaseTasks.InsertUserDetail(username, email, hexToString(hashSaltPassword), hexToString(salt), isAdmin);
            PopulateCurrentUser(username);
            return true;
        }
    }

    public void DeleteUser(String userName)
    {
        if(userExists(userName))
            DatabaseTasks.DeleteUserDetail(userName);
    }

    public void PopulateCurrentUser(String userName)
    {
        if(userExists(userName))
            _currentUser = DatabaseTasks.GetUser(userName);
    }

    public void removeCurrentUser() {
        _currentUser = null;
    }

    public String getUsername() { return _currentUser.userName; }
    public int getUserId() { return _currentUser.userId; }
    public int usernameToId(String username) { return username.hashCode(); }
    public String idToUsername(int userId) { return String.valueOf(userId); }

    public boolean isCorrectLogin(String username, String password)
    {
        if (userExists(username))
        {
            HashedPassword passwordInfo = DatabaseTasks.GetPasswordInfo(username);
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

        DatabaseTasks.PromoteUserToAdmin(userToPromote);
        //_currentUser.isAdmin = true;
    }

    public boolean isAdmin()
    {
        if(_currentUser != null)
            return _currentUser.isAdmin;
        else
            return false;
    }

    public void addFriend(int friendUserId)
    {
        if(!FriendshipExists(friendUserId))
            DatabaseTasks.InsertUserFriend(_currentUser.userId, friendUserId);
    }

    public void removeFriend(int friendUserId)
    {
        if(FriendshipExists(friendUserId))
            DatabaseTasks.DeleteUserFriendship(_currentUser.userId, friendUserId);
    }



    public void addAchievement(String achievementName, String achievementDesc)
    {
        if(!AchievementExists(achievementName))
            DatabaseTasks.InsertAchievement(getUserId(), achievementName, achievementDesc);
    }

    public void removeAchievement(String achievementName)
    {
        if(AchievementExists(achievementName))
            DatabaseTasks.DeleteAchievement(getUserId(), achievementName);
    }

    public List<String> getAchievements() {
        //TODO get from DB when ready
        return dbAchievements;
    }

    public Integer getNumberOfUsers()
    {
        return DatabaseTasks.GetCountRecordsFromTable("UserDetail");
    }

    public List<User> getAllUsers()
    {
        return DatabaseTasks.GetUsers();
    }

    public boolean  AchievementExists(String achievementName)
    {
        return DatabaseTasks.CheckIfRecordExistsWithParametersIntString("UserAchievements", "UserId",  Integer.toString(getUserId()), "AchievementName", achievementName);
    }

    public boolean FriendshipExists(int userId)
    {
        return DatabaseTasks.CheckIfRecordExistsWithParametersIntInt("UserFriends", "UserId",  Integer.toString(getUserId()), "FriendId", Integer.toString(userId));
    }

    public boolean userExists(String username)
    {
        return DatabaseTasks.CheckIfRecordExistsWithParameterString("UserDetail", "UserName", username);
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


    //*********************** PROBABLY WILL BE DELETED, QUIZ METHODS ***************************

    /**
     * Adds a user's quiz ID and score to the database
     * @param quizId
     * @param grade
     */
    public void addQuizScore(String quizId, Double grade) {
        //TODO add to DB when it's ready
        dbQuizHistory.put(quizId, grade);
    }

    /**
     * Removes a particular quiz history from a user
     * @param username
     * @param quizId
     */
    public void removeQuiz(String username, String quizId) {
        //TODO replace with DB when ready
        dbQuizHistory.remove(quizId);
    }

    /**
     * Returns the user's score for a particular quiz
     * @param quizId
     * @return
     */
    public Double getQuizScore(String quizId) {
        //TODO get from DB when it's ready
        return dbQuizHistory.get(quizId);
    }

    public Double getQuizHighScore(String quizId) {
        //TODO get from DB when it's ready
        return 100.0;
    }

    /**
     * Returns the number of quizzes a user has taken
     * @return number of quizzes taken
     */
    public Integer getNumberOfQuizzesTaken() {
        //TODO replace with DB when it's ready
        return dbQuizHistory.size();
    }

    public Integer getNumberOfQuizzesCreated() {
        //TODO replace with DB when it's ready
        if(dbQuizzesCreated.get(_currentUser.userName) == null) {
            return 0;
        }
        else
        {
            return dbQuizzesCreated.get(_currentUser.userName).size();
        }
    }

    /**
     * Returns the 5 most recently created quizzes for the user or empty list if user has not
     * created a quiz.
     * @return
     */
    public List<String> getRecentlyCreatedQuizzes() {
        //TODO pull from DB when it's ready
        int numQuizzesCreated = getNumberOfQuizzesCreated();

        if (numQuizzesCreated== 0) {
            return new ArrayList<String>();
        }
        else {
            List<String> quizList = new ArrayList<String>();
            for (int i = numQuizzesCreated-1; i >= Math.max(numQuizzesCreated - MAX_RECENT_CREATED_QUIZZES, 0); i--) {
                quizList.add(dbQuizzesCreated.get(_currentUser.userName).get(i));
            }
            return quizList;
        }
    }
}
