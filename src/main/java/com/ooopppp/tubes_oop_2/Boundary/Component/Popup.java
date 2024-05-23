package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Popup {
    protected VBox root;
    protected Scene scene;
    protected Stage stage;

    public Popup(Stage stage) {
        this.stage = stage;
        this.stage.initStyle(StageStyle.TRANSPARENT);

        root = new VBox(20);
        root.getStyleClass().add("popup");
        root.setAlignment(Pos.CENTER);

        Button closeButton = new Button("X");
        closeButton.getStyleClass().add("x-symbol");
        closeButton.setOnAction(event -> stage.close());

        closeButton.setOnMousePressed(event -> {
            closeButton.setTranslateY(1);
        });

        closeButton.setOnMouseReleased(event -> {
            closeButton.setTranslateY(0);
        });

        VBox closecontainer = new VBox(closeButton);
        closecontainer.setAlignment(Pos.TOP_RIGHT);

        root.getChildren().add(closecontainer);

        configure();

        scene = new Scene(root, 600, 330);
        scene.getStylesheets().add(getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css").toExternalForm());
        scene.setFill(null);
        this.stage.setScene(scene);
    }

    protected abstract void configure();
    protected abstract String getTitle();

    public void show() {
        stage.setTitle(getTitle());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
