package com.ooopppp.tubes_oop_2;

import com.ooopppp.tubes_oop_2.Boundary.InitView;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        InitView initView = new InitView(primaryStage);
        Scene scene = new Scene(initView, 980, 900);
        URL css = getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("Resource not found.");
        }
        initView.loadMedia();

        primaryStage.setOnCloseRequest(event -> {

            Platform.exit();
            System.exit(0);
        });
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        URL bgSound = Main.class.getResource("/com/ooopppp/tubes_oop_2/sound/opsound.mp3");
        if (bgSound == null) {
            System.out.println("Resource not found.");
            return;
        }
        Media media1 = new Media(bgSound.toExternalForm());
        MediaPlayer mediaPlayerOp = new MediaPlayer(media1);
        mediaPlayerOp.play();
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


}