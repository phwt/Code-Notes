/*
 * Code-Notes | ExceptionDatabase.java
 * Created | 6:16:27 PM Oct 12, 2019
 */
package code.notes.util;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phwts
 */
public class ExceptionDatabase {

    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rst;
    public static String url = "jdbc:derby:exceptiondb;create=true";

    public static void createConnection() throws SQLException {
        String path = Paths.get(System.getProperty("user.dir"), ".derby").toAbsolutePath().toString();
        System.setProperty("derby.system.home", path);
        conn = DriverManager.getConnection(url);
    }

    public static void closeConnection() throws SQLException {
        DriverManager.getConnection("jdbc:derby:;shutdown=true");
    }

    public static void catchSQLEx(SQLException ex) {
        Logger lgr = Logger.getLogger(ExceptionDatabase.class.getName());
        if (((ex.getErrorCode() == 50000) && ("XJ015".equals(ex.getSQLState())))) {
            lgr.log(Level.INFO, "Derby shut down normally", ex);
        } else {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public static void finallySQL() {
        try {
            if (rst != null) {
                rst.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ExceptionDatabase.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

//    public static void main(String[] args) {
//
//        try {
//            ExceptionDatabase.createConnection();
//
//            stmt = ExceptionDatabase.conn.createStatement();
//            stmt.executeUpdate("CREATE TABLE EXCEPTION(ID INT PRIMARY KEY, NAME VARCHAR(30), PRICE INT)");
//            stmt.executeUpdate("INSERT INTO CARS VALUES(1, 'Audi', 52642)");
//            stmt.executeUpdate("INSERT INTO CARS VALUES(2, 'Mercedes', 57127)");
//            stmt.executeUpdate("INSERT INTO CARS VALUES(3, 'Skoda', 9000)");
//            stmt.executeUpdate("INSERT INTO CARS VALUES(4, 'Volvo', 29000)");
//            stmt.executeUpdate("INSERT INTO CARS VALUES(5, 'Bentley', 350000)");
//            stmt.executeUpdate("INSERT INTO CARS VALUES(6, 'Citroen', 21000)");
//            stmt.executeUpdate("INSERT INTO CARS VALUES(7, 'Hummer', 41400)");
//            stmt.executeUpdate("INSERT INTO CARS VALUES(8, 'Volkswagen', 21600)");
//
//            ExceptionDatabase.closeConnection();
//        } catch (SQLException ex) {
//            ExceptionDatabase.catchSQLEx(ex);
//        } finally {
//            ExceptionDatabase.finallySQL();
//        }
//    }

}
