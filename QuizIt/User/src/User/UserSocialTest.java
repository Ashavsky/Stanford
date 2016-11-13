package User;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 3/7/2016.
 */
public class UserSocialTest
{
    @org.junit.Test
    public void Should_Add_And_Delete_A_Note()
    {
        int messageId = Messaging.addMessage(
                new Message(1,2,"note", "this is a note sent from 2 to recipient 1")
        );

        boolean doesMessageExist = Messaging.MessageExists(messageId);
        assertTrue(doesMessageExist);

        Messaging.removeMessage(messageId);
        doesMessageExist = Messaging.MessageExists(messageId);
        assertFalse(doesMessageExist);
    }

    @org.junit.Test
    public void Should_Add_And_Delete_A_Friend_Request()
    {
        int messageId = Messaging.addFriendRequest(3,4);

        boolean doesMessageExist = Messaging.MessageExists(messageId);
        assertTrue(doesMessageExist);

        Messaging.removeMessage(messageId);
        doesMessageExist = Messaging.MessageExists(messageId);
        assertFalse(doesMessageExist);
    }

    @org.junit.Test
    public void Should_Add_And_Delete_A_User_Challenge()
    {
        int messageId = Messaging.addMessage(
                new Message(5,2, "challenge", "this is a challenge sent from 2 to recipient 5")
        );

        boolean doesMessageExist = Messaging.MessageExists(messageId);
        assertTrue(doesMessageExist);

        Messaging.removeMessage(messageId);
        doesMessageExist = Messaging.MessageExists(messageId);
        assertFalse(doesMessageExist);
    }

    @org.junit.Test
    public void Should_Get_Sample_Note()
    {
        Message msg = Messaging.getMessage(2);
        String expected = "Note 2 with a lot of extra text to make sure it can store a lot.";
        String actual = msg.getContent();

        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void Should_Get_Sample_Challenge()
    {
        Message msg = Messaging.getMessage(4);
        String expected = "Youre being challenged!";
        String actual = msg.getContent();

        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void Should_Get_Friend_Request()
    {
        Message msg = Messaging.getMessage(7);
        String expected = "Rejected";
        String actual = msg.getContent();

        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void Should_Get_List_Of_Notes()
    {
        List<Message> messages = Messaging.getMessages(1, "note");

        assertTrue(messages.size() > 1);
    }

    @org.junit.Test
    public void Should_Get_List_Of_FriendRequests()
    {
        List<Message> messages = Messaging.getMessages(2, "friend");

        assertTrue(messages.size() == 1);
        assertEquals(messages.get(0).getContent(), "Sent");
    }

    @org.junit.Test
    public void Should_Get_List_Of_Challenges()
    {
        List<Message> messages = Messaging.getMessages(1, "challenge");

        assertTrue(messages.size() > 1);
    }

    @org.junit.Test
    public void Should_Get_List_Of_All_Messages()
    {
        List<Message> messages = Messaging.getMessages(1);

        assertTrue(messages.size() > 3);
    }

    @org.junit.Test
    public void Can_Determine_If_Friend_Request_Exists()
    {
        boolean friendRequestShouldExist = Messaging.requestExists(3, 2);
        boolean friendRequestShouldNotExist = Messaging.requestExists(1, 2);

        assertTrue(friendRequestShouldExist);
        assertFalse(friendRequestShouldNotExist);
    }
}
