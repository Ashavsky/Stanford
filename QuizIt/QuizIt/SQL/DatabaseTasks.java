package QuizIt;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 3/2/2016.
 */
public class DatabaseTasks
{
    static String account = MyDBInfo.MYSQL_USERNAME;
    static String password = MyDBInfo.MYSQL_PASSWORD;
    static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
    static String database = MyDBInfo.MYSQL_DATABASE_NAME;

    public static ResultSet GetResultSet(Connection con, String tableName, String parameterName, int parameterValue)
    {
        ResultSet rs = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " + database);

            String query = String.format("Select * from %1$s WHERE %2$s = %3$d;", tableName, parameterName, parameterValue);
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

    public static ResultSet GetResultSet(Connection con, String tableName)
    {
        ResultSet rs = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " + database);

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

    public static ResultSet GetResultSet(Connection con)
    {
        ResultSet rs = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery("USE " + database);

            String query = String.format("Select UserDetail.UserName from UserFriends join UserDetail on UserFriends.FriendId = UserDetail.UserId WHERE UserFriends.UserId = 1;");
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

        try
        {
            Connection conNew = (Connection) DriverManager.getConnection
                    ( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME ,MyDBInfo.MYSQL_PASSWORD);

            ResultSet rs = GetResultSet(conNew);
            while(rs.next())
            {
                String name = rs.getString("UserName");
                System.out.println("Friend Name: " + name);
            }

            ResultSet rs2 = GetResultSet(conNew, "UserDetail");
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

            conNew.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
