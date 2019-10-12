/*
 * Code-Notes | ExceptionLookup.java
 * Created | 6:52:43 PM Oct 12, 2019
 */
package code.notes.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author phwts
 */
public class ExceptionLookup {
    public static void main(String[] args) {
        try {
            System.setProperty("derby.system.home", "/home/janbodnar/.derby");
            
            Connection conn = ExceptionDatabase.conn;
            Statement stmt = ExceptionDatabase.stmt;
            ResultText rst = ExceptionDatabase.rst;
            String url = ExceptionDatabase.url;
            
            con = DriverManager.getConnection(url);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM USER12.CARS");

            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(" ");
                System.out.print(rs.getString(2));
                System.out.print(" ");
                System.out.println(rs.getString(3));
            }
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
