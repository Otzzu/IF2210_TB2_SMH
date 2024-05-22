package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.MessageDialog;
import com.ooopppp.tubes_oop_2.Boundary.Component.Header;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.ShuffleView;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameData;

import java.util.List;

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
            GameData gameData = GameData.getGameData();
            gameData.setCurrentPlayer(gameData.getAnotherPlayer());
            gameData.getCurrentPlayer().getFarm().notifyPlant();
            gameData.getAnotherPlayer().getFarm().notifyPlant();
            gameData.addTurn();
            header.getTurnNumber().setText(String.valueOf(gameData.getTurn()));
            parent.setSelectedCardDeck(null);
            parent.getBoard().getController().populateGrid(false);
            parent.getDeckContainer().getController().renderDeck();
            changePlayerTextColor();
            List<Card> cards = gameData.getCurrentPlayer().getDeck().shuffleCard();
            if (cards.size() == 0){
                MessageDialog.showInfoDialog(parent.getStage(), "Active deck full");
            } else {
                ShuffleView.showView(cards, parent);
            }
        });
    }

    public void changePlayerTextColor(){
        if (GameData.getGameData().getTurn() % 2 == 0){
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
