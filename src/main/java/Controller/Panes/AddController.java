package Controller.Panes;

import Controller.DictionaryManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController {

    public Button addBtn;
    @FXML
    private TextField wordToAdded;

    @FXML
    private TextField wordAddedExplain;

    @FXML
    public void addWord(ActionEvent event) {
        try {
            String word_to_added = wordToAdded.getText().trim();
            String word_added_explain = wordAddedExplain.getText().trim();
            DictionaryManagement.addWord(word_to_added, word_added_explain);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void back(ActionEvent event) {

    }
}
