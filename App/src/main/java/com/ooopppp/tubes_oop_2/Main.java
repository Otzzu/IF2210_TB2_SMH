package com.ooopppp.tubes_oop_2;

import com.ooopppp.tubes_oop_2.Boundary.InitView;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Player;
import javafx.application.Application;
import javafx.scene.Scene;
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
        initView.loadMediaAndPlay();
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
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