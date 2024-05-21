package com.ooopppp.tubes_oop_2.Boundary;

import com.ooopppp.tubes_oop_2.Boundary.Component.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class BuyDialog extends VBox {
    public BuyDialog() {
        this.setStyle("-fx-background-color:  #CB9270; -fx-font-family: 'Courier'; -fx-border-color: #FFFFFF; -fx-border-width: 15; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
//        this.setPadding(new Insets(10));
        // Create a grid to hold the cards
        // Add cards to the grid
        HBox cardContainer = new HBox();
        ItemComponent card = new ItemComponent("Item ", 500, 5);
        cardContainer.getChildren().addAll(card);
        cardContainer.setMaxWidth(280);
        cardContainer.setMaxHeight(200);
        cardContainer.setAlignment(Pos.CENTER);



        Text confirmationText = new Text("Do you want to buy this product ?");
        confirmationText.setStyle("-fx-font-size: 20px; -fx-font-weight: 900; -fx-font-family: 'Courier New';");
        confirmationText.setWrappingWidth(300); // Set wrapping width
        confirmationText.setTextAlignment(TextAlignment.CENTER);
        // Create an OK button
        Button okButton = new Button("Tidak");
        okButton.getStyleClass().add("circular-button-1");
        okButton.setOnAction(e -> ((Stage) getScene().getWindow()).close());

        Button cancelButton = new Button("Iya");
        cancelButton.getStyleClass().add("circular-button-1");
        cancelButton.setOnAction(e -> ((Stage) getScene().getWindow()).close());

        HBox buttonContainer =  new HBox();
        buttonContainer.getChildren().addAll(okButton,cancelButton);
        buttonContainer.setSpacing(30);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPrefWidth(300);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(40);
        this.getChildren().addAll(cardContainer,confirmationText, buttonContainer);
    }

    public void showDialog(Stage ownerStage) {
        // Create a new stage for the dialog
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(ownerStage);

        dialogStage.initStyle(StageStyle.TRANSPARENT);
        // Set up the scene and stage
        Scene scene = new Scene(this, 450, 350);
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