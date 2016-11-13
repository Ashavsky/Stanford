package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by scottparsons on 2/25/16.
 */
public class User {
    // Instance variables
    private String username;
    private final static int SALT_IDX = 0;
    private final static int PW_IDX = 1;
    private final static int MAX_RECENT_CREATED_QUIZZES = 5;
    private final static int MAX_RECENT_TAKEN_QUIZZES = 5;

    private static Map<String, List> dbUsersPasswords;
    private static Map<String, Boolean> dbUsersAdmin = new HashMap<String, Boolean>(); // REMOVE when DB exists
    private static Map<String, Set<String>> dbFriends = new HashMap<String, Set<String>>(); // REMOVE when DB exists
    private static Map<String, Set<String>> dbFriendRequests = new HashMap<String, Set<String>>();
    private static Map<String, Set<String>> dbMessages = new HashMap<String, Set<String>>();
    private static Map<String, Double> dbQuizHistory = new LinkedHashMap<String, Double>(); // REMOVE when DB exists
    private List<Achievement> dbAchievements = new ArrayList<Achievement>(); // REMOVE when DB exists
    private Map<String, List<String>> dbQuizzesCreated = new LinkedHashMap<String, List<String>>(); // REMOVE when DB exists

    public User() {
        dbUsersPasswords = new HashMap<String, List>(); //REMOVE when DB exists
        List l = new ArrayList();
        byte[] salt = generateSalt();
        l.add(hexToString(salt));
        l.add(hexToString(generateHashValue(hexToString(salt), "pass")));
        dbUsersPasswords.put("scott", l);
        dbUsersAdmin.put("scott", false);
    }


    /**
     * Creates a new user account and stores it in the database
     * @param username New username to create
     * @param password New password to create
     * @return true if successful, false if not
     */
    public boolean createNewUser(String username, String password, Boolean isAdmin) {
        if(userExists(username)) {
            System.out.println("Username already exists");
            return false;
        }
        else {
            //TODO replace with adding to database
            byte[] salt = generateSalt();
            List l = new ArrayList();
            l.add(salt);
            l.add(hexToString(generateHashValue(hexToString(salt), password)));
            dbUsersPasswords.put(username, l);
            dbUsersAdmin.put(username, isAdmin);
            setUsername(username);
            System.out.println("User added successfully");
            return true;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }

    /**
     * Checks if the login username and password are correct
     * @param username Login username
     * @param password Login password
     * @return true if username exists and password matches, false if not
     */
    public boolean isCorrectLogin(String username, String password) {
        return userExists(username) && passwordMatches(username, password);
    }

    /**
     * Changes a user from a regular to admin user
     * @param userToPromote Username to promote to admin
     */
    public void promoteToAdmin(String userToPromote) {
        //TODO replace with DB code
        dbUsersAdmin.put(userToPromote, true);
    }

    /**
     * Checks if a user has admin privileges or not
     * @return true if admin, false otherwise
     */
    public boolean isAdmin() {
        //TODO replace with DB code
        return dbUsersAdmin.get(username);
    }
    
    public boolean isFriends(String friendUsername) {
    	if (!dbFriends.containsKey(username)) return false;
    	return dbFriends.get(username).contains(friendUsername);
    }

    /**
     * Adds the username to the user's friend list
     * @param friendUsername Friend's username to add to friend list
     */
    public void addFriend(String friendUsername) {
        //TODO add to DB when it's ready
    	if (!dbFriends.containsKey(username)) {
    		dbFriends.put(username, new HashSet<String>());
    	}
    	dbFriends.get(username).add(friendUsername);
    	if (!dbFriends.containsKey(friendUsername)) {
    		dbFriends.put(friendUsername, new HashSet<String>());
    	}
    	dbFriends.get(friendUsername).add(username);
    }

    /**
     * Removes a friend from the user's friend list
     * @param friendUsername Friend's username to remove from friend list
     */
    public void removeFriend(String friendUsername) {
        //TODO remove from DB when it's ready
        if(dbFriends.containsKey(username) &&
        		dbFriends.get(username).contains(friendUsername)) {
        	dbFriends.get(username).remove(friendUsername);
        }
        if (dbFriends.containsKey(friendUsername) &&
        		dbFriends.get(friendUsername).contains(username)) {
            dbFriends.get(friendUsername).remove(username);
        }
    }
    
    public boolean sentRequest(String friendUsername) {
    	if (!dbFriendRequests.containsKey(friendUsername)) return false;
    	return dbFriendRequests.get(friendUsername).contains(username);
    }
    
    public Set<String> getFriendRequests() {
    	//TODO get from DB when it's ready
    	if (dbFriendRequests.containsKey(username)) {
    		return dbFriendRequests.get(username);
    	} else {
    		return new HashSet<String>();
    	}
    }
    
    public void addFriendRequest(String friendUsername) {
    	if (!dbFriendRequests.containsKey(friendUsername)) {
    		dbFriendRequests.put(friendUsername, new HashSet<String>());
    	}
    	dbFriendRequests.get(friendUsername).add(username);
    }
    
    public void removeFriendRequest(String friendUsername) {
    	if (dbFriendRequests.containsKey(username) && 
    			dbFriendRequests.get(username).contains(friendUsername)) {
    		dbFriendRequests.get(username).remove(friendUsername);
    	}
    }
    
    public Set<String> getMessages() {
    	//TODO get from DB when it's ready
    	if (dbMessages.containsKey(username)) {
    		return dbMessages.get(username);
    	} else {
    		return new HashSet<String>();
    	}
    }
    
    public static void addMessage(String username, String id) {
    	if (!dbMessages.containsKey(username)) {
    		dbMessages.put(username, new HashSet<String>());
    	}
    	dbMessages.get(username).add(id);
    }
    
    public static void removeMessage(String username, String id) {
    	//TODO error checking
    	dbMessages.get(username).remove(id);
    }

    /**
     * Adds a user's quiz ID and score to the database
     * @param quizId
     * @param score
     */
    public void addQuizScore(String quizId, Double score) {
        //TODO add to DB when it's ready
        dbQuizHistory.put(quizId, score);

        // TODO If user has max score for a quiz, they earn GREATEST achievement
        if(score > 0 && !dbAchievements.contains(Achievement.GREATEST)) {
            addAchievement(Achievement.GREATEST);
        }
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

    /**
     * Adds a created quiz to the user's history and database. Checks for achievements every time a quiz is created.
     * @param quizId Quiz identifier
     */
    public void addCreatedQuiz(String quizId) {
        //TODO replace with DB when it's ready
        // If user has created a quiz before, add new quiz to the database
        if(dbQuizzesCreated.containsKey(username)) {
            List<String> quizList = dbQuizzesCreated.get(username);
            quizList.add(quizId);

            // Check for achievements
            if(getNumberOfQuizzesCreated() == Achievement.PRODIGIOUS_AUTHOR.getThreshold()) {
                addAchievement(Achievement.PRODIGIOUS_AUTHOR);
            }
            else if(getNumberOfQuizzesCreated() == Achievement.PROLIFIC_AUTHOR.getThreshold()) {
                addAchievement(Achievement.PROLIFIC_AUTHOR);
            }
        }
        // Initialize created quiz list for user
        else {
            List<String> quizList = new ArrayList<String>();
            quizList.add(quizId);
            dbQuizzesCreated.put(username, quizList);
            addAchievement(Achievement.AMATEUR_AUTHOR); // First created quiz achievement
        }
    }


    public int getNumberOfQuizzesCreated() {
        //TODO replace with DB when it's ready
        if(dbQuizzesCreated.get(username) == null) {
            return 0;
        }
        else {
            return dbQuizzesCreated.get(username).size();
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
                quizList.add(dbQuizzesCreated.get(username).get(i));
            }
            return quizList;
        }
    }

    /**
     * Returns the 5 most recently taken quizzes and scores for the user or empty list if user has not
     * taken a quiz.
     * @return
     */
    public Map<String, Double> getRecentlyTakenQuizzes() {
        //TODO pull from DB when it's ready
        int numQuizzesTaken = getNumberOfQuizzesTaken();

        if (numQuizzesTaken== 0) {
            return new LinkedHashMap<String, Double>();
        }
        else {
            Map<String, Double> quizList = new LinkedHashMap<String, Double>();
            List<String> keys = new ArrayList<String>(dbQuizHistory.keySet());
            for (int i = keys.size()-1; i >= Math.max(numQuizzesTaken - MAX_RECENT_TAKEN_QUIZZES, 0); i--) {
                quizList.put(keys.get(i), dbQuizHistory.get(keys.get(i)));
            }
            return quizList;
        }
    }

    /**
     * Adds the user's achievement to the database
     * @param achievement
     */
    public void addAchievement(Achievement achievement) {
        //TODO add to DB when it's ready
        dbAchievements.add(achievement);
    }

    public List<Achievement> getAchievements() {
        //TODO get from DB when ready
        return dbAchievements;
    }

    public Integer getNumberOfUsers() {
        //TODO Get from DB when ready
        return dbUsersPasswords.size();
    }

    public Set<String> getAllUsers() {
        return dbUsersPasswords.keySet();
    }

    public static boolean userExists(String username) {
        // TODO replace with database code
        if(dbUsersPasswords.containsKey(username)) {
            System.out.println("username exists");
        }
        return dbUsersPasswords.containsKey(username);
    }

    // Returns the hash'd password from the database.  Assumes the username exists.
    private String getPasswordFromDB(String username) {
        return (String) dbUsersPasswords.get(username).get(PW_IDX);
    }

    // Checks if user login password matches the hash value stored in the database
    private boolean passwordMatches(String username, String password) {
        if (userExists(username)) {
            String salt = (String) dbUsersPasswords.get(username).get(SALT_IDX);
            String dbPassword = (String) dbUsersPasswords.get(username).get(PW_IDX);
            String hashPassword = hexToString(generateHashValue(salt, password));
            return hashPassword.equals(dbPassword);
        }
        return false;
    }

    // Generates the SHA hex hash value using the MessageDigest
    private static byte[] generateHashValue(String salt, String password) {
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

}
