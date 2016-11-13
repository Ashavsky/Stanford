package db;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import user.Activity;
import user.Message;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SocialPersistence
{
    public static  int InsertUserMessage(Message msg)
    {
        int msgId = 0;
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO UserSocial VALUES(");
            sb.append("null,");
            sb.append(msg.getRecipient()+ ",");
            sb.append(msg.getSender() + ",");
            sb.append("'" + msg.getType() + "',");
            sb.append("'" + formatter.format(new Date()) + "');");
            DatabaseTasks.ExecuteUpdate(sb.toString());

            String query = String.format("Select * from UserSocial WHERE %1$s = %2$s order by MessageId desc LIMIT 1;", "UserId", msg.getRecipient());
            ResultSet rs = DatabaseTasks.GetResultSet(query);
            rs.next(); // exactly one result so allowed
            msgId = rs.getInt(1);


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return msgId;
    }

    public static void InsertUserFriendRequest(Message msg)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO UserFriendRequests VALUES(");
        sb.append(msg.getMessageId()+ ",");
        sb.append("'" + msg.getContent() + "',");
        sb.append("false" + ");");

        DatabaseTasks.ExecuteUpdate(sb.toString());
    }

    public static void InsertUserNote(Message msg)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO UserNotes VALUES(");
        sb.append(msg.getMessageId()+ ",");
        sb.append("'" + msg.getContent() + "');");

        DatabaseTasks.ExecuteUpdate(sb.toString());
    }

    public static void InsertUserChallenge(Message msg) //Need info from quizzes here!
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO UserChallenges VALUES(");
        sb.append(msg.getMessageId()+ ",");
        sb.append("'" + msg.getContent() + "',");
        sb.append("'" + "Need to get info from quiz here" + "',");
        sb.append(-1 + ");");

        DatabaseTasks.ExecuteUpdate(sb.toString());
    }

    public static List<Message> GetMessages(int userId, String messageType)
    {
        List<Message> foundMessages = new ArrayList<>();
        int messageId = 0;
        int friendId = 0;
        String messageDate = "";
        String content = "";
        boolean friendAccepted = false;
        int quizChallengeId = 0;
        String quizChallengeScore = "";
        ResultSet rs;

        try
        {
            Statement stmt = db.DBConnection.getStatement();

            if(messageType.equals("note"))
            {
                String query = String.format("Select us.MessageId, UserId, FriendId, MessageType, MessageDate, MessageText as Content " +
                        "from UserSocial us inner join UserNotes un on us.MessageId = un.MessageId " +
                        "where UserId = %1$s;", userId);
                rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    messageId = Integer.parseInt(rs.getString("MessageId"));
                    userId = Integer.parseInt(rs.getString("UserId"));
                    friendId = Integer.parseInt(rs.getString("FriendId"));
                    messageType = rs.getString("MessageType");
                    messageDate =  rs.getString("MessageDate");
                    content = rs.getString("Content");
                    Message foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate);
                    foundMessages.add(foundMessage);
                }
            }
            else if(messageType.equals("friend"))
            {
                String query = String.format("Select us.MessageId, UserId, FriendId, MessageType, MessageDate, RequestStatus as Content, FriendAccepted " +
                        "from UserSocial us inner join UserFriendRequests uf on us.MessageId = uf.MessageId " +
                        "where UserId = %1$s;", userId);
                rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    messageId = Integer.parseInt(rs.getString("MessageId"));
                    userId = Integer.parseInt(rs.getString("UserId"));
                    friendId = Integer.parseInt(rs.getString("FriendId"));
                    messageType = rs.getString("MessageType");
                    messageDate =  rs.getString("MessageDate");
                    content = rs.getString("Content");
                    friendAccepted = Boolean.parseBoolean(rs.getString("FriendAccepted"));
                    Message foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate, friendAccepted);
                    foundMessages.add(foundMessage);
                }
            }
            else if(messageType.equals("challenge"))
            {
                String query = String.format("Select us.MessageId, UserId, FriendId, MessageType, MessageDate, ChallengerNote as Content, ChallengeScore, ChallengerQuizId " +
                        "from UserSocial us inner join UserChallenges uc on us.MessageId = uc.MessageId " +
                        "where UserId = %1$s;", userId);
                rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    messageId = Integer.parseInt(rs.getString("MessageId"));
                    userId = Integer.parseInt(rs.getString("UserId"));
                    friendId = Integer.parseInt(rs.getString("FriendId"));
                    messageType = rs.getString("MessageType");
                    messageDate =  rs.getString("MessageDate");
                    content = rs.getString("Content");
                    quizChallengeScore = rs.getString("ChallengeScore");
                    quizChallengeId = Integer.parseInt(rs.getString("ChallengerQuizId"));
                    Message foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate, quizChallengeScore, quizChallengeId);
                    foundMessages.add(foundMessage);
                }
            }
            else
            {
                String query = String.format("Select us.MessageId, UserId, FriendId, MessageType, MessageDate, MessageText, RequestStatus, FriendAccepted, " +
                        "ChallengerNote, ChallengeScore, ChallengerQuizId from UserSocial us " +
                        "left join UserChallenges uc on us.MessageId = uc.MessageId left join UserNotes un on us.MessageId = un.MessageId " +
                        "left join UserFriendRequests uf on us.MessageId = uf.MessageId " +
                        "where UserId = %1$s;", userId);
                rs = stmt.executeQuery(query);

                while(rs.next())
                {
                    messageId = Integer.parseInt(rs.getString("MessageId"));
                    userId = Integer.parseInt(rs.getString("UserId"));
                    friendId = Integer.parseInt(rs.getString("FriendId"));
                    messageType = rs.getString("MessageType");
                    messageDate =  rs.getString("MessageDate");
                    Message foundMessage = null;

                    if(messageType.equals("note"))
                    {
                        content = rs.getString("MessageText");
                        foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate);
                    }
                    else if(messageType.equals("friend"))
                    {
                        content = rs.getString("RequestStatus");
                        friendAccepted = Boolean.parseBoolean(rs.getString("FriendAccepted"));
                        foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate, friendAccepted);
                    }
                    else
                    {
                        content = rs.getString("ChallengerNote");
                        quizChallengeScore = rs.getString("ChallengeScore");
                        quizChallengeId = Integer.parseInt(rs.getString("ChallengerQuizId"));
                        foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate, quizChallengeScore, quizChallengeId);
                    }

                    foundMessages.add(foundMessage);
                }
            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return foundMessages;
    }

    public static Message GetMessage(int messageId)
    {
        Message foundMessage = null;
        int userId = 0;
        int friendId = 0;
        String messageType = "";
        String messageDate = "";
        String content = "";
        boolean friendAccepted = false;
        int quizChallengeId = 0;
        String quizChallengeScore = "";

        try
        {
            ResultSet rs = DatabaseTasks.GetResultSetWithParameter("UserSocial", "MessageId", Integer.toString(messageId));

            if(rs.next())
            {
                userId = Integer.parseInt(rs.getString("UserId"));
                friendId = Integer.parseInt(rs.getString("FriendId"));
                messageType = rs.getString("MessageType");
                messageDate =  rs.getString("MessageDate");
            }

            if(messageType.equals("note"))
            {
                rs = DatabaseTasks.GetResultSetWithParameter("UserNotes", "MessageId", Integer.toString(messageId));
                if(rs.next())
                {
                    content = rs.getString("MessageText");
                    foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate);
                }
            }
            else if(messageType.equals("friend"))
            {
                rs =  DatabaseTasks.GetResultSetWithParameter("UserFriendRequests", "MessageId", Integer.toString(messageId));
                if(rs.next())
                {
                    content = rs.getString("RequestStatus");
                    friendAccepted = Boolean.parseBoolean(rs.getString("FriendAccepted"));
                    foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate, friendAccepted);
                }
            }
            else
            {
                rs = DatabaseTasks.GetResultSetWithParameter("UserChallenges", "MessageId", Integer.toString(messageId));
                if(rs.next())
                {
                    content = rs.getString("ChallengerNote");
                    quizChallengeScore = rs.getString("ChallengeScore");
                    foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate, quizChallengeScore, quizChallengeId);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return foundMessage;
    }

    public static List<Integer> GetFriendsIds(int userId)
    {
        List<Integer> friendIds = new ArrayList<Integer>();

        try {
            ResultSet rs = DatabaseTasks.GetResultSetWithParameter("UserFriends", "UserId", "'" + userId + "'");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            while(rs.next())
            {
                int Id = Integer.parseInt(rs.getString("FriendId"));
                friendIds.add(Id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return friendIds;
    }
}
