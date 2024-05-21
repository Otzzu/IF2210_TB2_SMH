package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Entity.CardFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SellCardComponent extends VBox {
    private Text textHarga;
    private Rectangle imagePlaceholder;

    public SellCardComponent(String name, int price) {
        // Create a placeholder for the image
        this.setPrefWidth(140);
        this.setPrefHeight(170);
        this.setPadding(new Insets(10));
        this.getStyleClass().add("card-store");
        CardComponent card = new CardComponent(new CardFactory().createCard("SAPI"), false);

        // Create labels for name and price

        HBox priceLabel = new HBox();
        priceLabel.setAlignment(Pos.CENTER);
        Text priceTag = new Text("Harga");
        priceTag.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#AA6039;  -fx-font-weight: 900;");
        textHarga = new Text("500");
        textHarga.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#000000; -fx-font-weight: 400;");
        priceLabel.getChildren().addAll(priceTag,textHarga);
        priceLabel.setSpacing(3);
        // Set up the layout
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.getChildren().addAll(card, priceLabel);
    }
}