package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import com.ooopppp.tubes_oop_2.Entity.Animal;

public class HewanPopUp extends Popup {
    private String name;
    private int weight;
    private String activeItem;
    private String imagePath;
    private VBox contentContainer;
    private Animal animal;

    public HewanPopUp(Stage stage, String name, int weight, String activeItem, String imagePath, Animal animal) {
        super(stage);
        this.name = name;
        this.weight = weight;
        this.activeItem = activeItem;
        this.imagePath = "/com/ooopppp/tubes_oop_2/img" + imagePath;
        this.animal = animal;
        configure();
    }

    @Override
    protected void configure() {
        if (contentContainer == null) {
            contentContainer = new VBox(10);
            contentContainer.setAlignment(Pos.TOP_CENTER);
            root.getChildren().add(contentContainer);
        } else {
            contentContainer.getChildren().clear();
        }

        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().addAll("text-popup-title");
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        nameLabel.setAlignment(Pos.CENTER);

        Image image = null;
        try {
            image = new Image(getClass().getResourceAsStream(imagePath), 100, 100, true, true);
        } catch (Exception e) {
            image = new Image(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/black.png"), 100, 100, true, true);
        }

        ImageView imageView = new ImageView(image);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label weightLabel = new Label("Berat     :");
        weightLabel.getStyleClass().addAll("text-popup");
        GridPane.setConstraints(weightLabel, 0, 0);

        Label weightValueLabel = new Label(String.valueOf(weight));
        weightValueLabel.getStyleClass().addAll("text-popup");
        GridPane.setConstraints(weightValueLabel, 1, 0);

        Label itemActiveLabel = new Label("Item aktif:");
        itemActiveLabel.getStyleClass().addAll("text-popup");
        GridPane.setConstraints(itemActiveLabel, 0, 1);
        GridPane.setValignment(itemActiveLabel, VPos.TOP);

        VBox activeItemsBox = new VBox();
        activeItemsBox.setAlignment(Pos.TOP_LEFT);

        if (activeItem != null && !activeItem.isEmpty()) {
            String[] items = activeItem.split(", ");
            for (String item : items) {
                Label itemLabel = new Label(item);
                itemLabel.getStyleClass().addAll("text-popup");
                activeItemsBox.getChildren().add(itemLabel);
            }
        } else {
            Label noActiveItemsLabel = new Label("No active items");
            noActiveItemsLabel.getStyleClass().addAll("text-popup");
            activeItemsBox.getChildren().add(noActiveItemsLabel);
        }
        GridPane.setConstraints(activeItemsBox, 1, 1);
        GridPane.setValignment(activeItemsBox, VPos.TOP);

        grid.getChildren().addAll(weightLabel, weightValueLabel, itemActiveLabel, activeItemsBox);

        HBox infoBox = new HBox(20, grid, imageView);
        infoBox.setAlignment(Pos.CENTER);

        Button harvestButton = new Button("Panen");
        harvestButton.getStyleClass().add("button-pop-item");
        harvestButton.setPadding(new Insets(20));
        harvestButton.setOnAction(event -> harvest(animal));

        HBox buttonBox = new HBox(harvestButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(buttonBox, Priority.ALWAYS);

        contentContainer.getChildren().addAll(nameLabel, infoBox, buttonBox);
    }

    @Override
    protected String getTitle() {
        return "Info Hewan";
    }
}