package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Pos;

public abstract class Popup extends Application {
    protected VBox root;
    protected Scene scene;
    protected Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.initStyle(StageStyle.TRANSPARENT);

        root = new VBox(20);
        root.getStyleClass().add("popup");
        root.setAlignment(Pos.CENTER);

        Button closeButton = new Button("X");
        closeButton.getStyleClass().add("x-symbol");
        closeButton.setOnAction(event -> primaryStage.close());

        closeButton.setOnMousePressed(event -> {
            closeButton.setTranslateY(1);
        });

        closeButton.setOnMouseReleased(event -> {
            closeButton.setTranslateY(0);
        });

        VBox container = new VBox(closeButton);
        container.setAlignment(Pos.TOP_RIGHT);

        root.getChildren().add(container);

        configure();

        scene = new Scene(root, 600, 330);
        scene.getStylesheets().add(getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css").toExternalForm());
        scene.setFill(null);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected abstract void configure();
    protected abstract String getTitle();
}
