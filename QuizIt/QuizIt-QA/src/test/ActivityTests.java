package test;

import db.UserPersistence;
import user.IUserRepository;
import user.UserRepository;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 3/9/2016.
 */
public class ActivityTests
{

    @org.junit.Test
    public void Should_Add_Get_Quiz_Activity() throws SQLException
    {
        IUserRepository userRepository = new UserRepository();
        userRepository.createNewUser("ActivityUser", "a@gmail.com", "Qwer1234", false);
        userRepository.PopulateCurrentUser("ActivityUser");

        /*db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "QuizTaken", 0);
        db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "QuizCreated", 0);
        db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "QuizTaken", 0);
        db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "Achievement", 0);

        assertTrue(db.UserPersistence.GetActivities(userRepository.getUserId()).size() == 4);*/
        userRepository.DeleteUser("ActivityUser");

        userRepository.PopulateCurrentUser("admin");
        db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "QuizTaken",2);
        db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "QuizTaken",3);
        db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "QuizCreated",4);
        db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "QuizCreated",5);
        db.UserPersistence.InsertUserActivity(userRepository.getUserId(), "QuizTaken",6);
    }

}
