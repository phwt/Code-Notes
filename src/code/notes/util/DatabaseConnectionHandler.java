/*
 * Code-Notes | DatabaseConnectionHandler.java
 * Created | 8:06:44 PM Nov 9, 2019
 */
package code.notes.util;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phwts
 */
public class DatabaseConnectionHandler {

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rst;
    private static String url = "jdbc:derby:exceptiondb;";

    public static void createConnection() throws SQLException {
        System.out.println(Paths.get(System.getProperty("user.dir"), ".derby").toAbsolutePath().toString());

        System.setProperty("derby.system.home", Paths.get(System.getProperty("user.dir"), ".derby").toAbsolutePath().toString());
        conn = DriverManager.getConnection(url);
    }

    public static void closeConnection() throws SQLException {
        DriverManager.getConnection("jdbc:derby:;shutdown=true");
    }

    public static Set selectData(String table_name) throws SQLException {
        stmt = conn.createStatement();
        rst = stmt.executeQuery("SELECT * FROM APP." + table_name);
        
        Set result = new HashSet();
        while (rst.next()) {
            String result_each[] = {rst.getString(1), rst.getString(2)};
            result.add(result_each);
        }
        return result;
    }

    public static Connection getConn() {
        return conn;
    }

    public static Statement getStmt() {
        return stmt;
    }

    public static ResultSet getRst() {
        return rst;
    }

    public static void main(String[] args) {
        try {
            DatabaseConnectionHandler.createConnection();
            Set datas = DatabaseConnectionHandler.selectData("EXCEPTION_PYTHON");
            for(Object data : datas){
                System.out.print(((String[]) data)[0]);
                System.out.print(((String[]) data)[1]);
            }
            
            DatabaseConnectionHandler.closeConnection();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(TestSelect.class.getName());
            if (((ex.getErrorCode() == 50000)
                    && ("XJ015".equals(ex.getSQLState())))) {
                lgr.log(Level.INFO, "Derby shut down normally", ex);
            } else {
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } finally {
            try {
                if (DatabaseConnectionHandler.rst != null) {
                    DatabaseConnectionHandler.rst.close();
                }
                if (DatabaseConnectionHandler.stmt != null) {
                    DatabaseConnectionHandler.stmt.close();
                }
                if (DatabaseConnectionHandler.conn != null) {
                    DatabaseConnectionHandler.conn.close();
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(TestSelect.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}
