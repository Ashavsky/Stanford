package User;

import java.util.*;

public class Messaging implements IMessaging
{
	private static Map<Integer, List<Integer>> messages = new HashMap<Integer, List<Integer>>();
    private static Map<Integer, Set<Integer>> dbFriendRequests = new HashMap<Integer, Set<Integer>>();
    private static Map<Integer, Set<Integer>> dbMessages = new HashMap<Integer, Set<Integer>>();
	
	public static int addMessage(Message msg) //may throw MySqlException that duplicate entry, this is OK - SQL prevent duplicate entries which is good
	{
		int newMessageId = DatabaseTasks.InsertUserMessage(msg);
		msg.setMessageId(newMessageId);

		if(msg.getType().equals("friend"))
			DatabaseTasks.InsertUserFriendRequest(msg);
		else if(msg.getType().equals("note"))
			DatabaseTasks.InsertUserNote(msg);
		else
			DatabaseTasks.InsertUserChallenge(msg);

		return newMessageId;
	}

	public static boolean MessageExists(int messageId)
	{
		return DatabaseTasks.CheckIfRecordExistsWithParameterInt("UserSocial", "MessageId",  Integer.toString(messageId));
	}

	public static void removeMessage(int messageId)
	{
		if(MessageExists(messageId))
		{
			Message msg = getMessage(messageId);

			if(msg.getType().equals("friend"))
				DatabaseTasks.DeleteUserMessage(messageId, "UserFriendRequests");
			else if(msg.getType().equals("note"))
				DatabaseTasks.DeleteUserMessage(messageId, "UserNotes");
			else
				DatabaseTasks.DeleteUserMessage(messageId, "UserChallenges");
		}
	}

	public static Message getMessage(int id)
	{
		Message msg = null;

		if(MessageExists(id))
			msg = DatabaseTasks.GetMessage(id);

		return msg;
	}

	public static List<Message> getFriendRequests(int userId)
	{
		List<Message> messageList = null;
		messageList = getMessages(userId, "friend");
		return messageList;
	}

	public static List<Message> getChallenges(int userId)
	{
		List<Message> messageList = null;
		messageList = getMessages(userId, "challenge");
		return messageList;
	}

	public static List<Message> getNotes(int userId)
	{
		List<Message> messageList = null;
		messageList = getMessages(userId, "note");
		return messageList;
	}

	public static List<Message> getMessages(int userId)
	{
		List<Message> messageList = null;
		messageList = getMessages(userId, "");
		return messageList;
	}

    public static List<Message> getMessages(int userId, String messageType)
	{
		List<Message> messageList = null;
		messageList = DatabaseTasks.GetMessages(userId, messageType);
		return messageList;
    }
    
    public static boolean requestExists(int sender, int recipient)
	{
    	return DatabaseTasks.CheckIfRecordExistsWithParametersIntInt("UserSocial us inner join UserFriendRequests uf on us.MessageId = uf.MessageId", "UserId", Integer.toString(recipient), "FriendId", Integer.toString(sender));
    }
    
    public static int addFriendRequest(int sender, int recipient)
	{
    	Message msg = new Message(1,2,"friend", "text doesn't matter here");
		return addMessage(msg);
    }
    
    public static void removeFriendRequest(int sender, int recipient)
	{
    	if (dbFriendRequests.containsKey(recipient) && 
    			dbFriendRequests.get(recipient).contains(sender)) {
    		dbFriendRequests.get(recipient).remove(sender);
    	}
    }
}