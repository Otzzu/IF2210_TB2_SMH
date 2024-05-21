package com.ooopppp.tubes_oop_2.Boundary;

import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import com.ooopppp.tubes_oop_2.Controller.ShuffleController;
import com.ooopppp.tubes_oop_2.Entity.Card;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;


public class ShuffleView extends Stage {
    private List<CardComponent> cardComponents;
    private Button okButton;
    private Button switchButton;
    private ShuffleController controller;
    private HBox bottomRow;
    private HBox topRow;
    private Scene scene;
    private VBox layout;

    private ShuffleView(List<Card> cards) {
        layout = new VBox(20);
        scene = new Scene(layout, 400, 600); // Adjusted size
        scene.setFill(Color.TRANSPARENT);
        URL css = getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("CSS Resource not found.");
        }
        this.cardComponents = new ArrayList<>();
        for (Card c: cards){
            this.cardComponents.add(new CardComponent(c, false));
        }
        controller = new ShuffleController(this);
        topRow = new HBox(10);
        topRow.setAlignment(Pos.CENTER);

        bottomRow = new HBox(10);
        bottomRow.setAlignment(Pos.CENTER);
        initModality(Modality.APPLICATION_MODAL); // Ensure it blocks input to other windows
        initStyle(StageStyle.UNDECORATED); // Remove window decorations
        initializeComponents();
        buildLayout();

        this.setScene(scene);
        this.initStyle(StageStyle.TRANSPARENT);
    }

    public void setCards(List<Card> cards) {
        this.cardComponents.clear();
        for (Card c: cards){
            this.cardComponents.add(new CardComponent(c, false));
        }
    }

    private void applyStylesToCards() {
        for (CardComponent cardComponent : cardComponents) {
            cardComponent.getStyleClass().add("card-bigger");
            cardComponent.setImageView(65,65);
        }
    }
    private void initializeComponents() {
        // Switch Button
        Image image = new Image(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/switch.png"));
        // Create an ImageView with the image
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(24); // Set the width of the image (adjust as necessary)
        imageView.setFitHeight(24);
        switchButton = new Button();
        switchButton.setGraphic(imageView);
        switchButton.setOnAction(e -> controller.handleSwitchCards()); // Implement this method based on what 'Switch' should do

        // Ok Button
        okButton = new Button("OK");
        okButton.setOnAction(e -> this.close()); // Or any other action

        // Add style classes for buttons
        okButton.getStyleClass().add("circular-button-dark-brown");
        switchButton.getStyleClass().add("circular-button-dark-brown");
    }


    public void buildLayout() {
        topRow.getChildren().clear();
        bottomRow.getChildren().clear();
        layout.getChildren().clear();
        applyStylesToCards();

        if (!cardComponents.isEmpty()) {
            topRow.getChildren().add(cardComponents.get(0)); // Always add the first card to the top row
            if (cardComponents.size() >= 2) {
                topRow.getChildren().add(cardComponents.get(1)); // Add the second card to the top row if it exists
            }
        }

        // Add remaining cards to the bottom row if there are more than two
        for (int i = 2; i < cardComponents.size(); i++) {
            bottomRow.getChildren().add(cardComponents.get(i));
        }
        // VBox to hold both HBoxes
        VBox cardLayout = new VBox(20); // Adjust this value to manage the vertical spacing between the two rows
        cardLayout.getChildren().addAll(topRow, bottomRow);
        cardLayout.setAlignment(Pos.CENTER);

        // Buttons HBox
        HBox buttonsBox = new HBox(10, switchButton, okButton);
        buttonsBox.setAlignment(Pos.CENTER);

        // Main layout VBox
        layout.getChildren().addAll(cardLayout, buttonsBox);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-color: #FFEFC8; -fx-border-width: 5px; -fx-background-color: #E99C1E");

        // Scene and other setup remains similar
    }

    public static void showView(List<Card> cards){
        ShuffleView shuffleView = new ShuffleView(cards);
        shuffleView.showAndWait();
    }


}
