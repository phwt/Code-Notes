/*
 * Code-Notes | ExceptionLookup.java
 * Created | 6:52:43 PM Oct 12, 2019
 */
package code.notes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author phwts
 */
public class ExceptionLookup {
    public static void main(String[] args) {
        try {
            System.setProperty("derby.system.home", ExceptionDatabase.path);
            
            Connection conn = ExceptionDatabase.conn;
            Statement stmt = ExceptionDatabase.stmt;
            ResultSet rst = ExceptionDatabase.rst;
            String url = ExceptionDatabase.url;
            
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            rst = stmt.executeQuery("SELECT * FROM APP.EXCEPTION_DB");

            while (rst.next()) {
                System.out.print(rst.getInt(1));
                System.out.print(" ");
                System.out.print(rst.getString(2));
//                System.out.print(" ");
//                System.out.println(rst.getString(3));
            }
            
            ExceptionDatabase.closeConnection();
//            ExceptionDatabase.createConnection();
//
//            Statement stmt = ExceptionDatabase.conn.createStatement();
//            stmt.executeUpdate("CREATE TABLE EXCEPTION(ID INT PRIMARY KEY, EXCEPTION VARCHAR(50), DESC VARCHAR(200))");
//            stmt.executeUpdate("INSERT INTO EXCEPTION VALUES(1, 'Audi', 52642)");
//
//            ExceptionDatabase.closeConnection();
        } catch (SQLException ex) {
            ExceptionDatabase.catchSQLEx(ex);
        } finally {
            ExceptionDatabase.finallySQL();
        }
    }
}
