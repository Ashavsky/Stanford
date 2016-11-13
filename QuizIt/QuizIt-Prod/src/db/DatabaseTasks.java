package db;


import com.mysql.jdbc.Statement;
import quiz.QuizAttempt;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 3/2/2016.
 */
public class DatabaseTasks
{
    public static void ExecuteUpdate(String sql)
    {
        try
        {
            Statement stmt = db.DBConnection.getStatement();
            stmt.executeQuery("SET @@auto_increment_increment=1; ");

            stmt.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean CheckIfRecordExistsWithParameterString(String tableName, String parameterName, String parameterValue)
    {
        String query = String.format("Select * from %1$s WHERE %2$s = %3$s;", tableName, parameterName, "'" + parameterValue + "'");
        boolean doesRecordExist = CheckIfRecordExists(query);
        return doesRecordExist;
    }

    public static boolean CheckIfRecordExistsWithParameterInt(String tableName, String parameterName, String parameterValue)
    {
        String query = String.format("Select * from %1$s WHERE %2$s = %3$s;", tableName, parameterName, parameterValue);
        boolean doesRecordExist = CheckIfRecordExists(query);
        return doesRecordExist;
    }

    public static boolean CheckIfRecordExistsWithParametersIntInt(String tableName, String parameterName, String parameterValue, String parameterName2, String paramaterValue2)
    {
        String query = String.format("Select * from %1$s WHERE %2$s = %3$s and %4$s = %5$s;", tableName, parameterName, parameterValue, parameterName2, paramaterValue2);
        boolean doesRecordExist = CheckIfRecordExists(query);
        return doesRecordExist;
    }

    public static boolean CheckIfRecordExistsWithParametersIntString(String tableName, String parameterName, String parameterValue, String parameterName2, String paramaterValue2)
    {
        String query = String.format("Select * from %1$s WHERE %2$s = %3$s and %4$s = '%5$s';", tableName, parameterName, parameterValue, parameterName2, paramaterValue2);
        boolean doesRecordExist = CheckIfRecordExists(query);
        return doesRecordExist;
    }

    public static boolean CheckIfRecordExists(String query)
    {
        boolean doesRecordExist = false;

        try
        {
            ResultSet rs = GetResultSet(query);
            doesRecordExist = rs.next(); //if row exists, record is found, can return true
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return doesRecordExist;
    }

    public static boolean CheckIfMultipleRecordsExists(String query)
    {
        boolean doesRecordExist = false;

        try
        {
            ResultSet rs = GetResultSet(query);
            rs.next();
            doesRecordExist = rs.next(); //if 2nd row exists, can return true
            
        }
        catch (SQLException e)
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
            ResultSet rs = GetResultSet("Count(*)", tableName);

            rs.next(); // exactly one result so allowed
            count = rs.getInt(1);
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return count;
    }

    public static ResultSet GetResultSetWithParameter(String tableName, String parameterName, String parameterValue)
    {
        String query = String.format("Select * from %1$s WHERE %2$s = %3$s;", tableName, parameterName, parameterValue);
        ResultSet rs = GetResultSet(query);
        return rs;
    }

    public static ResultSet GetResultSet(String selectionType, String tableName)
    {
        String query = String.format("Select " + selectionType + " from %1$s;", tableName);
        ResultSet rs = GetResultSet(query);
        return rs;
    }

    public static ResultSet GetResultSet(String query)
    {
        ResultSet rs = null;

        try
        {
            Statement stmt = db.DBConnection.getStatement();

            rs = stmt.executeQuery(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return rs;
    }
}