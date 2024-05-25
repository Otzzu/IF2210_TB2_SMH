package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.GenericDialog;
import com.ooopppp.tubes_oop_2.Boundary.Component.MessageDialog;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.SaveLoadFile;
import com.ooopppp.tubes_oop_2.Main;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DialogController {
    private GenericDialog dialog;
    private Stage stage;
    private MainView parent;

    public DialogController(GenericDialog dialog, Stage stage, MainView parent) {
        this.dialog = dialog;
        this.stage = stage;
        this.parent = parent;
    }

    public void handleChoose(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = new File(".");
        directoryChooser.setInitialDirectory(file);
        directoryChooser.setTitle("Open Resource File");
        File selectedFile = directoryChooser.showDialog(stage);

        if (selectedFile != null) {
            dialog.getChooseFileButton().setText(selectedFile.getAbsolutePath());
            System.out.println(selectedFile.getAbsolutePath());
        }
    }

    public boolean saveGame(String format, String folder) {
        // Implementation to save the game

        System.out.println("save");
        System.out.println("Format " + format);
        System.out.println("folder " + folder);

        SaveLoadFile saveLoadFile =  GameData.getGameData().getPluginManager().getSaveLoadFiles(format);
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
                    throw e;
                }
            } else {
                System.out.println("Directory already exists: " + path);
            }

            saveLoadFile.saveData(folder);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean loadGame(String format, String folder) {
        // Implementation to load the game
        System.out.println("load");
        System.out.println("Format" + format);
        System.out.println("folder " + folder);

        SaveLoadFile saveLoadFile =  GameData.getGameData().getPluginManager().getSaveLoadFiles(format);
        try{

            if (saveLoadFile == null){
                throw new Exception("no config");
            }
            saveLoadFile.loadData(folder);
            int turn = GameData.getGameData().getTurn();

            if (turn % 2 == 0){
                GameData.getGameData().setCurrentPlayer(GameData.getGameData().getPlayers()[1]);
            } else {
                GameData.getGameData().setCurrentPlayer(GameData.getGameData().getPlayers()[0]);
            }

            parent.getStage().getScene().setRoot(new MainView(parent.getStage()));


        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
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
    public void handleAction() {
        String selectedFormat = dialog.getComboBox().getSelectionModel().getSelectedItem();
        String folderName = dialog.getChooseFileButton().getText().trim();
//        if (!isValidFilename(folderName)) {
//            dialog.getFeedbackLabel().setText("Error: Failed to " + dialog.getActionType().toLowerCase() + " state");
//            dialog.getFeedbackLabel().setStyle("-fx-font-size: 20; -fx-font-family: 'Courier'; -fx-font-weight: 900; -fx-text-fill: red;");
//        } else {
        boolean res = false;
        if (dialog.getActionType().equalsIgnoreCase("Save")) {
            res = saveGame(selectedFormat, folderName);
        } else if (dialog.getActionType().equalsIgnoreCase("Load")) {
            res = loadGame(selectedFormat, folderName);
        }
        if (res){
            dialog.getFeedbackLabel().setText(dialog.getActionType() + " State successful");
            dialog.getFeedbackLabel().setStyle("-fx-font-size: 20; -fx-font-family: 'Courier'; -fx-font-weight: 900; -fx-text-fill: green;");
            dialog.getTextField().setText("Folder name");
            MessageDialog.showInfoDialog(stage, dialog.getActionType() + " State successful");
            stage.close();
        } else {
            dialog.getFeedbackLabel().setText(dialog.getActionType() + " State Failed");
            dialog.getFeedbackLabel().setStyle("-fx-font-size: 20; -fx-font-family: 'Courier'; -fx-font-weight: 900; -fx-text-fill: red;");
            dialog.getTextField().setText("Folder name");
            MessageDialog.showErrorDialog(stage, dialog.getActionType() + " State failed");
//            }
        }

    }

}
