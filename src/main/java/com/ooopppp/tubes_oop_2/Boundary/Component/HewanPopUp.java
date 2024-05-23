package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HewanPopUp extends Popup {
    private String name;
    private int weight;
    private String activeItem;
    private int countActiveItem;
    private String imagePath;

    public HewanPopUp(Stage stage, String name, int weight, String activeItem, int countActiveItem, String imagePath) {
        super(stage);
        this.name = name;
        this.weight = weight;
        this.activeItem = activeItem;
        this.countActiveItem = countActiveItem;
        this.imagePath = imagePath;
    }

    @Override
    protected void configure() {
        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().addAll("text-popup-title");
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        nameLabel.setAlignment(javafx.geometry.Pos.CENTER);

        Image image;
        try {
            if (imagePath != null && !imagePath.isEmpty()) {
                image = new Image(getClass().getResourceAsStream(imagePath));
            } else {
                throw new IllegalArgumentException("Image path is empty or null");
            }
        } catch (Exception e) {
            // Provide a default image in case of an invalid path
            image = new Image(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/images/susu.png")); // Replace with your default image path
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label weightLabel = new Label("Berat: " + weight + " kg");
        weightLabel.getStyleClass().addAll("text-popup");

        Label itemActiveLabel = new Label("Item aktif: " + activeItem + "(" + countActiveItem + ")");
        itemActiveLabel.getStyleClass().addAll("text-popup");

        VBox weightAndItemBox = new VBox(10, weightLabel, itemActiveLabel);
        weightAndItemBox.setPadding(new Insets(20));

        HBox infoBox = new HBox(20, weightAndItemBox, imageView);
        infoBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button harvestButton = new Button("Panen");
        harvestButton.getStyleClass().add("button-pop-item");
        harvestButton.setPadding(new Insets(20));
        harvestButton.setAlignment(javafx.geometry.Pos.CENTER);

        root.getChildren().addAll(nameLabel, infoBox, harvestButton);
    }

    @Override
    protected String getTitle() {
        return "Info Hewan";
    }
}
