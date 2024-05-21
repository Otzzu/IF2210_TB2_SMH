package com.ooopppp.tubes_oop_2;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.GenericDialog;
// import com.ooopppp.tubes_oop_2.Boundary.SeranganBeruangView;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.GameState;
import com.ooopppp.tubes_oop_2.Entity.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = new SeranganBeruangView(primaryStage);
        GameData.getGameData().start();
        Parent root = new MainView(primaryStage);
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

        GenericDialog.showGenericDialog(primaryStage, "Load"); //ntr type diganti sm passing pas klik button
    }

    public static void main(String[] args) {
        GameData gameData = GameData.getGameData();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        gameData.addPlayer(player1);
        gameData.addPlayer(player2);
        gameData.setCurrentPlayer(player1);


        launch(args);
    }

    private void loadMediaAndPlay() {
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
