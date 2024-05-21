package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.Header;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.GameState;

public class HeaderController {

    private Header header;
    private MainView parent;

    public HeaderController(Header header, MainView parent) {
        this.header = header;
        this.parent = parent;
    }

    public void attachEventsButtonNext(){
        header.getButtonNext().setOnAction(e -> {
            parent.getBtnSound().stop();
            parent.getBtnSound().play();
            GameState gameState = GameState.getGameState();
            gameState.setCurrentPlayer(gameState.getAnotherPlayer());
            gameState.getCurrentPlayer().getFarm().notifyPlant();
            gameState.getAnotherPlayer().getFarm().notifyPlant();
            gameState.addTurn();
            header.getTurnNumber().setText(String.valueOf(gameState.getTurn()));
            parent.setSelectedCardDeck(null);
            parent.getBoard().getController().populateGrid(false);
            parent.getDeckContainer().getController().renderDeck();
            changePlayerTextColor();
        });
    }

    public void changePlayerTextColor(){
        if (GameState.getGameState().getTurn() % 2 == 0){
            header.getPlayer2Text().getStyleClass().remove("text-white");
            header.getPlayer2Text().getStyleClass().add("text-green");
            header.getPlayer1Text().getStyleClass().remove("text-green");
            header.getPlayer1Text().getStyleClass().add("text-white");
        } else {
            header.getPlayer1Text().getStyleClass().remove("text-white");
            header.getPlayer1Text().getStyleClass().add("text-green");
            header.getPlayer2Text().getStyleClass().remove("text-green");
            header.getPlayer2Text().getStyleClass().add("text-white");

        }
    }

}
