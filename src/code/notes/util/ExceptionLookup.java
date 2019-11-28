/*
 * Code-Notes | ExceptionLookup.java
 * Created | 6:52:43 PM Oct 12, 2019
 */
package code.notes.util;

import java.nio.file.Paths;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author phwts
 */
public class ExceptionLookup {

    /**
     * Convert user's entered keyword into SQL statement
     *
     * @param keyword
     * @return SQL statement
     */
    public static String toStatement(String keyword) {
        System.out.println(keyword);
        if (keyword.isEmpty()) {
            return ""; // Select everything
        }
        String[] keywords = keyword.split("[, ?.@+=:]+");

        for (int i = 0; i < keywords.length; i++) {
            keywords[i] = "UPPER(EXCEPTION_KEY) LIKE UPPER('%" + keywords[i] + "%')";
        }

        return " WHERE " + String.join(" OR ", keywords);
    }

    /**
     * Select data from table specified by "lang"
     *
     * @param lang Language to lookup - possible values "python", "java"
     * (case-insensitive)
     * @param keyword User's entered keyword
     * @return ObserveableList in ExceptionModel (id, key, solution)
     */
    public static ObservableList<ExceptionModel> searchException(String lang, String keyword) {
        ObservableList<ExceptionModel> row_data = FXCollections.observableArrayList();
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
            String sql = "SELECT * FROM APP." + table_name + keyword_stmt;
            ResultSet rst = s.executeQuery(sql);

            int count = 0;
            while (rst.next()) {
                row_data.add(new ExceptionModel(
                        count + 1,
                        rst.getString(1),
                        rst.getString(2)
                ));
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
        return row_data;
    }
}
