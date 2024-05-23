package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TanamanPopup extends Popup {
    private String name;
    private int age;
    private String activeItem;
    private int countActiveItem;
    private String imagePath;

    public TanamanPopup(Stage stage, String name, int age, String activeItem, int countActiveItem, String imagePath) {
        super(stage);
        this.name = name;
        this.age = age;
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
        if (imagePath != null && !imagePath.isEmpty()) {
            image = new Image(imagePath);
        } else {
            // Provide a default image or handle the missing image scenario
            image = new Image("/path/to/default/image.png"); // Replace with your default image path
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label ageLabel = new Label("Umur: " + age + " hari");
        ageLabel.getStyleClass().addAll("text-popup");

        Label itemActiveLabel = new Label("Item aktif: " + activeItem + "(" + countActiveItem + ")");
        itemActiveLabel.getStyleClass().addAll("text-popup");

        VBox ageAndItemBox = new VBox(10, ageLabel, itemActiveLabel);
        ageAndItemBox.setPadding(new Insets(20));

        HBox infoBox = new HBox(20, ageAndItemBox, imageView);
        infoBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button harvestButton = new Button("Panen");
        harvestButton.getStyleClass().add("button-pop-item");
        harvestButton.setPadding(new Insets(20));
        harvestButton.setAlignment(javafx.geometry.Pos.CENTER);

        root.getChildren().addAll(nameLabel, infoBox, harvestButton);
    }

    @Override
    protected String getTitle() {
        return "Info Tanaman";
    }
}
