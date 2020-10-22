package Controller.Panes;

import Controller.DictionaryManagement;
import Model.Dictionary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController {
    Dictionary dict = new Dictionary();

    @FXML
    public Button addBtn;

    @FXML
    private TextField wordToAdd;

    @FXML
    private TextField wordAddedExplain;

    @FXML
    public void addWord() {
        try {
            String word_to_added = wordToAdd.getText().trim();
            String word_added_explain = wordAddedExplain.getText().trim();
            DictionaryManagement.addWord(word_to_added, word_added_explain);
            wordToAdd.clear();
            wordAddedExplain.clear();
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
