package Controller.Panes;

import Controller.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SearchController {
    File file= new File("./data/dictionaries.txt");

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private TextField WordTarget;

    @FXML
    private TextField WordExplain;

    @FXML
    private ListView<String> Suggest;

    @FXML
    public void handleSearch(ActionEvent event) throws IOException {
        if (Suggest.getItems().size() != 0) {
            Suggest.getItems().clear();
        }
        String word_target = WordTarget.getText().trim();
        DictionaryManagement.insertFromFile();
        WordExplain.setText(DictionaryManagement.lookup(word_target));
    }

    @FXML
    public void handleAdd(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/add-controller.fxml"));
            Parent addWordGUI = loader.load();
            Scene scene = new Scene(addWordGUI);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleEdit(ActionEvent event) {
    }

    @FXML
    public void handleRemove(ActionEvent event) {
    }

    @FXML
    public void speak(ActionEvent event) {
    }
}
