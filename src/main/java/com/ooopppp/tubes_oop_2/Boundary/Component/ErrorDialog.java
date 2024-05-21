package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ErrorDialog {
    private Stage dialogStage;
    private static ErrorDialog instance;

    private ErrorDialog() {
        dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Error");
        dialogStage.setMinWidth(250);
        dialogStage.setMinHeight(120);

        Label label = new Label();
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dialogStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        dialogStage.setScene(scene);
    }

    public static ErrorDialog getInstance() {
        if (instance == null) {
            instance = new ErrorDialog();
        }
        return instance;
    }

    public void showError(String message) {
        ((Label)((VBox)dialogStage.getScene().getRoot()).getChildren().get(0)).setText(message);
        dialogStage.showAndWait();
    }
}
