package Controller.Panes;

import Controller.DictionaryManagement;
import Model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.io.IOException;

public class RemoveController {

    @FXML
    private TextField wordToRemove;

    @FXML
    private final Tooltip tooltip = new Tooltip("Word not found");

    /**
     * Remove word in dictionary.
     */
    @FXML
    public void removeWord() throws IOException {
        Dictionary dict = new Dictionary();

        String removed = wordToRemove.getText().trim();
        int index = DictionaryManagement.getIndexByWord(removed);

        if (index != -1) {
            Dictionary.dict.remove(index);
        } else {
            wordToRemove.setTooltip(tooltip);
        }
        DictionaryManagement.dictionaryExportToFile();
    }

    /**
     * Back to main.
     */
    @FXML
    public void back(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/MainUI.fxml"));
            Parent mainGUI = loader.load();
            Scene scene = new Scene(mainGUI);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}
