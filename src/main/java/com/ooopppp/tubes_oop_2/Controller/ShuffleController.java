package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.ShuffleView;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameData;

import java.util.List;

public class ShuffleController {

    private ShuffleView shuffleView;

    public ShuffleController(ShuffleView shuffleView) {
        this.shuffleView = shuffleView;
    }

    public void handleSwitchCards() {
        // Logic to switch cards goes here
        // For example, shuffle the cards within the list or rotate positions
        List<Card> cards = GameData.getGameData().getCurrentPlayer().getDeck().shuffleCard();
        shuffleView.setCards(cards);
        shuffleView.buildLayout();
    }
}
