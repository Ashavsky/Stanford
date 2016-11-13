package user;

import java.util.*;

public class SocialRepository implements ISocialRepository
{
	public static int addMessage(Message msg) //may throw MySqlException that duplicate entry, this is OK - SQL prevent duplicate entries which is good
	{
		int newMessageId = db.SocialPersistence.InsertUserMessage(msg);
		msg.setMessageId(newMessageId);

		if(msg.getType().equals("friend"))
			db.SocialPersistence.InsertUserFriendRequest(msg);
		else if(msg.getType().equals("note"))
			db.SocialPersistence.InsertUserNote(msg);
		else
			db.SocialPersistence.InsertUserChallenge(msg);

		return newMessageId;
	}

	public static boolean MessageExists(int messageId)
	{
		return db.DatabaseTasks.CheckIfRecordExistsWithParameterInt("UserSocial", "MessageId",  Integer.toString(messageId));
	}

	public static void removeMessage(int messageId)
	{
		if(MessageExists(messageId))
		{
			Message msg = getMessage(messageId);

			if(msg.getType().equals("friend"))
				db.UserPersistence.DeleteUserMessage(messageId, "UserFriendRequests");
			else if(msg.getType().equals("note"))
				db.UserPersistence.DeleteUserMessage(messageId, "UserNotes");
			else
				db.UserPersistence.DeleteUserMessage(messageId, "UserChallenges");
		}
	}

	public static Message getMessage(int id)
	{
		Message msg = null;

		if(MessageExists(id))
			msg = db.SocialPersistence.GetMessage(id);

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
		messageList = db.SocialPersistence.GetMessages(userId, messageType);
		return messageList;
    }
    
    public static boolean requestExists(int sender, int recipient)
	{
    	return db.DatabaseTasks.CheckIfRecordExistsWithParametersIntInt("UserSocial us inner join UserFriendRequests uf on us.MessageId = uf.MessageId", "UserId", Integer.toString(recipient), "FriendId", Integer.toString(sender));
    }

}