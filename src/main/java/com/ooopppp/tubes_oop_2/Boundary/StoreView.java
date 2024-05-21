
package com.ooopppp.tubes_oop_2.Boundary;


import com.ooopppp.tubes_oop_2.Boundary.Component.*;
import com.ooopppp.tubes_oop_2.Controller.BuyController;
import com.ooopppp.tubes_oop_2.Controller.StoreController;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Product;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ScrollPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StoreView extends VBox {
    private Stage stage;
    private Button buttonJual;
    private ImageView imageView;
    private Map<String, List<Product>> items;
    private StoreController controller;
    private MediaPlayer btnSound;
    private GridPane grid;
    public StoreView(Stage stage) {
        super();
        this.stage = stage;
        this.items = GameData.getGameData().getStore().getItemSells();
        controller = new StoreController(this);
        this.setStyle("-fx-background-color: #AA6039;");

        // Header
//        BorderPane headerContainer = new BorderPane();// Set size of the container
        HBox imageBox = new HBox();
        imageBox.setPrefWidth(150);
        imageView = new ImageView();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/back.png")));
        imageView.setImage(image);
        imageView.getStyleClass().add("image-view");
        imageBox.getChildren().addAll(imageView);
        imageBox.setAlignment(Pos.CENTER_LEFT);
        Label header = new Label("Toko");
        header.setPrefWidth(230);
        header.setPrefHeight(73);
        header.setAlignment(Pos.CENTER);
        header.getStyleClass().add("label-store");
        buttonJual = new Button("Jual");
        buttonJual.getStyleClass().add("button-sell");
        buttonJual.setPrefWidth(150);

        Region leftSpace = new Region();
        Region rightSpace = new Region();
        HBox.setHgrow(leftSpace, Priority.ALWAYS);
        HBox.setHgrow(rightSpace, Priority.ALWAYS);


        HBox headerContainer = new HBox();
        headerContainer.setPadding(new Insets(20));
        headerContainer.getChildren().addAll(imageBox,leftSpace,header,rightSpace,buttonJual);
        headerContainer.setAlignment(Pos.CENTER);



        // Grid for items
        grid = new GridPane();
        grid.setVgap(30);
        grid.setHgap(30); // Increased gap between columns
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);
        populateGrid();


        // ScrollPane to make the GridPane scrollable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(grid);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.getStyleClass().add("scroll-bar");
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true); // Ensures that GridPane widths are within the ScrollPane view

        // Adding components to the VBox
        this.getChildren().addAll(headerContainer, scrollPane);
        this.setSpacing(20);
        controller.loadSound();

        buttonJual.setOnAction(e -> controller.attachEventsJualButton());
        imageView.setOnMouseClicked(e ->controller.attachEventsBackButton());
    }

    public void populateGrid(){
        grid.getChildren().clear();
        int col = 0;
        int row = 0;
        for (Map.Entry<String, List<Product>> entry : items.entrySet()) {
            String productName = entry.getKey();
            List<Product> productList = entry.getValue();
            if (productList.isEmpty()) continue;

            // Assuming all products with the same name have the same price and added weight
            Product sampleProduct = productList.get(0);
            int quantity = productList.size();

            ItemComponent item = new ItemComponent(sampleProduct, quantity);
            grid.add(item, col, row);
            col++;
            if (col == 3) { // 3 columns per row
                col = 0;
                row++;
            }
            item.setOnMouseClicked(e -> {
                this.getBtnSound().stop();
                this.getBtnSound().play();
                BuyDialog buyDialog = new BuyDialog(sampleProduct, quantity);
                buyDialog.setController(new BuyController(buyDialog, this));
                buyDialog.showDialog(this.stage);
            });
        }
    }

    public void refresh() {
        this.items = GameData.getGameData().getStore().getItemSells();
        populateGrid();
    }

    public Button getButtonJual() {
        return buttonJual;
    }

    public Stage getStage() {
        return stage;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public MediaPlayer getBtnSound() {
        return btnSound;
    }

    public void setBtnSound(MediaPlayer btnSound) {
        this.btnSound = btnSound;
    }
}


