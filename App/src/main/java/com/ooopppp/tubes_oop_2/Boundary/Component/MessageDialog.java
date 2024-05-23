package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;

import java.util.Objects;

public class MessageDialog {
    private Stage dialogStage;
    private String message;

    public MessageDialog(Stage owner, String message, boolean isError) {
        this.dialogStage = new Stage();
        this.message = message;
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(owner);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.setTitle("Error");

        VBox dialogVBox = new VBox(0);
        dialogVBox.setPadding(new Insets(0, 10, 10, 10));
        dialogVBox.setStyle("-fx-background-color: #FFEFC8; -fx-font-family: 'Courier'; -fx-border-color: #5B311C; -fx-border-width: 10; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
        setupComponents(dialogVBox, isError);

        Scene dialogScene = new Scene(dialogVBox, 370, 300);
        dialogScene.getStylesheets().add(getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css").toExternalForm());
        dialogScene.setFill(null);
        dialogStage.setScene(dialogScene);
    }

    private void setupComponents(VBox dialogVBox, boolean isError){
        Label label = new Label (isError ? "Error !!!" : "Info");
        if (isError){
            label.setStyle("-fx-text-fill: #FF0000; -fx-font-weight: 800; -fx-font-family: 'Courier'; -fx-font-size: 30;");
        } else {
            label.setStyle("-fx-text-fill: black; -fx-font-weight: 800; -fx-font-family: 'Courier'; -fx-font-size: 30;");
        }

        ImageView imageView = new ImageView();

        Image image= new Image(Objects.requireNonNull(getClass().getResourceAsStream(isError ? "/com/ooopppp/tubes_oop_2/img/error.png" : "/com/ooopppp/tubes_oop_2/img/info.png")));
        imageView.setFitHeight(35);
        imageView.setFitWidth(35);
        imageView.setImage(image);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setMinHeight(40);
        grid.setMaxHeight(40);
        grid.setHgap(7);

        grid.add(label, 1, 0);
        grid.add(imageView, 0, 0);

        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-size: 20; -fx-text-alignment: center; -fx-alignment: CENTER; -fx-font-family: 'Courier'; -fx-text-fill: #000000; -fx-font-weight: 800;");
        HBox feedbackBox = new HBox(messageLabel);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(200);
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
        MessageDialog dialog = new MessageDialog(owner, message, true);
        dialog.showDialog();
    }

    public static void showInfoDialog(Stage owner, String message) {
        MessageDialog dialog = new MessageDialog(owner, message, false);
        dialog.showDialog();
    }

    public void showDialog() {
        dialogStage.showAndWait();
    }
}
