package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.ooopppp.tubes_oop_2.Entity.CardFactory;


public class ItemComponent extends HBox {
    private VBox info;
    private VBox priceLabel;
    private VBox quantityLabel;
    private Text textHarga;
    private Text textJumlah;

    public ItemComponent(String name, int price, int quantity) {
        super(10); // spacing between elements
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(141);
        this.setPrefWidth(250);
        this.setPadding(new Insets(10,10,10,10));

        info = new VBox();
        info.setAlignment(Pos.CENTER_LEFT);
        priceLabel = new VBox();
        priceLabel.setAlignment(Pos.CENTER_LEFT);
        Text priceTag = new Text("Harga");
        priceTag.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#AA6039;  -fx-font-weight: 900;");
        textHarga = new Text("500");
        textHarga.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#000000; -fx-font-weight: 400;");
        priceLabel.getChildren().addAll(priceTag,textHarga);


        quantityLabel = new VBox();
        quantityLabel.setAlignment(Pos.CENTER_LEFT);
        Text quantityTag = new Text("Jumlah");
        quantityTag.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#AA6039;  -fx-font-weight: 900;");
        textJumlah = new Text("2");
        textJumlah.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#000000;  -fx-font-weight: 400;");
        quantityLabel.getChildren().addAll(quantityTag,textJumlah);

        info.getChildren().addAll(priceLabel,quantityLabel);
        info.setSpacing(10);


        CardComponent card = new CardComponent(new CardFactory().createCard("SAPI"), false);

        this.getChildren().addAll(card , info);
        this.setSpacing(25);
        this.getStyleClass().add("card-store");
    }
}
