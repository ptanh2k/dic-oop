package Controller.Panes;

import Controller.DictionaryManagement;
import Model.Dictionary;
import Model.Word;
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
import java.util.ArrayList;

public class SearchController {
    File file= new File("./data/dictionaries.txt");

    private Dictionary dictionary = new Dictionary();

    @FXML
    private ArrayList<String> targets = new ArrayList<String>();

    @FXML
    private TextField WordTarget;

    @FXML
    private TextField WordExplain;

    @FXML
    private ListView<String> Suggest = new ListView<String>();

    @FXML
    public void handleSearch(ActionEvent event) throws IOException {
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
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/edit-controller.fxml"));
            Parent editWordGUI = loader.load();
            Scene scene = new Scene(editWordGUI);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleRemove(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/remove-controller.fxml"));
            Parent removeWordGUI = loader.load();
            Scene scene = new Scene(removeWordGUI);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleEnterText() {
        if (Suggest.getItems().size() != 0) {
            Suggest.getItems().clear();
        }

        String target = WordTarget.getText().trim();



    }

    @FXML
    public void handleSelectListView() {

    }

    @FXML
    public void speak(ActionEvent event) {
    }
}
