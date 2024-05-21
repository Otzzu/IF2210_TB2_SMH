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
        this.setPrefWidth(205);
        this.setPadding(new Insets(30));

        info = new VBox();
        info.setAlignment(Pos.CENTER_LEFT);
        priceLabel = new VBox();
        priceLabel.setAlignment(Pos.CENTER_LEFT);
        Text priceTag = new Text("Harga");
        textHarga = new Text("500");
        priceLabel.getChildren().addAll(priceTag,textHarga);
        priceLabel.setSpacing(3);

        quantityLabel = new VBox();
        quantityLabel.setAlignment(Pos.CENTER_LEFT);
        Text quantityTag = new Text("Jumlah");
        textJumlah = new Text("2");
        quantityLabel.getChildren().addAll(quantityTag,textJumlah);
        quantityLabel.setSpacing(3);

        info.getChildren().addAll(priceLabel,quantityLabel);
        info.setSpacing(20);


        CardComponent card = new CardComponent(new CardFactory().createCard("SAPI"), false);

        this.getChildren().addAll(card , info);
        this.setSpacing(25);
        this.getStyleClass().add("card-store");
    }
}
