/*
 * Code-Notes-FX | ExceptionModel.java
 * Created | 2:12:08 PM Nov 20, 2019
 */
package code.notes.fxgui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author phwts
 */
public class ExceptionModel {
    private SimpleIntegerProperty id;
    private SimpleStringProperty key;
    private SimpleStringProperty solution;

    public ExceptionModel(int id, String key, String solution) {
        this.id = new SimpleIntegerProperty(id);
        this.key = new SimpleStringProperty(key);
        this.solution = new SimpleStringProperty(solution);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getKey() {
        return key.get();
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getSolution() {
        return solution.get();
    }

    public void setValue(String solution) {
        this.solution.set(solution);
    }
    
}
