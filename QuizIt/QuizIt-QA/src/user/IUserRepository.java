package user;

import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 3/3/2016.
 */
public interface IUserRepository
{

    void PopulateCurrentUser(String userName); //XX

    boolean userExists(String username); //XX
    boolean createNewUser(String username, String email, String password, Boolean isAdmin); //XX
    void removeCurrentUser(); //XX
    String getUsername(); //XX
    int getUserId(); //XX
    int usernameToId(String username); //temporary method
    String idToUsername(int userId); //temporary method
    boolean isCorrectLogin(String username, String password); //XX
    boolean isAdmin(String username); //XX
    boolean isAdmin(); //XX
    void addFriend(int friendUserId); //XX
    void removeFriend(int friendUserId); //XX
    void addAchievement(String achievementName, String achievementDesc); //XX
    void removeAchievement(String achievementName); //XX
    boolean  AchievementExists(String achievementName); //XX
    Integer getNumberOfUsers(); //XX
    boolean FriendshipExists(int userId); //XX
    List<User> getAllUsers(); //XX

    //AdminTasks
    void promoteToAdmin(String userToPromote);
    void DeleteUser(String userName); //XX



    //QuizTasks
    double getQuizHighScore(int quizId);

}
