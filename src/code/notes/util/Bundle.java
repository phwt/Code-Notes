/*
 * Code-Notes | Bundle.java
 * Created | 3:15:32 PM Oct 10, 2019
 */
package code.notes.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author phwts
 */
public class Bundle {

    public static final Locale THAI = new Locale("th", "TH");

    public static Locale toLocale(String string_locale) {
        switch (string_locale) {
            case "en":
                return Locale.ENGLISH;
            case "th":
                return THAI;
            default:
                return null;
        }
    }
    
    public static Locale getLocale() {
        return toLocale(UserPreferences.getLocale());
    }

    @Deprecated
    public static void setLocale(Locale locale) {
        Locale.setDefault(locale);
        ResourceBundle.clearCache();
    }

    /**
     * Get the localized string from provided key
     * 
     * @param key String's key
     * @return Localized string correspond to key provided and user's default locale.
     */
    public static String get(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("code/notes/res/Bundle");
        return bundle.getString(key);
    }

    @Deprecated
    public static void refreshLocale() {
        setLocale(toLocale(UserPreferences.getLocale()));
    }

}
