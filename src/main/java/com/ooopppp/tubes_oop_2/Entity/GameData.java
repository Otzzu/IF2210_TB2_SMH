package com.ooopppp.tubes_oop_2.Entity;

public class GameData {
    private static GameData gameData;
    private Player currentPlayer;
    private Player[] players;
    private int turn;
    private String gameState;

    private GameData(){
        players = new Player[2];
        turn = 1;
        gameState = "Shuffle";
    }

//    public void start(){
//        gameState.start();
//    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public String getGameState() {
        return gameState;
    }

    public static GameData getGameData(){
        if (gameData != null){
            return gameData;
        }
        gameData = new GameData();
        return gameData;
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
