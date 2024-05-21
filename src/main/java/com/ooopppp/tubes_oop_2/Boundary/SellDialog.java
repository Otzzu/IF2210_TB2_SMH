package com.ooopppp.tubes_oop_2.Boundary;

import com.ooopppp.tubes_oop_2.Boundary.Component.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class SellDialog extends VBox {
    public SellDialog() {
        this.setStyle("-fx-background-color:  #CB9270; -fx-font-family: 'Courier'; -fx-border-color: #E99C1E; -fx-border-width: 15; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
        this.setPadding(new Insets(10));
        // Create a grid to hold the cards
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        // Add cards to the grid
        for (int i = 0; i < 6; i++) {
            SellCardComponent card = new SellCardComponent("Item " + (i + 1), 500);
            gridPane.add(card, i % 3, i / 3);
        }

        // Create an OK button
        Button okButton = new Button("OK");
        okButton.getStyleClass().add("circular-button");
        okButton.setOnAction(e -> ((Stage) getScene().getWindow()).close());

        // Set up the layout
        HBox buttonBox = new HBox(okButton);
        buttonBox.setAlignment(Pos.CENTER);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(gridPane, buttonBox);
    }

    public void showDialog(Stage ownerStage) {
        // Create a new stage for the dialog
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(ownerStage);

        dialogStage.initStyle(StageStyle.TRANSPARENT);
        // Set up the scene and stage
        Scene scene = new Scene(this, 620, 550);
        scene.setFill(null);

        URL css = getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("Resource not found.");
        }

        dialogStage.setScene(scene);
        dialogStage.setTitle("Sell Dialog");
        dialogStage.showAndWait();
    }
}