/*
 * Code-Notes | ExceptionLookup.java
 * Created | 6:52:43 PM Oct 12, 2019
 */
package code.notes.util;

import code.notes.gui.MockGUI;
import code.notes.util.MockDatabase;
import java.nio.file.Paths;
import java.sql.*;

/**
 *
 * @author phwts
 */
public class ExceptionLookup {
    public static void search(String lang, String keyword) {
        String s1 = keyword;
        String[] keywords = s1.split("[, ?.@+=:]+");
        for (String words : keywords) {
            String[][] data = MockDatabase.searchException(lang, words);
            for (String[] w : data) {
                if(w[0] != null){
                    MockGUI.displayText(w[0]);
            }}
        }
    }
    
    public static String[][] searchException(String lang, String keyword) {
        String[][] result = null;
        Connection connect = null;
        Statement s = null;
        try {
            System.setProperty("derby.system.home", Paths.get(System.getProperty("user.dir"), ".derby").toAbsolutePath().toString());
            connect = DriverManager.getConnection("jdbc:derby:exceptiondb;");
            s = connect.createStatement();
            
            String table_name;
            switch(lang.toLowerCase()){
                case "python":
                    table_name = "EXCEPTION_PYTHON";
                    break;
                case "java":
                    table_name = "EXCEPTION_J";
                    break;
                default:
                    return null;
            }
            
            String sql = "SELECT * FROM APP." + table_name + " WHERE UCASE(EXCEPTION_KEY) LIKE UCASE('%" + keyword + "%')";
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
