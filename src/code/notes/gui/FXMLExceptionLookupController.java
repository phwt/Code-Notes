/*
 * Code-Notes-FX | FXMLExceptionLookupController.java
 * Created | 9:22:12 PM Nov 19, 2019
 */
package code.notes.gui;

import code.notes.util.ExceptionModel;
import code.notes.util.ExceptionLookup;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author phwts
 */
public class FXMLExceptionLookupController implements Initializable {

    private ExceptionModel current_model = null;

    @FXML
    private TextField search_kw;
    @FXML
    private ComboBox combo_lang;
    @FXML
    private TextArea result_area;
    @FXML
    private Button close_btn;
    @FXML
    private Button moreinfo_btn;

    @FXML
    private TableView result_table;
    @FXML
    private TableColumn<ExceptionModel, Integer> col_id;
    @FXML
    private TableColumn<ExceptionModel, String> col_key;
    @FXML
    private TableColumn<ExceptionModel, String> col_sol;

    private ObservableList<ExceptionModel> row_data = FXCollections.observableArrayList();

    @FXML
    private void handleSearchAction(ActionEvent e) {
        if (!combo_lang.getSelectionModel().isEmpty()) {
            String selected_lang = (String) combo_lang.getValue();
            String user_search = search_kw.getText();
            row_data = ExceptionLookup.searchException(selected_lang, user_search);
            result_table.setItems(row_data);
        }
    }

    @FXML
    private void handleMoreInfoAction(ActionEvent e) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                URI infoURI = new URI("https://www.google.com/search?q=" + ((String) combo_lang.getValue()) + "+" + current_model.getKey());
                Desktop.getDesktop().browse(infoURI);
            } catch (URISyntaxException | IOException ex) {
                // (Should) never happens
            }
        }
    }

    @FXML
    private void handleCloseAction(ActionEvent e) {
        Stage stage = (Stage) close_btn.getScene().getWindow();
        stage.close();
    }

    private void showSelectionData(ExceptionModel model) {
        result_area.setText(model.getSolution());
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

        result_table.setRowFactory(tv -> {
            TableRow<ExceptionModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    moreinfo_btn.setDisable(false);
                    ExceptionModel model = row.getItem();
                    current_model = model;
                    showSelectionData(model);
                }
            });
            return row;
        });
    }

}
