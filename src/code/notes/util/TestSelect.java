/*
 * Code-Notes | TestSelect.java
 * Created | 9:54:25 PM Oct 13, 2019
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

public class TestSelect {

    public static void main(String[] args) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:derby:exceptiondb";

        try {
            
            System.out.println(Paths.get(System.getProperty("user.dir"), ".derby").toAbsolutePath().toString());
            
            System.setProperty("derby.system.home", Paths.get(System.getProperty("user.dir"), ".derby").toAbsolutePath().toString());

            con = DriverManager.getConnection(url);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM APP.EXCEPTION_DB");

            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(" ");
                System.out.print(rs.getString(2));
//                System.out.print(" ");
//                System.out.println(rs.getString(3));
            }

            DriverManager.getConnection("jdbc:derby:;shutdown=true");

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
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(TestSelect.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}
