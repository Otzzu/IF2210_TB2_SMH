package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Entity.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.ooopppp.tubes_oop_2.Entity.CardFactory;


public class ItemComponent extends HBox {
    private  Product product;
    private int quantity;
    private VBox info;
    private VBox priceLabel;
    private VBox quantityLabel;
    private Text textHarga;
    private Text textJumlah;

    public ItemComponent(Product product, int quantity) {
        super(10); // spacing between elements
        this.setAlignment(Pos.CENTER);
        this.setMaxHeight(160);
        this.setMaxWidth(270);
        this.setPadding(new Insets(10,10,10,10));
        this.product = product;
        this.quantity = quantity;

        info = new VBox();
        info.setAlignment(Pos.CENTER_LEFT);
        priceLabel = new VBox();
        priceLabel.setAlignment(Pos.CENTER_LEFT);
        Text priceTag = new Text("Harga");
        priceTag.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#AA6039;  -fx-font-weight: 900;");
        textHarga = new Text(String.valueOf(product.getPrice()));
        textHarga.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#000000; -fx-font-weight: 400;");
        priceLabel.getChildren().addAll(priceTag,textHarga);


        quantityLabel = new VBox();
        quantityLabel.setAlignment(Pos.CENTER_LEFT);
        Text quantityTag = new Text("Jumlah");
        quantityTag.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#AA6039;  -fx-font-weight: 900;");
        textJumlah = new Text(String.valueOf(quantity));
        textJumlah.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#000000;  -fx-font-weight: 400;");
        quantityLabel.getChildren().addAll(quantityTag,textJumlah);

        info.getChildren().addAll(priceLabel,quantityLabel);
        info.setSpacing(10);


        CardComponent card = new CardComponent(this.product, false);

        this.getChildren().addAll(card , info);
        this.setSpacing(25);
        this.getStyleClass().add("card-store");
    }
}
