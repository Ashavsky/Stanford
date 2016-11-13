package User;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 3/2/2016.
 */
public class DatabaseTasks
{
    public static void InsertAchievement(int userId, String achievementName, String achievementDesc)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);
            stmt.executeQuery("SET @@auto_increment_increment=1; ");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String tempToolTip = "'sometooltip.jpg'";

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO UserAchievements VALUES(");
            sb.append("null,");
            sb.append(userId + ",");
            sb.append("'" + achievementName + "',");
            sb.append("'" + achievementDesc+ "',");
            sb.append("'" + formatter.format(new Date()) + "',");
            sb.append(tempToolTip + ");");

            stmt.executeUpdate(sb.toString());
            con.close();
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void InsertUserDetail(String userName, String email, String password, String salt, boolean isAdmin)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);
            stmt.executeQuery("SET @@auto_increment_increment=1; ");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO UserDetail VALUES(");
            sb.append("null,");
            sb.append("'" + userName + "',");
            sb.append("'" + password + "',");
            sb.append("'" + salt + "',");
            sb.append(String.valueOf(isAdmin) + ",");
            sb.append("'" + formatter.format(new Date()) + "',");
            sb.append("'" + email + "');");

            stmt.executeUpdate(sb.toString());
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void InsertUserFriend(int userId, int friendUserId)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);
            stmt.executeQuery("SET @@auto_increment_increment=1; ");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO UserFriends VALUES(");
            sb.append(userId + ",");
            sb.append(friendUserId + ",");
            sb.append("'" + formatter.format(new Date()) + "');");

            stmt.executeUpdate(sb.toString());

            StringBuilder sb2 = new StringBuilder();
            sb2.append("INSERT INTO UserFriends VALUES(");
            sb2.append(friendUserId + ",");
            sb2.append(userId + ",");
            sb2.append("'" + formatter.format(new Date()) + "');");

            Statement stmt2 = (Statement) con.createStatement();
            stmt2.executeUpdate(sb2.toString());
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public static  int InsertUserMessage(Message msg)
    {
        int msgId = 0;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);
            stmt.executeQuery("SET @@auto_increment_increment=1; ");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO UserSocial VALUES(");
            sb.append("null,");
            sb.append(msg.getRecipient()+ ",");
            sb.append(msg.getSender() + ",");
            sb.append("'" + msg.getType() + "',");
            sb.append("'" + formatter.format(new Date()) + "');");
            stmt.executeUpdate(sb.toString());

            String query = String.format("Select * from UserSocial WHERE %1$s = %2$s order by MessageId desc LIMIT 1;", "UserId", msg.getRecipient());
            ResultSet rs = stmt.executeQuery(query);
            rs.next(); // exactly one result so allowed
            msgId = rs.getInt(1);

            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return msgId;
    }

    public static void InsertUserFriendRequest(Message msg)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);
            stmt.executeQuery("SET @@auto_increment_increment=1; ");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO UserFriendRequests VALUES(");
            sb.append(msg.getMessageId()+ ",");
            sb.append("'" + "Sent" + "',");
            sb.append("false" + ");");

            stmt.executeUpdate(sb.toString());
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void InsertUserNote(Message msg)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);
            stmt.executeQuery("SET @@auto_increment_increment=1; ");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO UserNotes VALUES(");
            sb.append(msg.getMessageId()+ ",");
            sb.append("'" + msg.getContent() + "');");

            stmt.executeUpdate(sb.toString());
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void InsertUserChallenge(Message msg) //Need info from quizzes here!
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);
            stmt.executeQuery("SET @@auto_increment_increment=1; ");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO UserChallenges VALUES(");
            sb.append(msg.getMessageId()+ ",");
            sb.append("'" + msg.getContent() + "',");
            sb.append("'" + "Need to get info from quiz here" + "',");
            sb.append(-1 + ");");

            stmt.executeUpdate(sb.toString());
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void DeleteAchievement(int userId, String achievementName)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Delete from UserAchievements WHERE UserId = %1$s and AchievementName = '%2$s';", userId, achievementName);
            stmt.executeUpdate(query.toString());

            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void DeleteUserMessage(int messageId, String tableName)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Delete from UserSocial WHERE MessageId = %1$s;", messageId);
            stmt.executeUpdate(query.toString());

            query = String.format("Delete from %1$s WHERE MessageId = %2$s;", tableName, messageId);
            stmt.executeUpdate(query.toString());

            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void DeleteUserFriendship(int userId, int friendId)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Delete from UserFriends WHERE UserId = %1$s and FriendId = %2$s;", userId, friendId );
            stmt.executeUpdate(query.toString());

            query = String.format("Delete from UserFriends WHERE UserId = %1$s and FriendId = %2$s;", friendId, userId );
            stmt.executeUpdate(query.toString());

            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void DeleteUserDetail(String userName)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Delete from UserDetail WHERE UserName = %1$s;", "'" + userName + "'");

            stmt.executeUpdate(query.toString());
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void PromoteUserToAdmin(String userName) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection
                    ("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("UPDATE UserDetail SET AdminFlag='1' WHERE UserName = %1$s;", "'" + userName + "'");

            stmt.executeUpdate(query.toString());
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<User> GetUsers()
    {
        List<User> userList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            ResultSet rs = GetResultSet(con, "*", "UserDetail");

            while(rs.next())
            {
                User foundUser = new User();
                foundUser.userName = rs.getString("UserName");
                foundUser.userId = Integer.parseInt(rs.getString("UserId"));
                foundUser.email = rs.getString("UserEmail");
                foundUser.dateCreated = rs.getString("UserCreateDate");
                String isAdminst = rs.getString("AdminFlag");
                foundUser.isAdmin = isAdminst.equals("1");
                userList.add(foundUser);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return userList;
    }

    public static HashedPassword GetPasswordInfo(String userName)
    {
        HashedPassword foundUser = null;
        try {
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            ResultSet rs = GetResultSetWithParameter(con, "UserDetail", "UserName", "'" + userName + "'");

            if(rs.next())
            {
                String hashedPass = rs.getString("UserPassword");
                String hashedSalt = rs.getString("UserSalt");
                foundUser = new HashedPassword(hashedPass, hashedSalt);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return foundUser;
    }

    public static User GetUser(String userName)
    {
        User foundUser = null;

        try {
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            ResultSet rs = GetResultSetWithParameter(con, "UserDetail", "UserName", "'" + userName + "'");

            if(rs.next())
            {
                foundUser = new User();
                foundUser.userName = rs.getString("UserName");
                foundUser.userId = Integer.parseInt(rs.getString("UserId"));
                foundUser.email = rs.getString("UserEmail");
                foundUser.dateCreated = rs.getString("UserCreateDate");
                String isAdminst = rs.getString("AdminFlag");
                foundUser.isAdmin = isAdminst.equals("1");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return foundUser;
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
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            if(messageType.equals("note"))
            {
                String query = String.format("Select us.MessageId, UserId, FriendId, MessageType, MessageDate, MessageText as Content " +
                        "from UserSocial us inner join UserNotes un on us.MessageId = un.MessageId " +
                        "where UserId = 1;", userId);
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

            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
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
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            ResultSet rs = GetResultSetWithParameter(con, "UserSocial", "MessageId", Integer.toString(messageId));

            if(rs.next())
            {
                userId = Integer.parseInt(rs.getString("UserId"));
                friendId = Integer.parseInt(rs.getString("FriendId"));
                messageType = rs.getString("MessageType");
                messageDate =  rs.getString("MessageDate");
            }

            if(messageType.equals("note"))
            {
                rs = GetResultSetWithParameter(con, "UserNotes", "MessageId", Integer.toString(messageId));
                if(rs.next())
                {
                    content = rs.getString("MessageText");
                    foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate);
                }
            }
            else if(messageType.equals("friend"))
            {
                rs = GetResultSetWithParameter(con, "UserFriendRequests", "MessageId", Integer.toString(messageId));
                if(rs.next())
                {
                    content = rs.getString("RequestStatus");
                    friendAccepted = Boolean.parseBoolean(rs.getString("FriendAccepted"));
                    foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate, friendAccepted);
                }
            }
            else
            {
                rs = GetResultSetWithParameter(con, "UserChallenges", "MessageId", Integer.toString(messageId));
                if(rs.next())
                {
                    content = rs.getString("ChallengerNote");
                    quizChallengeScore = rs.getString("ChallengeScore");
                    foundMessage = new Message(messageId, userId, friendId, messageType, content, messageDate, quizChallengeScore, quizChallengeId);
                }
            }

            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return foundMessage;
    }

    public static boolean CheckIfRecordExistsWithParameterString(String tableName, String parameterName, String parameterValue)
    {
        boolean doesRecordExist = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            ResultSet rs = GetResultSetWithParameter(con, tableName, parameterName, "'" + parameterValue + "'");

            doesRecordExist = rs.next(); //if row exists, record is found, can return true
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return doesRecordExist;
    }

    public static boolean CheckIfRecordExistsWithParameterInt(String tableName, String parameterName, String parameterValue)
    {
        boolean doesRecordExist = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            ResultSet rs = GetResultSetWithParameter(con, tableName, parameterName, parameterValue);

            doesRecordExist = rs.next(); //if row exists, record is found, can return true
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return doesRecordExist;
    }

    public static boolean CheckIfRecordExistsWithParametersIntInt(String tableName, String parameterName, String parameterValue, String parameterName2, String paramaterValue2)
    {
        boolean doesRecordExist = false;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Select * from %1$s WHERE %2$s = %3$s and %4$s = %5$s;", tableName, parameterName, parameterValue, parameterName2, paramaterValue2);
            ResultSet rs = stmt.executeQuery(query);

            doesRecordExist = rs.next(); //if row exists, record is found, can return true
            con.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return doesRecordExist;
    }

    public static boolean CheckIfRecordExistsWithParametersIntString(String tableName, String parameterName, String parameterValue, String parameterName2, String paramaterValue2)
    {
        boolean doesRecordExist = false;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Select * from %1$s WHERE %2$s = %3$s and %4$s = '%5$s';", tableName, parameterName, parameterValue, parameterName2, paramaterValue2);
            ResultSet rs = stmt.executeQuery(query);

            doesRecordExist = rs.next(); //if row exists, record is found, can return true
            con.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return doesRecordExist;
    }

    public static int GetCountRecordsFromTable(String tableName)
    {
        int count = 0;
        try
        {
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            ResultSet rs = GetResultSet(con, "Count(*)", tableName);

            rs.next(); // exactly one result so allowed
            count = rs.getInt(1);
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return count;
    }

    public static ResultSet GetResultSetWithParameter(Connection con,String tableName, String parameterName, String parameterValue)
    {
        ResultSet rs = null;

        try
        {
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Select * from %1$s WHERE %2$s = %3$s;", tableName, parameterName, parameterValue);
            rs = stmt.executeQuery(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return rs;
    }

    public static ResultSet GetResultSet(Connection con,String selectionType, String tableName)
    {
        ResultSet rs = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Select " + selectionType + " from %1$s;", tableName);
            rs = stmt.executeQuery(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return rs;
    }

    public static ResultSet GetRows(String tableName)
    {
        ResultSet rs = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Select * from %1$s;", tableName);
//            String query = String.format("Select UserDetail.UserName from UserFriends join UserDetail on UserFriends.FriendId = UserDetail.UserId WHERE UserFriends.UserId = 1;");
            rs = stmt.executeQuery(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return rs;
    }

    //Was just used to test handout example
    public static void main(String[] args)
    {
        GetResultSet();
    }

    public static void GetResultSet()
    {
        ResultSet rs = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);

            String query = String.format("Select UserDetail.UserName from UserFriends join UserDetail on UserFriends.FriendId = UserDetail.UserId WHERE UserFriends.UserId = 1;");
            rs = stmt.executeQuery(query);

            while(rs.next())
            {
                String name = rs.getString("UserName");
                System.out.println("Friend Name: " + name);
            }

            ResultSet rs2 = GetRows("UserDetail");
            ResultSetMetaData rsmd = (ResultSetMetaData) rs2.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            for (int i = 1; i <= columnsNumber; i++)
            {
                System.out.print(rsmd.getColumnName(i) + ",  ");
                if (i == columnsNumber) System.out.println(";");
            }

            while (rs2.next()) {
                for (int i = 1; i <= columnsNumber; i++)
                {
                    String columnValue = rs2.getString(i);
                    System.out.print(columnValue + ", ");
                    if (i == columnsNumber) System.out.println(";");
                }
                System.out.println("");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}