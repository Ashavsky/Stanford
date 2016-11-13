package user;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class UserSearchServletTest {
	User user;
	
	@Before
    public void setUp() throws Exception {
        user = new User();
        user.createNewUser("john", "password", false);
    }

	@Test
	public void testChallenge() {
		assertEquals(new HashSet<String>(), user.getMessages());
		Messaging.addMessage(new Message("scott", "john", "challenge", "2"));
		assertEquals(new HashSet<String>(Arrays.asList("0")), user.getMessages());
		user.setUsername("scott");
		assertEquals(new HashSet<String>(Arrays.asList("0")), user.getMessages());
	}
	
	@Test
	public void testFriend() {
		assertFalse(user.isFriends("scott"));
		assertFalse(user.sentRequest("scott"));
		user.addFriendRequest("scott");
		assertTrue(user.sentRequest("scott"));
		assertEquals(new HashSet<String>(), user.getFriendRequests());
		
		user.setUsername("scott");
		assertEquals(new HashSet<String>(Arrays.asList("john")), user.getFriendRequests());
		user.addFriend("john");
		user.removeFriendRequest("john");
		assertTrue(user.isFriends("john"));
		assertEquals(new HashSet<String>(), user.getFriendRequests());
		
		user.setUsername("john");
		assertTrue(user.isFriends("scott"));
		assertFalse(user.sentRequest("scott"));
	}

}
