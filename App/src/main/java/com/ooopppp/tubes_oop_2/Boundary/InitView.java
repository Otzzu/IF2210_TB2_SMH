package com.ooopppp.tubes_oop_2.Boundary;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class InitView extends StackPane {

    private Stage primaryStage;

    public InitView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initialize();
    }

    private void initialize() {
        // Load the image and create an ImageView object
        Image image = new Image("/com/ooopppp/tubes_oop_2/img/init.png");  // Adjust path as necessary
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(768);  // Adjust the image fit height as necessary

        // Create a start button
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> switchToMainView());

        // Add the image and button to this StackPane
        this.getChildren().addAll(imageView, startButton);
        StackPane.setAlignment(startButton, javafx.geometry.Pos.BOTTOM_CENTER);
    }

    private void switchToMainView() {
        // Assuming MainView is also a type of Parent
        MainView mainView = new MainView(primaryStage);
        primaryStage.getScene().setRoot(mainView);
    }
}
