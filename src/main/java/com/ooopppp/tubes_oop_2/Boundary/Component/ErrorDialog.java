package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ErrorDialog {
    private Stage dialogStage;
    private String message;

    public ErrorDialog(Stage owner, String message) {
        this.dialogStage = new Stage();
        this.message = message;
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(owner);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.setTitle("Error");

        VBox dialogVBox = new VBox(0);
        dialogVBox.setPadding(new Insets(0, 10, 10, 10));
        dialogVBox.setStyle("-fx-background-color: #FFEFC8; -fx-font-family: 'Courier'; -fx-border-color: #5B311C; -fx-border-width: 10; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
        setupComponents(dialogVBox);

        Scene dialogScene = new Scene(dialogVBox, 370, 300);
        dialogScene.getStylesheets().add(getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css").toExternalForm());
        dialogScene.setFill(null);
        dialogStage.setScene(dialogScene);
    }

    private void setupComponents(VBox dialogVBox){
        Label errorLabel = new Label ("Error !!!");
        errorLabel.setStyle("-fx-text-fill: #FF0000; -fx-font-weight: 800; -fx-font-family: 'Courier'; -fx-font-size: 30;");

        ImageView errorImageView = new ImageView();
        Image errorImage= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/error.png")));
        errorImageView.setFitHeight(35);
        errorImageView.setFitWidth(35);
        errorImageView.setImage(errorImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setMinHeight(40);
        grid.setMaxHeight(40);
        grid.setHgap(7);

        grid.add(errorLabel, 1, 0);
        grid.add(errorImageView, 0, 0);

        Label errorMessage = new Label(message);
        errorMessage.setStyle("-fx-font-size: 25; -fx-text-alignment: center; -fx-alignment: CENTER; -fx-font-family: 'Courier'; -fx-text-fill: #000000; -fx-font-weight: 800;");
        HBox feedbackBox = new HBox(errorMessage);
        errorMessage.setWrapText(true);
        errorMessage.setMaxWidth(330);
        feedbackBox.setAlignment(Pos.CENTER);
        feedbackBox.setMinHeight(110);
        feedbackBox.setMaxHeight(110);
        feedbackBox.setPadding(new Insets(10, 0, 40, 0));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> dialogStage.close());
        closeButton.setMaxSize(100, 45);
        closeButton.setMinSize(100, 45);
        closeButton.setStyle("-fx-background-color: #5B311C; -fx-text-fill: #DBE056; -fx-font-family: 'Courier'; -fx-font-size: 20; -fx-font-weight: 900; -fx-background-radius: 15;");
        closeButton.setOnMousePressed(event -> {
            closeButton.setTranslateY(1);
        });

        closeButton.setOnMouseReleased(event -> {
            closeButton.setTranslateY(0);
        });

        VBox closeContainer = new VBox(closeButton);
        closeContainer.setAlignment(Pos.BOTTOM_CENTER);

        dialogVBox.getChildren().addAll(grid, feedbackBox, closeContainer);
    }

    public static void showErrorDialog(Stage owner, String message) {
        ErrorDialog dialog = new ErrorDialog(owner, message);
        dialog.showDialog();
    }

    public void showDialog() {
        dialogStage.showAndWait();
    }
}
