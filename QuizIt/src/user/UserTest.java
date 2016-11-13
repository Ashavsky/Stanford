package user;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by scottparsons on 2/25/16.
 */
public class UserTest {
    User user;

    @org.junit.Before
    public void setUp() throws Exception {
        user = new User();
        user.createNewUser("john", "password", false);
        user.createNewUser("scott", "stanford", false);
    }

    @org.junit.Test
    public void testCreateNewUser() throws Exception {
        assertTrue(user.createNewUser("jay", "computer", false));
        assertFalse(user.createNewUser("scott", "stanford", false));
    }

    @org.junit.Test
    public void testIsCorrectLogin() throws Exception {
        assertTrue(user.isCorrectLogin("john", "password"));
        assertFalse(user.isCorrectLogin("alex", "password"));
        assertFalse(user.isCorrectLogin("scott", "stanfords"));
    }

    @Test
    public void testName() throws Exception {

    }
}