/*
 * Code-Notes | ExceptionLookup.java
 * Created | 6:52:43 PM Oct 12, 2019
 */
package code.notes.util;

import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phwts
 */
public class ExceptionLookup {
    static Set datas;
    public static Set searchException(String lang, String keyword){
        try {
            DatabaseConnectionHandler.createConnection();
            
            datas = DatabaseConnectionHandler.selectData("EXCEPTION_PYTHON");
//            return datas;
//            for(Object data : datas){
//                System.out.print(((String[]) data)[0]);
//                System.out.print(((String[]) data)[1]);
//            }
            
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
                if (DatabaseConnectionHandler.getRst() != null) {
                    DatabaseConnectionHandler.getRst().close();
                }
                if (DatabaseConnectionHandler.getStmt() != null) {
                    DatabaseConnectionHandler.getStmt().close();
                }
                if (DatabaseConnectionHandler.getConn() != null) {
                    DatabaseConnectionHandler.getConn().close();
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(TestSelect.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return datas;
    }
}