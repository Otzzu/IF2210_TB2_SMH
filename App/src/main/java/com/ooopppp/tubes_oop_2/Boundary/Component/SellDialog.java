package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Controller.SellController;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;

public class SellDialog extends VBox {
    private List<Product> productActiveDeck;
    private Product selectedProduct = null;
    private SellController controller;
    public SellDialog() {
        productActiveDeck = GameData.getGameData().getCurrentPlayer().getDeck().getProductsFromActiveDeck();
        this.setStyle("-fx-background-color:  #FFEFC8; -fx-font-family: 'Courier'; -fx-border-color: #E99C1E; -fx-border-width: 15; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
        this.setPadding(new Insets(10));
        // Create a grid to hold the cards
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        // Add cards to the grid
        int col = 0;
        int row = 0;
        for (Product product : productActiveDeck) {
            SellCardComponent card = new SellCardComponent(product);
            card.setOnMouseClicked(e -> {
                handleCardSelection(card);
            });
            gridPane.add(card, col, row);
            col++;
            if (col == 3) { // 3 columns per row
                col = 0;
                row++;
            }
        }

        // Create an OK button
        Button okButton = new Button("OK");
        okButton.getStyleClass().add("circular-button");
        // Set up the layout
        HBox buttonBox = new HBox(okButton);
        buttonBox.setAlignment(Pos.CENTER);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(40);
        this.getChildren().addAll(gridPane, buttonBox);

        okButton.setOnAction(e -> controller.attachEventsOkButton());
    }

    public void setController(SellController controller) {
        this.controller = controller;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    private void handleCardSelection(SellCardComponent selectedCard) {
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (this.getChildren().get(i) instanceof SellCardComponent) {
                SellCardComponent card = (SellCardComponent) this.getChildren().get(i);
                card.setSelected(false);
            }
        }
        selectedCard.setSelected(true);
        selectedProduct = selectedCard.getProduct();
    }

    public void showDialog(Stage ownerStage) {
        // Create a new stage for the dialog
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(ownerStage);

        dialogStage.initStyle(StageStyle.TRANSPARENT);
        // Set up the scene and stage
        Scene scene = new Scene(this, 620, 550);
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
}