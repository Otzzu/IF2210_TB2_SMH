package com.ooopppp.tubes_oop_2.Entity;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameData {
    private static GameData gameData;
    private Player currentPlayer;
    private Player[] players;
    private int turn;
    private Store store;
    private PluginManager pluginManager;
    private Media media;
    private MediaPlayer mediaPlayer;

    public Media getMedia() {
        return media;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }



    private GameData(){
        players = new Player[2];
        store = new Store();
        turn = 1;
        pluginManager = new PluginManager();

    }

    public static void clear(){
        PluginManager pluginManagerTemp = getGameData().getPluginManager();
        Media media1 = getGameData().getMedia();
        MediaPlayer mediaPlayer1 = getGameData().getMediaPlayer();
        gameData = new GameData();
        gameData.setPluginManager(pluginManagerTemp);
        gameData.setMediaPlayer(mediaPlayer1);
        gameData.setMedia(media1);
    }

    public void setPluginManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    //    public void start(){
//        gameState.start();
//    }



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

    public Store getStore() {
        return store;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
