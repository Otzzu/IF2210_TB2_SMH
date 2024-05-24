package com.ooopppp.tubes_oop_2.Boundary.Component;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import com.ooopppp.tubes_oop_2.Entity.LivingBeing;

public abstract class Popup {
    protected VBox root;
    protected Scene scene;
    protected Stage stage;
    private HarvestAction harvestAction;

    protected void playClickSound() {
        URL soundUrl = getClass().getResource("/com/ooopppp/tubes_oop_2/sound/button-click.mp3");
        if (soundUrl != null) {
            Media clickSound = new Media(soundUrl.toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
            mediaPlayer.play();
        } else {
            System.out.println("Sound file not found.");
        }
    }


    public Popup(Stage stage) {
        this.stage = stage;
        this.stage.initStyle(StageStyle.TRANSPARENT);

        root = new VBox();
        root.getStyleClass().add("popup");
        root.setAlignment(Pos.CENTER);

        Button closeButton = new Button("X");
        closeButton.getStyleClass().add("x-symbol");
        closeButton.setOnAction(event -> stage.close());

        closeButton.setOnMousePressed(event -> closeButton.setTranslateY(1));
        closeButton.setOnMouseReleased(event -> closeButton.setTranslateY(0));

        VBox closeContainer = new VBox(closeButton);
        closeContainer.setAlignment(Pos.TOP_RIGHT);

        root.getChildren().add(closeContainer);

        configure();

        scene = new Scene(root, 600, 350);
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

    public void setOnHarvest(HarvestAction action) {
        this.harvestAction = action;
    }

    protected void harvest(LivingBeing livingBeing) {
        System.out.println(livingBeing.getName());
        if (harvestAction != null) {
            harvestAction.execute(livingBeing);
        }
    }

    @FunctionalInterface
    public interface HarvestAction {
        void execute(LivingBeing livingBeing);
    }
}
