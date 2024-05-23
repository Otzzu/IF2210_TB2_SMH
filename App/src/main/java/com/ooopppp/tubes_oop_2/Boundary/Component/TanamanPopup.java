package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class TanamanPopup extends Popup {
    private String name;
    private int age;
    private String activeItem;
    private String imagePath;

    public TanamanPopup(String name, int age, String activeItem, String imagePath) {
        this.name = name;
        this.age = age;
        this.activeItem = activeItem;
        this.imagePath = imagePath;
    }

    @Override
    protected void configure() {
        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().addAll("text-popup-title");
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        nameLabel.setAlignment(javafx.geometry.Pos.CENTER);

        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label weightLabel = new Label("Umur: " + age + " kg");
        weightLabel.getStyleClass().addAll( "text-popup");

        Label itemActiveLabel = new Label("Item aktif: " + activeItem);
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
        return "Info Tanaman";
    }
}
