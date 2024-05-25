package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class WinDialog {
    private Stage dialogStage;
    private String name;
    private Stage ownerStage;
    private static GaussianBlur blurEffect;

    public WinDialog(Stage owner, String name){
        this.dialogStage = new Stage();
        this.name = name;
        this.ownerStage = owner;
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(owner);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.setTitle("Winner");

        VBox dialogVBox = new VBox(0);
        dialogVBox.setPadding(new Insets(10));
        dialogVBox.setStyle("-fx-background-color: #5B311C; -fx-font-family: 'Courier'; -fx-border-color: #AA6039; -fx-border-width: 10; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
        setupComponents(dialogVBox);

        Scene dialogScene = new Scene(dialogVBox, 550, 300);
        dialogScene.getStylesheets().add(getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css").toExternalForm());
        dialogScene.setFill(null);
        dialogStage.setScene(dialogScene);
    }

    private void setupComponents(VBox dialogVBox){
        Label endLabel= new Label ("Permainan Selesai");
        endLabel.setStyle("-fx-text-fill: #00FF00; -fx-font-weight: 800; -fx-font-family: 'Courier'; -fx-font-size: 30; -fx-text-alignment: center");

        ImageView trophyLeftView = new ImageView();
        Image trophyLeft= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/trophy.png")));
        trophyLeftView.setFitHeight(60);
        trophyLeftView.setFitWidth(55);
        trophyLeftView.setImage(trophyLeft);

        ImageView trophyRightView = new ImageView();
        Image trophyRight= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/trophy.png")));
        trophyRightView.setFitHeight(60);
        trophyRightView.setFitWidth(55);
        trophyRightView.setImage(trophyRight);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setMinHeight(70);
        grid.setMaxHeight(70);
        grid.setHgap(20);

        grid.add(trophyLeftView, 0, 0);
        grid.add(endLabel, 1, 0);
        grid.add(trophyRightView, 2, 0);

        Label errorMessage = new Label(this.name != null ? "Pemenangnya " + name : "Pertandingan Seri");
        errorMessage.setStyle("-fx-font-size: 28; -fx-text-alignment: center; -fx-alignment: CENTER; -fx-font-family: 'Courier'; -fx-text-fill: #00FF00; -fx-font-weight: 800;");
        HBox feedbackBox = new HBox(errorMessage);
        feedbackBox.setAlignment(Pos.CENTER);
        feedbackBox.setPadding(new Insets(20, 0, 32, 0));

        Button closeButton = new Button("OK");
        closeButton.setOnAction(event -> {
            dialogStage.close();
            ownerStage.close();
        });
        closeButton.setMaxSize(80, 45);
        closeButton.setMinSize(80, 45);
        closeButton.setStyle("-fx-background-color: #FFEFC8; -fx-text-fill: #E99C1E; -fx-font-family: 'Courier'; -fx-font-size: 25; -fx-font-weight: 800; -fx-background-radius: 13;");
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

    public static void showWinDialog(Stage owner, String name) {
        WinDialog dialog = new WinDialog(owner, name);
        if (blurEffect == null) {
            blurEffect = new GaussianBlur(10);
        }
        owner.getScene().getRoot().setEffect(blurEffect);
        dialog.dialogStage.setOnHidden(event -> {
            owner.getScene().getRoot().setEffect(null);
        });
        dialog.showDialog();
    }

    public void showDialog() {
        dialogStage.showAndWait();
    }
}
