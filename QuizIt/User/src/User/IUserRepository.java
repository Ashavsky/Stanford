package User;

import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 3/3/2016.
 */
public interface IUserRepository
{
    void PopulateCurrentUser(String userName); //XX
    List<String> getAchievements(); //This needs to return a list of achievements not names

    boolean userExists(String username); //XX
    boolean createNewUser(String username, String email, String password, Boolean isAdmin); //XX
    void removeCurrentUser(); //XX
    String getUsername(); //XX
    public int getUserId(); //XX
    public int usernameToId(String username); //temporary method
    public String idToUsername(int userId); //temporary method
    boolean isCorrectLogin(String username, String password); //XX
    boolean isAdmin(); //XX
    void addFriend(int friendUserId); //XX
    void removeFriend(int friendUserId); //XX
    public void addAchievement(String achievementName, String achievementDesc); //XX
    public void removeAchievement(String achievementName); //XX
    public boolean  AchievementExists(String achievementName); //XX
    Integer getNumberOfUsers(); //XX
    boolean FriendshipExists(int userId); //XX
    public List<User> getAllUsers(); //XX

    //AdminTasks
    void promoteToAdmin(String userToPromote);
    void DeleteUser(String userName); //XX



    //QuizTasks
//    void addQuizScore(String quizId, Double grade);
//    void removeQuiz(String username, String quizId);
//    Double getQuizScore(String quizId); //TO DELETE, you can have multiple attempts on same quiz, method doesnt make sense
    Double getQuizHighScore(String quizId);
//    Integer getNumberOfQuizzesTaken();
//    void addCreatedQuiz(String quizId);
//    Integer getNumberOfQuizzesCreated();
//    List<String> getRecentlyCreatedQuizzes();

}
