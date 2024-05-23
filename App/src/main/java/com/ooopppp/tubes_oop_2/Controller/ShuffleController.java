package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.ShuffleView;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameData;

import java.util.List;

public class ShuffleController {

    private ShuffleView shuffleView;
    private MainView parent;

    public ShuffleController(ShuffleView shuffleView, MainView parent) {
        this.shuffleView = shuffleView;
        this.parent = parent;
    }

    public void handleSwitchCards() {
        parent.getBtnSound().stop();
        parent.getBtnSound().play();
        List<Card> cards = GameData.getGameData().getCurrentPlayer().getDeck().shuffleCard();
        shuffleView.setCards(cards);
        shuffleView.buildLayout();
    }

    public void handleOk(){
        parent.getBtnSound().stop();
        parent.getBtnSound().play();

        GameData.getGameData().getCurrentPlayer().getDeck().moveToActiveDeck(shuffleView.getDataCards());

        parent.getBoard().getController().populateGrid(false);
        parent.getDeckContainer().getController().renderDeck();
        parent.getDeckContainer().getController().updateDeckLabel();
        shuffleView.close();

    }
}
