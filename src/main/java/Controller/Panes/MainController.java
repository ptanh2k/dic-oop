package Controller.Panes;

import Controller.DictionaryManagement;
import View.GoogleAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    @FXML
    private TextField wordTarget;

    @FXML
    private TextField wordExplain;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ListView<String> suggest = new ListView<String>();

    ObservableList<String> listTarget = FXCollections.observableArrayList();

    /**
     * Handle searching activity.
     */
    @FXML
    public void handleSearch() throws IOException {
        String word_target = wordTarget.getText().trim();
//            if (DictionaryManagement.getIndexByWord(word_target) != -1) {
//                WordExplain.setText(DictionaryManagement.lookup(word_target));
//            } else {
//                WordExplain.setText("Word not found");
//            }
        if (checkBox.isSelected()) {
            wordExplain.setText(GoogleAPI.translate(word_target));
        } else {
            if (DictionaryManagement.trie.searchInTrie(word_target)) {
                wordExplain.setText(DictionaryManagement.trie.lookup(word_target));
            } else {
                wordExplain.setText("WORD NOT FOUND!!!");
            }
        }
    }


    /**
     * Switch to Add word UI.
     */
    @FXML
    public void handleAdd(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/AddUI.fxml"));
            Parent addWordGUI = loader.load();
            Scene scene = new Scene(addWordGUI);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Switch to Edit word UI.
     */
    @FXML
    public void handleEdit(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/EditUI.fxml"));
        Parent editWordGUI = loader.load();
        Scene scene = new Scene(editWordGUI);
        stage.setScene(scene);
        stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Switch to Remove word UI.
     */
    @FXML
    public void handleRemove(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/RemoveUI.fxml"));
            Parent removeWordGUI = loader.load();
            Scene scene = new Scene(removeWordGUI);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Pronounce word.
     */
    @FXML
    public void speak() {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            if (wordTarget.getText() != null) {
                Synthesizer synthesizer
                        = Central.createSynthesizer(
                        new SynthesizerModeDesc(Locale.US));

                // Allocate synthesizer
                synthesizer.allocate();

                // Resume Synthesizer
                synthesizer.resume();

                // Speaks the given text until the queue is empty.
                synthesizer.speakPlainText(wordTarget.getText(), null);
                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Show words suggestion.
     */
    @FXML
    public void toggleSuggestion() {
        try {
            if (suggest.getItems().size() != 0) {
                suggest.getItems().clear();
            }

            String input = wordTarget.getText().trim();
            ArrayList<String> searcher = DictionaryManagement.dictionarySearcher(input);

            if (searcher.size() == 0) {
                wordExplain.setText("WORD NOT FOUND!");
            } else {
                for (String target : searcher) {
                    suggest.getItems().add(target);
                }
            }
            suggest.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            suggest.setOnMouseClicked(mouseEvent -> {
                listTarget = suggest.getSelectionModel().getSelectedItems();
                for (String word : listTarget) {
                    wordTarget.setText(word);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

//    public String displayForExplain(String key) {
//        ArrayList<String> split = new ArrayList<String>();
//        for (int i = 0; i < key.length(); i++) {
//            split.get(0) = key.substring()
//        }
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

