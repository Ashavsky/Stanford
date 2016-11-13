package db;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by scottparsons on 3/10/16.
 */
public class DBConnection {
    private static DBConnection _dbConnectionSingleton = null;
    private static Connection _conn = null;
    private static Statement _stmt = null;
    private boolean _flag = true;

    /**
     * A private Constructor prevents any other class from instantiating.
     */
    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            _flag = false;
        } catch (IllegalAccessException e) {
            _flag = false;
        } catch (ClassNotFoundException e) {
            _flag = false;
        }
        if (_flag) {
            openConnection();
        }
    }

    public Connection openConnection() {
        if (_conn == null) {
            try {
                _conn = (Connection) DriverManager.getConnection
                        ("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
                _stmt = (Statement) _conn.createStatement();
                _stmt.executeQuery("USE " +  MyDBInfo.MYSQL_DATABASE_NAME);
            } catch (SQLException e) {
                _flag = false;
            }
        }
        return _conn;
    }

    /**
     * Static 'instance' method
     */
    public static DBConnection getInstance() {
        if (_dbConnectionSingleton == null) {
            _dbConnectionSingleton = new DBConnection();
        }
        return _dbConnectionSingleton;
    }

    public static Connection getConnection() {
        return getInstance()._conn;
    }

    public static Statement getStatement() {
        return getInstance()._stmt;
    }

    public static void closeConnection() {
        try {
            _conn.close();
        } catch (SQLException e) {
        }
    }

    public boolean getConnectionStatus() {
        return _flag;
    }
}
