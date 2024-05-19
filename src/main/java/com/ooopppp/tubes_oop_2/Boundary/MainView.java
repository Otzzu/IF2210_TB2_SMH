package com.ooopppp.tubes_oop_2.Boundary;


import com.ooopppp.tubes_oop_2.Boundary.Component.*;
import com.ooopppp.tubes_oop_2.Helper.Observer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MainView extends VBox {
    private Board board;
    private HBox mainContent;
    private Sidebar sidebar;
    private Header header;
    private DeckContainer deckContainer;
    private CardComponent selectedCardDeck;
    private List<Observer> cardsInBoard;

    public void addObserver(Observer observer){
        cardsInBoard.add(observer);
    }

    public void clearObserver(){
        cardsInBoard.clear();
    }

    public void notifyCardsInBoard(String data){
        for (Observer obs: cardsInBoard){
            obs.update(data);
        }
    }

    public CardComponent getSelectedCardDeck() {
        return selectedCardDeck;
    }

    public void setSelectedCardDeck(CardComponent selectedCardDeck) {
        this.selectedCardDeck = selectedCardDeck;
    }

    public MainView(){
        super();
        cardsInBoard = new ArrayList<>(20);
        mainContent = new HBox();
        sidebar = new Sidebar();
        header = new Header();
        deckContainer = new DeckContainer(this);
        board = new Board(5, 4, this, deckContainer);
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        mainContent.getChildren().addAll(board, space, sidebar);
        this.getChildren().addAll(header, mainContent, deckContainer);
        this.setSpacing(45);
    }
}
