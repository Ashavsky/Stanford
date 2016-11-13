package User;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class UserSearchServletTest {
	IUserRepository userRepository;
	
	@Before
    public void setUp() throws Exception {
        userRepository = new UserRepository();
        userRepository.PopulateCurrentUser("Scott");
    }

	@Test
	public void testChallenge() {
		assertEquals(new HashSet<Integer>(), Messaging.getMessages(userRepository.getUserId()));
		Messaging.addMessage(new Message(1, 3, "challenge", "2"));
		assertEquals(new HashSet<Integer>(Arrays.asList(0)), Messaging.getMessages(userRepository.getUserId()));
        userRepository.PopulateCurrentUser("Alex");
		assertEquals(new HashSet<Integer>(Arrays.asList(0)), Messaging.getMessages(userRepository.getUserId()));
	}
	
	@Test
	public void testFriend() {
		assertFalse(Messaging.requestExists(1, 3));
		Messaging.addFriendRequest(1, 3);
		assertTrue(Messaging.requestExists(1, 3));
		assertEquals(new HashSet<Integer>(), Messaging.getFriendRequests(1));
		assertEquals(new HashSet<Integer>(Arrays.asList(1)), Messaging.getFriendRequests(3));

		Messaging.removeFriendRequest(1, 3);
		assertFalse(Messaging.requestExists(1, 3));
		assertEquals(new HashSet<Integer>(), Messaging.getFriendRequests(3));
	}

}
