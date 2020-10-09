package Controller.Panes;

import Controller.DictionaryManagement;
import Model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SearchPane {
    @FXML
    public Button SearchButton;

    @FXML
    private TextField WordTarget;

    @FXML
    private TextField WordExplain;

    @FXML
    private Button searchBtn;
    @FXML
    private ListView<String> Suggest;

    @FXML
    public void handleSearch(ActionEvent event) throws IOException {
        DictionaryManagement manager = new DictionaryManagement();

        String word_target = WordTarget.getText().trim();

        manager.insertFromFile();



    }

    @FXML
    public void handleEnterInput(ActionEvent event) {
        if (Suggest.getItems().size() != 0) {
            Suggest.getItems().clear();
        }
    }
}
