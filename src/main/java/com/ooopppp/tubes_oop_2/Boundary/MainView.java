package com.ooopppp.tubes_oop_2.Boundary;


import com.ooopppp.tubes_oop_2.Boundary.Component.*;
import com.ooopppp.tubes_oop_2.Helper.Observer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;
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
    private Stage stage;
    private MediaPlayer btnSound;

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

    public MainView(Stage stage){
        super();
        this.stage = stage;
        cardsInBoard = new ArrayList<>(20);
        mainContent = new HBox();
        sidebar = new Sidebar(this);
        header = new Header(this);
        deckContainer = new DeckContainer(this);
        board = new Board(5, 4, this);
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        mainContent.getChildren().addAll(board, space, sidebar);
        this.getChildren().addAll(header, mainContent, deckContainer);
        this.setSpacing(45);

        loadSound();
    }

    public void loadSound(){
        URL btnSoundUrl = getClass().getResource("/com/ooopppp/tubes_oop_2/sound/button-click.mp3");
        if (btnSoundUrl == null) {
            System.out.println("Resource not found.");

            return;
        }

        Media media = new Media(btnSoundUrl.toExternalForm());
        btnSound = new MediaPlayer(media);
        btnSound.setVolume(50);
    }

    public MediaPlayer getBtnSound() {
        return btnSound;
    }

    public Board getBoard() {
        return board;
    }

    public Sidebar getSidebar() {
        return sidebar;
    }

    public Header getHeader() {
        return header;
    }
}
