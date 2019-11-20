/*
 * Code-Notes-FX | FXMLExceptionLookupController.java
 * Created | 9:22:12 PM Nov 19, 2019
 */
package code.notes.fxgui;

import code.notes.util.ExceptionLookup;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author phwts
 */
public class FXMLExceptionLookupController implements Initializable {

    @FXML
    private Button search_btn;
    @FXML
    private TextField search_kw;
    @FXML
    private ComboBox combo_lang;

    @FXML
    private TableView result_table;
    @FXML
    private TableColumn<ExceptionModel, Integer> col_id;
    @FXML
    private TableColumn<ExceptionModel, String> col_key;
    @FXML
    private TableColumn<ExceptionModel, String> col_sol;

    ObservableList<ExceptionModel> row_data = FXCollections.observableArrayList();

    @FXML
    private void handleSearchAction(ActionEvent e) {
        String selected_lang = (String) combo_lang.getValue();
        String user_search = search_kw.getText();
        if (!selected_lang.isEmpty()) {
            row_data = ExceptionLookup.searchException(selected_lang, user_search);
            result_table.setItems(row_data);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_lang.getItems().addAll("Python", "Java");

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_key.setCellValueFactory(new PropertyValueFactory<>("key"));
        col_sol.setCellValueFactory(new PropertyValueFactory<>("solution"));

        result_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        col_id.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        col_key.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        col_sol.setMaxWidth(1f * Integer.MAX_VALUE * 60);
    }

}
