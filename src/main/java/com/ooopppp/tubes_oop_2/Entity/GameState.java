package com.ooopppp.tubes_oop_2.Entity;

import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import javafx.scene.layout.VBox;

public class GameState {
    private static GameState gameState;
    private Player currentPlayer;
    private Player[] players;
    private int turn;

    private GameState(){
        players = new Player[2];
        turn = 1;
    }

    public static GameState getGameState(){
        if (gameState != null){
            return gameState;
        }
        gameState = new GameState();
        return gameState;
    }

    public void addPlayer(Player player){
        if (players[0] == null){
            players[0] = player;
        } else {
            players[1] = player;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getAnotherPlayer() {
        if (players[0].equals(currentPlayer)){
            return players[1];
        } else {
            return players[0];
        }
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addTurn(){
        turn++;
    }

    public int getTurn() {
        return turn;
    }
}
