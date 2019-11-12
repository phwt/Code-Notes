/*
 * Code-Notes | MockDatabase.java
 * Created | 11:17:36 AM Nov 11, 2019
 */
package code.notes.database;

/**
 *
 * @author phwts
 */
public class MockDatabase {
    final static private String[][] mock_data_python = {
        {"ValueError", "........"}, 
        {"NameError", "........"}, 
        {"KeyError", "........"}, 
        {"IndexError", "........"}, 
        {"EOFError", "........"}, 
        {"RecursionError", "........"}
    };
    
    final static private String[][] mock_data_java = {
        {"NullPointerException", "........"}, 
        {"StackOverflowError", "........"}, 
        {"IndexOutOfBoundsException", "........"}, 
        {"IllegalAccessException", "........"}, 
        {"NoSuchFieldException", "........"}, 
        {"ClassNotFoundException", "........"}
    };
    
    final static private String[][] mock_data_js = {
        {"EvalError", "........"}, 
        {"RangeError", "........"}, 
        {"ReferenceError", "........"}, 
        {"SyntaxError", "........"}, 
        {"TypeError", "........"}, 
        {"URIError", "........"}
    };
    
    public static String[][] searchException(String lang, String keyword) {
        String[][] search_data, result_data;
        switch (lang) {
            case "python":
                search_data = mock_data_python;
                break;
            case "java":
                search_data = mock_data_java;
                break;
            case "js":
                search_data = mock_data_js;
                break;
            default:
                return null;
        }
        
        result_data = new String[20][2];
        int cur_index = 0;
        for(String[] data: search_data){
            if(data[0].toLowerCase().equals(keyword.toLowerCase())){
                result_data[cur_index] = data;
                cur_index++;
            }
        }
        
        return result_data;
    }
}
