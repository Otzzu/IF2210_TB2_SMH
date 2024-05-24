package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Controller.BuyController;
import com.ooopppp.tubes_oop_2.Entity.Product;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class BuyDialog extends VBox {
    private  Product product;
    private int quantity;
    private Button buyButton;
    private  Button cancelButton;
    private BuyController controller;

    public BuyDialog(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.setStyle("-fx-background-color:  #CB9270; -fx-font-family: 'Courier'; -fx-border-color: #FFFFFF; -fx-border-width: 15; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
//        this.setPadding(new Insets(10));
        // Create a grid to hold the cards
        // Add cards to the grid
        HBox cardContainer = new HBox();
        ItemComponent card = new ItemComponent(this.product, this.quantity);
        cardContainer.getChildren().addAll(card);
        cardContainer.setMaxWidth(280);
        cardContainer.setMaxHeight(200);
        cardContainer.setAlignment(Pos.CENTER);



        Text confirmationText = new Text("Do you want to buy this product ?");
        confirmationText.setStyle("-fx-font-size: 20px; -fx-font-weight: 900; -fx-font-family: 'Courier New';");
        confirmationText.setWrappingWidth(300); // Set wrapping width
        confirmationText.setTextAlignment(TextAlignment.CENTER);
        // Create an OK button
        buyButton = new Button("Beli");
        this.buyButton.getStyleClass().add("circular-button-1");
        cancelButton = new Button("Tidak");
        cancelButton.getStyleClass().add("circular-button-1");
        cancelButton.setOnAction(e -> controller.handleCancelButton());

        HBox buttonContainer =  new HBox();
        buttonContainer.getChildren().addAll(buyButton,cancelButton);
        buttonContainer.setSpacing(30);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPrefWidth(300);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(40);
        this.getChildren().addAll(cardContainer,confirmationText, buttonContainer);
        buyButton.setOnAction(e -> controller.attachEventsBuyButton());
    }

    public void showDialog(Stage ownerStage) {
        // Create a new stage for the dialog
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(ownerStage);

        dialogStage.initStyle(StageStyle.TRANSPARENT);
        // Set up the scene and stage
        Scene scene = new Scene(this, 450, 350);
        scene.setFill(null);

        URL css = getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("Resource not found.");
        }

        dialogStage.setScene(scene);
        dialogStage.setTitle("Sell Dialog");
        dialogStage.showAndWait();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setController(BuyController controller) {
        this.controller = controller;
    }
}