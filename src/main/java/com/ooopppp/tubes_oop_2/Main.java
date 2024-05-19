package com.ooopppp.tubes_oop_2;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.GameState;
import com.ooopppp.tubes_oop_2.Entity.Player;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new MainView();
        Scene scene = new Scene(root, 980, 900);
        URL css = getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("Resource not found.");
        }
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        GameState gameState = GameState.getGameState();
        Player player1 = new Player();
        Player player2 = new Player();
        gameState.addPlayer(player1);
        gameState.addPlayer(player2);
        gameState.setCurrentPlayer(player1);
        launch(args);
    }
}
