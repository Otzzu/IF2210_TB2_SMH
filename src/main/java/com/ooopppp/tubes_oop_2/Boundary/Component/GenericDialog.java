package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Controller.DialogController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
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
    private static GaussianBlur blurEffect;

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


        Scene dialogScene = new Scene(dialogVBox, 720, 490);
        dialogScene.getStylesheets().add(getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css").toExternalForm());
        dialogScene.setFill(null);
        dialogStage.setScene(dialogScene);
    }

    public static void showGenericDialog(Stage owner, String type) {
        GenericDialog dialog = new GenericDialog(owner, type);
        if (blurEffect == null) {
            blurEffect = new GaussianBlur(10);
        }
        owner.getScene().getRoot().setEffect(blurEffect);

        dialog.dialogStage.setOnHidden(event -> {
            owner.getScene().getRoot().setEffect(null);
        });
        dialog.showDialog();
    }

    public String getActionType() {
        return actionType;
    }

    private void setupComponents(VBox dialogVBox) {
        Button closeButton = new Button("X");
        closeButton.getStyleClass().add("x-symbol");
        closeButton.setOnAction(event -> dialogStage.close());

        closeButton.setOnMousePressed(event -> {
            closeButton.setTranslateY(1);
        });

        closeButton.setOnMouseReleased(event -> {
            closeButton.setTranslateY(0);
        });

        VBox closecontainer = new VBox(closeButton);
        closecontainer.setAlignment(Pos.TOP_RIGHT);

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

        dialogVBox.getChildren().addAll(closecontainer, titleBox, grid, actionButtonBox, feedbackBox);
    }

    private void setupComboBox(ComboBox<String> comboBox) {
        comboBox.setMaxSize(430, 45);
        comboBox.setMinSize(430, 45);
        comboBox.setStyle("-fx-font-family: 'Courier'; -fx-background-color: #DBE056; -fx-font-size: 20;");
        comboBox.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setStyle("-fx-pref-width: 390; -fx-prev-height: 60; -fx-background-color: #DBE056");
                }
            }
        });
    }

    private void setupTextField(TextField textField) {
        textField.setMaxSize(430, 45);
        textField.setMinSize(430, 45);
        textField.setStyle("-fx-font-family: 'Courier'; -fx-background-color: #DBE056; -fx-font-size: 20;");
    }

    private void setupActionButton(Button actionButton, ComboBox<String> formatComboBox, TextField folderTextField, Label feedbackLabel) {
        actionButton.setMaxSize(170, 65);
        actionButton.setMinSize(170, 65);
        actionButton.getStyleClass().add("button-pop-item");
        actionButton.setStyle("-fx-background-color: #5B311C; -fx-text-fill: #DBE056; -fx-font-family: 'Courier'; -fx-font-size: 30; -fx-font-weight: 900; -fx-background-radius: 15;");

        actionButton.setOnMouseEntered(event -> {
            actionButton.setScaleX(1.05);
            actionButton.setScaleY(1.05);
        });

        actionButton.setOnMouseExited(event -> {
            actionButton.setScaleX(1.0);
            actionButton.setScaleY(1.0);
        });

        actionButton.setOnMousePressed(event -> {
            actionButton.setScaleX(0.9);
            actionButton.setScaleY(0.9);
        });

        actionButton.setOnMouseReleased(event -> {
            actionButton.setScaleX(1.0);
            actionButton.setScaleY(1.0);
        });
        actionButton.setOnAction(e -> controller.handleAction(formatComboBox, folderTextField, feedbackLabel));
    }





    public void showDialog() {
        dialogStage.showAndWait();
    }
}
