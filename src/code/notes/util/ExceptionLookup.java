/*
 * Code-Notes | ExceptionLookup.java
 * Created | 6:52:43 PM Oct 12, 2019
 */
package code.notes.util;

import java.nio.file.Paths;
import java.sql.*;

/**
 *
 * @author phwts
 */
public class ExceptionLookup {

    public static String toStatement(String keyword) {
        String[] keywords = keyword.split("[, ?.@+=:]+");
        
        for(int i=0; i<keywords.length; i++) {
            keywords[i] = "UPPER(EXCEPTION_KEY) LIKE UPPER('%" + keywords[i] + "%')";
        }

        return String.join(" OR ", keywords);
    }

    public static String[][] searchException(String lang, String keyword) {
        String[][] result = null;
        Connection connect = null;
        Statement s = null;
        try {
            java.nio.file.Path user_path = Paths.get(System.getProperty("user.dir"), ".derby").toAbsolutePath();
            System.setProperty("derby.system.home", user_path.toString());
            connect = DriverManager.getConnection("jdbc:derby:exceptiondb;");
            s = connect.createStatement();

            String table_name;
            switch (lang.toLowerCase()) {
                case "python":
                    table_name = "EXCEPTION_PYTHON";
                    break;
                case "java":
                    table_name = "EXCEPTION_J";
                    break;
                default:
                    return null;
            }
            
            String keyword_stmt = toStatement(keyword);
            String sql = "SELECT * FROM APP." + table_name + " WHERE " + keyword_stmt;
            System.out.println(sql);
            ResultSet rst = s.executeQuery(sql);

            result = new String[10][3];
            int count = 0;
            while (rst.next()) {
                result[count][0] = Integer.toString(count + 1);
                result[count][1] = rst.getString(1);
                result[count][2] = rst.getString(2);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connect != null) {
                s.close();
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
