package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.ooopppp.tubes_oop_2.Entity.Plant;

public class TanamanPopup extends Popup {
    private String imagePath;
    private VBox contentContainer;
    private Plant plant;
    private MainView parent;

    public TanamanPopup(Stage stage, Plant plant, MainView parent) {
        super(stage);
        this.imagePath = "/com/ooopppp/tubes_oop_2/img" + plant.getImage();
        this.plant = plant;
        this.parent = parent;
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

        Label nameLabel = new Label(plant.getName());
        nameLabel.getStyleClass().addAll("text-popup-title");
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        nameLabel.setAlignment(Pos.CENTER);

        Image image;
        try {
            if (imagePath != null && !imagePath.isEmpty()) {
                image = new Image(getClass().getResourceAsStream(imagePath), 100, 100, true, true);
            } else {
                throw new IllegalArgumentException("Image path is empty or null");
            }
        } catch (Exception e) {
            image = new Image(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/black.png"));
        }

        ImageView imageView = new ImageView(image);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label ageLabel = new Label("Umur      :");
        ageLabel.getStyleClass().addAll("text-popup");
        GridPane.setConstraints(ageLabel, 0, 0);

        Label ageValueLabel = new Label(plant.getAge() + " (" + plant.getAgeToHarvest() + ")");
        ageValueLabel.getStyleClass().addAll("text-popup");
        GridPane.setConstraints(ageValueLabel, 1, 0);

        Label itemActiveLabel = new Label("Item aktif:");
        itemActiveLabel.getStyleClass().addAll("text-popup");
        GridPane.setConstraints(itemActiveLabel, 0, 1);
        GridPane.setValignment(itemActiveLabel, VPos.TOP);

        VBox activeItemsBox = new VBox();
        activeItemsBox.setAlignment(Pos.TOP_LEFT);

        String activeItem = plant.getFormattedActiveItems();
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

        grid.getChildren().addAll(ageLabel, ageValueLabel, itemActiveLabel, activeItemsBox);

        HBox infoBox = new HBox(20, grid, imageView);
        infoBox.setAlignment(Pos.CENTER);

        Button harvestButton = new Button("Panen");
        harvestButton.getStyleClass().add("button-pop-item");
        harvestButton.setPadding(new Insets(20));
        harvestButton.setOnAction(event -> harvest(plant));

        HBox buttonBox = new HBox(harvestButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(buttonBox, Priority.ALWAYS);
        contentContainer.getChildren().addAll(nameLabel, infoBox);
        if (parent != null && parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan")){
            contentContainer.getChildren().add(buttonBox);
        }
    }

    @Override
    protected String getTitle() {
        return "Info Tanaman";
    }
}
