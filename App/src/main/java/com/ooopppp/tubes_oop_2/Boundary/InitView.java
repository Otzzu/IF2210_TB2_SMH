package com.ooopppp.tubes_oop_2.Boundary;

import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Random;

public class InitView extends StackPane {
    private Stage primaryStage;

    public InitView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initialize();
    }

    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/init.png"));
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(900);

        Button startButton = new Button("START");
        startButton.getStyleClass().add("button-start");
        startButton.setOnAction(e -> switchToMainView());

        StackPane.setAlignment(startButton, Pos.BOTTOM_CENTER);
        startButton.setTranslateY(-80);

        this.getChildren().addAll(imageView, startButton);
    }


    private void switchToMainView() {
        MainView root = new MainView(primaryStage);
        Scene scene = new Scene(root, 980, 900);
        URL css = getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("Resource not found.");
        }
        loadMediaAndPlay();
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        List<Card> cards = GameData.getGameData().getCurrentPlayer().getDeck().shuffleCard();
        ShuffleView.showView(cards, root);

        Random random = new Random();
        int randomNumber = random.nextInt(4);
        if (randomNumber == 0) {

            root.getHeader().initializeComponents(true);
            root.getController().highlightAttackAreas();
            AttackPopup.showView(root);
        }
    }

    public void loadMediaAndPlay() {
        new Thread(() -> {
            try {
                URL bgSound = getClass().getResource("/com/ooopppp/tubes_oop_2/sound/Ingame_Music.mp3");
                if (bgSound == null) {
                    System.out.println("Resource not found.");

                    return;
                }

                Media media = new Media(bgSound.toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setVolume(40);

                Platform.runLater(mediaPlayer::play);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
