package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Controller.DeckController;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class DeckContainer extends HBox {

    private HBox deck;
    private Label countCardDeck;
    DeckController controller;

    public DeckController getController() {
        return controller;
    }

    public HBox getDeck() {
        return deck;
    }

    public DeckContainer(MainView parent){
        super();
        controller = new DeckController(this, parent);
        deck = new HBox();
        deck.setSpacing(20);
        deck.setAlignment(Pos.CENTER);

        countCardDeck = new Label("Deck\n4/50");
        countCardDeck.setAlignment(Pos.CENTER);
        countCardDeck.setPrefWidth(140);
        countCardDeck.setPrefHeight(78);
        countCardDeck.getStyleClass().add("label-deck");

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        controller.renderDeck();

        this.getChildren().addAll(deck, space, countCardDeck);
        this.setAlignment(Pos.CENTER);

    }
}
