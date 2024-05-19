package com.ooopppp.tubes_oop_2.Entity;

import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import javafx.scene.layout.VBox;

public class GameState {
    private static GameState gameState;
    private Player currentPlayer;
    private Player[] players;

    private GameState(){
        players = new Player[2];
    }

    public static GameState getGameState(){
        if (gameState != null){
            return gameState;
        }
        gameState = new GameState();
        return gameState;
    }

    public void addPlayer(Player player){
        players[players.length - 1] = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
