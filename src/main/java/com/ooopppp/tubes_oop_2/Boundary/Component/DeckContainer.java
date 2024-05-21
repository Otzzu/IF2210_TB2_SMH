package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Controller.DeckController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

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

        countCardDeck = new Label("Deck\n4/40");
        countCardDeck.setAlignment(Pos.CENTER);
        countCardDeck.setPrefWidth(140);
        countCardDeck.setPrefHeight(78);
        countCardDeck.getStyleClass().add("label-deck");

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        controller.renderDeck();
        controller.updateDeckLabel();

        this.getChildren().addAll(deck, space, countCardDeck);
        this.setAlignment(Pos.CENTER);

    }

    public Label getCountCardDeck() {
        return countCardDeck;
    }
}
