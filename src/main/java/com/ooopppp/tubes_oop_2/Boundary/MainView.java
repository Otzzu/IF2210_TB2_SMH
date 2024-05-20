package com.ooopppp.tubes_oop_2.Boundary;


import com.ooopppp.tubes_oop_2.Boundary.Component.*;
import com.ooopppp.tubes_oop_2.Helper.Observer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainView extends VBox {
    protected Board board;
    private HBox mainContent;
    private Sidebar sidebar;
    protected Header header;
    private DeckContainer deckContainer;
    private CardComponent selectedCardDeck;
    private List<Observer> cardsInBoard;
    private Stage stage;

    public DeckContainer getDeckContainer() {
        return deckContainer;
    }

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

    public MainView(Stage stage, boolean useTimer){
        super();
        this.stage = stage;
        cardsInBoard = new ArrayList<>(20);
        mainContent = new HBox();
        sidebar = new Sidebar();
        header = new Header(useTimer);
        deckContainer = new DeckContainer(this);
        board = new Board(5, 4, this);
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        mainContent.getChildren().addAll(board, space, sidebar);
        this.getChildren().addAll(header, mainContent, deckContainer);
        this.setSpacing(45);
    }
}
