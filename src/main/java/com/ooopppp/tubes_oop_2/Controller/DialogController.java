package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.GenericDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DialogController {
    private GenericDialog dialog;

    public DialogController(GenericDialog dialog) {
        this.dialog = dialog;
    }

    public void saveGame(String format, String folder) {
        // Implementation to save the game
        System.out.println("save");
        System.out.println("Format " + format);
        System.out.println("folder " + folder);
    }

    public void loadGame(String format, String folder) {
        // Implementation to load the game
        System.out.println("load");
        System.out.println("Format" + format);
        System.out.println("folder " + folder);
        // Example: Read from file, query database, etc.
    }

    private boolean isValidFilename(String filename) {
        if (filename.trim().isEmpty()) {
            return false;
        }
        String invalidChars = "\\/:;.'*?\"<>|";
        for (char c : invalidChars.toCharArray()) {
            if (filename.indexOf(c) >= 0) {
                return false;
            }
        }
        return true;
    }
    public void handleAction(ComboBox<String> formatComboBox, TextField folderTextField, Label feedbackLabel) {
        String selectedFormat = formatComboBox.getSelectionModel().getSelectedItem();
        String folderName = folderTextField.getText().trim();
        if (!isValidFilename(folderName)) {
            feedbackLabel.setText("Error: Failed to " + dialog.getActionType().toLowerCase() + " state");
            feedbackLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Courier'; -fx-font-weight: 900; -fx-text-fill: red;");
        } else {
            if (dialog.getActionType().equalsIgnoreCase("Save")) {
                saveGame(selectedFormat, folderName);
            } else if (dialog.getActionType().equalsIgnoreCase("Load")) {
                loadGame(selectedFormat, folderName);
            }
            feedbackLabel.setText(dialog.getActionType() + " State Successfully");
            feedbackLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Courier'; -fx-font-weight: 900; -fx-text-fill: green;");
        }
    }

}
