package com.ooopppp.tubes_oop_2.Boundary;

import com.ooopppp.tubes_oop_2.Controller.DialogController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GenericDialog {
    private Stage dialogStage;
    private DialogController controller;
    private String actionType; // "Save" or "Load"

    public GenericDialog(Stage owner, String actionType) {
        this.dialogStage = new Stage();
        this.controller = new DialogController(this);
        this.actionType = actionType;
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(owner);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.setTitle(actionType + " Game");

        VBox dialogVBox = new VBox(0);
        dialogVBox.setPadding(new Insets(10));
        dialogVBox.setStyle("-fx-background-color: #FFEFC8; -fx-font-family: 'Courier'; -fx-border-color: #AA6039; -fx-border-width: 15; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
        setupComponents(dialogVBox);

        Scene dialogScene = new Scene(dialogVBox, 720, 450);
        dialogScene.setFill(null);
        dialogStage.setScene(dialogScene);
    }

    public static void showGenericDialog(Stage owner, String type) {
        GenericDialog dialog = new GenericDialog(owner, type);
        dialog.showDialog();
    }

    private void setupComponents(VBox dialogVBox) {
        Button closeButton = new Button("X");
        closeButton.setOnAction(e -> dialogStage.close());
        closeButton.setStyle("-fx-background-color: #FFEFC8; -fx-text-fill: #FF0000; -fx-font-family: 'Courier'; -fx-font-size: 30;");
        HBox closeLabel = new HBox(closeButton);
        closeLabel.setAlignment(Pos.TOP_RIGHT);

        Label titleLabel = new Label(actionType);
        titleLabel.setStyle("-fx-font-family: 'Courier'; -fx-font-size: 40; -fx-text-fill: #000000; -fx-font-weight: 900;");
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.setPadding(new Insets(0, 0, 25, 0));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(20);

        Label formatLabel = new Label("Format");
        formatLabel.setStyle("-fx-font-size: 25; -fx-font-weight: 900; -fx-font-family: 'Courier';");
        ComboBox<String> formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll("TXT1", "TXT2", "TXT3");
        setupComboBox(formatComboBox);

        Label folderLabel = new Label("Folder");
        folderLabel.setStyle("-fx-font-size: 25; -fx-font-weight: 900; -fx-font-family: 'Courier';");
        TextField folderTextField = new TextField();
        folderTextField.setPromptText("Folder Name");
        setupTextField(folderTextField);

        Label feedbackLabel = new Label("");
        HBox feedbackBox = new HBox(feedbackLabel);
        feedbackBox.setAlignment(Pos.CENTER);

        Button actionButton = new Button(actionType);
        setupActionButton(actionButton, formatComboBox, folderTextField, feedbackLabel);

        grid.add(formatLabel, 0, 0);
        grid.add(formatComboBox, 1, 0);
        grid.add(folderLabel, 0, 1);
        grid.add(folderTextField, 1, 1);

        HBox actionButtonBox = new HBox(actionButton);
        actionButtonBox.setAlignment(Pos.CENTER);
        actionButtonBox.setPadding(new Insets(30, 0, 25, 0));

        dialogVBox.getChildren().addAll(closeLabel, titleBox, grid, actionButtonBox, feedbackBox);
    }

    private void setupComboBox(ComboBox<String> comboBox) {
        comboBox.setMaxSize(430, 45);
        comboBox.setMinSize(430, 45);
        comboBox.setStyle("-fx-font-family: 'Courier'; -fx-background-color: #0DA300; -fx-font-size: 20;");
        comboBox.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setStyle("-fx-pref-width: 390; -fx-prev-height: 60");
                }
            }
        });
    }

    private void setupTextField(TextField textField) {
        textField.setMaxSize(430, 45);
        textField.setMinSize(430, 45);
        textField.setStyle("-fx-font-family: 'Courier'; -fx-background-color: #0DA300; -fx-font-size: 20;");
    }

    private void setupActionButton(Button actionButton, ComboBox<String> formatComboBox, TextField folderTextField, Label feedbackLabel) {
        actionButton.setMaxSize(170, 55);
        actionButton.setMinSize(170, 55);
        actionButton.setStyle("-fx-background-color: #5B311C; -fx-text-fill: #DBE056; -fx-font-family: 'Courier'; -fx-font-size: 30; -fx-font-weight: 900; -fx-background-radius: 15;");
        actionButton.setOnAction(e -> handleAction(formatComboBox, folderTextField, feedbackLabel));
    }

    private void handleAction(ComboBox<String> formatComboBox, TextField folderTextField, Label feedbackLabel) {
        String selectedFormat = formatComboBox.getSelectionModel().getSelectedItem();
        String folderName = folderTextField.getText().trim();
        if (!isValidFilename(folderName)) {
            feedbackLabel.setText("Error: Failed to " + actionType.toLowerCase() + " state");
            feedbackLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Courier'; -fx-font-weight: 900; -fx-text-fill: red;");
        } else {
            if (actionType.equalsIgnoreCase("Save")) {
                controller.saveGame(selectedFormat, folderName);
            } else if (actionType.equalsIgnoreCase("Load")) {
                controller.loadGame(selectedFormat, folderName);
            }
            feedbackLabel.setText(actionType + " State Successfully");
            feedbackLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Courier'; -fx-font-weight: 900; -fx-text-fill: green;");
        }
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

    public void showDialog() {
        dialogStage.showAndWait();
    }
}
