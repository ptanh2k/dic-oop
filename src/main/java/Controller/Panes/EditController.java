package Controller.Panes;

import Controller.DictionaryCommandline;
import Controller.DictionaryManagement;
import Model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditController {

    Dictionary dict = new Dictionary();

    @FXML
    private Button editBtn;

    @FXML
    private TextField wordToEdit;

    @FXML
    private TextField wordEdited;

    @FXML
    private TextField explain;

    @FXML
    public void editWord(ActionEvent event) throws IOException {
        String to_edit = wordToEdit.getText().trim();

        if (DictionaryManagement.getIndexByWord(to_edit) != -1) {
            String edit_to = wordEdited.getText().trim();
            String new_explain = explain.getText().trim();
            dict.getDict().get(DictionaryManagement.getIndexByWord(to_edit)).setWord_target(edit_to);
            dict.getDict().get(DictionaryManagement.getIndexByWord(to_edit)).setWord_explain(new_explain);
            DictionaryManagement.dictionaryExportToFile();
        } else {
            explain.setText("Word not found");
        }
    }

    @FXML
    public void back(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/MainUI.fxml"));
            Parent mainGUI = loader.load();
            Scene scene = new Scene(mainGUI);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}