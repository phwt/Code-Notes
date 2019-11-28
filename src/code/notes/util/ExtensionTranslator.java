/*
 * Code-Notes | ExtensionTranslator.java
 * Created | 4:12:47 PM Oct 11, 2019
 */
package code.notes.util;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

/**
 *
 * @author phwts
 */
public class ExtensionTranslator {

    /**
     * Get extension from a filename
     * 
     * @param filename
     * @return Extension of provided filename
     */
    public static String getExtension(String filename) {
        int i = filename.lastIndexOf('.');
        return filename.substring(i + 1);
    }

    /**
     * Get syntax style correspond to the filename provided
     * 
     * @param filename
     * @return Syntax Constants
     */
    public static String getConstant(String filename) {
        switch (getExtension(filename).toLowerCase()) {
            case "actionscript":
                return SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT;
//            case "assembler_x86":
//                return SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86;
//            case "bbcode":
//                return SyntaxConstants.SYNTAX_STYLE_BBCODE;
            case "c":
                return SyntaxConstants.SYNTAX_STYLE_C;
            case "clj":
                return SyntaxConstants.SYNTAX_STYLE_CLOJURE;
            case "cpp":
                return SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS;
            case "cs":
                return SyntaxConstants.SYNTAX_STYLE_CSHARP;
            case "css":
                return SyntaxConstants.SYNTAX_STYLE_CSS;
//            case "delphi":
//                return SyntaxConstants.SYNTAX_STYLE_DELPHI;
            case "dtd":
                return SyntaxConstants.SYNTAX_STYLE_DTD;
            case "f": case "f90": case "for":
                return SyntaxConstants.SYNTAX_STYLE_FORTRAN;
            case "groovy":
                return SyntaxConstants.SYNTAX_STYLE_GROOVY;
            case "htaccess":
                return SyntaxConstants.SYNTAX_STYLE_HTACCESS;
            case "html":
                return SyntaxConstants.SYNTAX_STYLE_HTML;
            case "java":
                return SyntaxConstants.SYNTAX_STYLE_JAVA;
            case "js":
                return SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT;
            case "json":
                return SyntaxConstants.SYNTAX_STYLE_JSON;
            case "jsp":
                return SyntaxConstants.SYNTAX_STYLE_JSP;
            case "tex":
                return SyntaxConstants.SYNTAX_STYLE_LATEX;
            case "lisp":
                return SyntaxConstants.SYNTAX_STYLE_LISP;
            case "lua":
                return SyntaxConstants.SYNTAX_STYLE_LUA;
            case "mak": case "mk":
                return SyntaxConstants.SYNTAX_STYLE_MAKEFILE;
            case "mxml":
                return SyntaxConstants.SYNTAX_STYLE_MXML;
            case "nsi":
                return SyntaxConstants.SYNTAX_STYLE_NSIS;
            case "pl":
                return SyntaxConstants.SYNTAX_STYLE_PERL;
            case "php":
                return SyntaxConstants.SYNTAX_STYLE_PHP;
            case "properties":
                return SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE;
            case "py":
                return SyntaxConstants.SYNTAX_STYLE_PYTHON;
            case "rb":
                return SyntaxConstants.SYNTAX_STYLE_RUBY;
            case "sas":
                return SyntaxConstants.SYNTAX_STYLE_SAS;
            case "scala":
                return SyntaxConstants.SYNTAX_STYLE_SCALA;
            case "sql":
                return SyntaxConstants.SYNTAX_STYLE_SQL;
            case "tcl":
                return SyntaxConstants.SYNTAX_STYLE_TCL;
            case "sh":
                return SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL;
            case "vb":
                return SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC;
            case "bat":
                return SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH;
            default:
                return SyntaxConstants.SYNTAX_STYLE_NONE;
        }
    }
}
