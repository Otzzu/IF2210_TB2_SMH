package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Entity.CardFactory;
import com.ooopppp.tubes_oop_2.Entity.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SellCardComponent extends VBox {
//    private String itemName;
//    private int price;
    private Product product;
    private Text textHarga;
    private Rectangle imagePlaceholder;
    private boolean isSelected = false;

    public SellCardComponent(Product product) {
        // Create a placeholder for the image
        this.product = product;
        this.setMaxWidth(140);
        this.setMaxHeight(170);
        this.setPadding(new Insets(10));
        this.getStyleClass().add("card-store");
        CardComponent card = new CardComponent(product, false);

        // Create labels for name and price

        HBox priceLabel = new HBox();
        priceLabel.setAlignment(Pos.CENTER);
        Text priceTag = new Text("Harga");
        priceTag.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#AA6039;  -fx-font-weight: 900;");
        textHarga = new Text(String.valueOf(product.getPrice()));
        textHarga.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill:#000000; -fx-font-weight: 400;");
        priceLabel.getChildren().addAll(priceTag,textHarga);
        priceLabel.setSpacing(3);
        // Set up the layout
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.getChildren().addAll(card, priceLabel);

        this.setOnMouseClicked(e -> toggleSelection());
    }

    public Product getProduct() {
        return product;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        updateStyle();
    }

    private void toggleSelection() {
        isSelected = !isSelected;
        updateStyle();
    }

    private void updateStyle() {
        if (isSelected) {
            this.setStyle("-fx-border-color: #E99C1E; -fx-border-width: 3px; -fx-background-color: #DBE056;");
        } else {
            this.setStyle("-fx-border-color: transparent; -fx-background-color: #DBE056;");
        }
    }
}