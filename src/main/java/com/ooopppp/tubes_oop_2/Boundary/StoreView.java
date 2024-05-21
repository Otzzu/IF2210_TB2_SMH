
package com.ooopppp.tubes_oop_2.Boundary;


import com.ooopppp.tubes_oop_2.Boundary.Component.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ScrollPane;

public class StoreView extends VBox {
    public StoreView() {
        super();
        this.setStyle("-fx-background-color: #AA6039;");

        // Header
        BorderPane headerContainer = new BorderPane();// Set size of the container
        Label header = new Label("Toko");
        header.setPrefWidth(230);
        header.setPrefHeight(73);
        header.setAlignment(Pos.CENTER);
        header.getStyleClass().add("label-store");
        headerContainer.setCenter(header); // Center the label within the container

        // Grid for items
        GridPane grid = new GridPane();
        grid.setVgap(60);
        grid.setHgap(60); // Increased gap between columns
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);
        int numCols = 3;
        int numRows = 5;

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                ItemComponent item = new ItemComponent("Item " + (i * numRows + j + 1), 500, 5);
                grid.add(item, i, j);
            }
        }


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
    }
}
