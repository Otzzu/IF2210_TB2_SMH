package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.GenericDialog;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.SaveLoadFile;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        SaveLoadFile saveLoadFile =  GameData.getGameData().getPluginManager().getSaveLoadFiles("com.ooopppp.SaveLoadJSON");
        try{
            if (saveLoadFile == null){
                throw new Exception("no config");
            }
            Path path = Paths.get(folder);


            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    System.out.println("Directory created successfully: " + path);
                } catch (IOException e) {
                    System.out.println("Failed to create directory: " + e.getMessage());
                }
            } else {
                System.out.println("Directory already exists: " + path);
            }

            saveLoadFile.saveData("halo");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("JiJiJi");
        }
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
