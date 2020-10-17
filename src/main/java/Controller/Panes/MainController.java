package Controller.Panes;

import Controller.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    @FXML
    private TextField WordTarget;

    @FXML
    private TextField WordExplain;

    @FXML
    private ListView<String> Suggest = new ListView<String>();

    ObservableList<String> listTarget = FXCollections.observableArrayList();

    @FXML
    public void handleSearch() {
        try {
            String word_target = WordTarget.getText().trim();
            DictionaryManagement.insertFromFile();
            if (DictionaryManagement.getIndexByWord(word_target) != -1) {
                WordExplain.setText(DictionaryManagement.lookup(word_target));
            } else {
                WordExplain.setText("Word not found");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleAdd(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    public void speak(ActionEvent event) {

    }

    @FXML
    public void toggleSuggestion() {
        try {
            if (Suggest.getItems().size() != 0) {
                Suggest.getItems().clear();
            }

            String input = WordTarget.getText().trim();
            ArrayList<String> searcher = DictionaryManagement.dictionarySearcher(input);

            if (searcher.size() == 0) {
                WordExplain.setText("WORD NOT FOUND!");
            } else {
                for (String target : searcher) {
                    Suggest.getItems().add(target);
                }
            }
            Suggest.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            Suggest.setOnMouseClicked(mouseEvent -> {
                listTarget = Suggest.getSelectionModel().getSelectedItems();
                for (String word : listTarget) {
                    WordTarget.setText(word);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

//    public String displayForExplain(String key) {
//        ArrayList<String> split = new ArrayList<String>();
//        for (int i = 0; i < key.length(); i++) {
//            split.get(0) = key.substring()
//        }
//    }
}



//    @FXML
//    public void toggleSuggestion() throws IOException {
//        entries.clear();
//        WordTarget.textProperty().addListener(
//                (observable, oldVal, newVal) -> handleSearchByKey(oldVal, newVal));
//        Suggest.setMaxHeight(372);
//        for (Word word : dictionary.getDict()) {
//            entries.add(word.getWord_target());
//        }
//        Suggest.setItems(entries);
//    }

//
//    @FXML
//    public void handleSearchByKey(String oldVal, String newVal) {
//        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
//            Suggest.setItems(entries);
//        }
//        newVal = newVal.toUpperCase();
//        ObservableList<String> subentries = FXCollections.observableArrayList();
//        for ( Object entry : Suggest.getItems() ) {
//            String entryText = (String)entry;
//            if ( entryText.toUpperCase().contains(newVal) ) {
//                subentries.add(entryText);
//            }
//        }
//        Suggest.setItems(subentries);
//    }

