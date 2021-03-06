package ro.teamnet.zth.api.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mi on 4/28/2015.
 */
public class DBManager {

    static final String CONNECTION_STRING = "jdbc:mysql://" + DProperties.IP + "/" + DProperties.SCHEMA;

    private DBManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver() {
        try {
            Class.forName(DProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        registerDriver();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING, DProperties.USER, DProperties.PAROLA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /* TODO */
    public static boolean checkConnection() {
        Connection conn = getConnection();
        java.sql.Statement stm = null;
        try {
            stm = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = stm.executeQuery("SELECT 1 FROM DUAL;");
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
