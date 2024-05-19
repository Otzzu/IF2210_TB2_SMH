package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class DeckContainer extends HBox {

    private HBox deck;
    private Label countCardDeck;
    private MainView parent;
    public DeckContainer(MainView parent){
        super();
        this.parent = parent;
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

        renderDeck();

        this.getChildren().addAll(deck, space, countCardDeck);
        this.setAlignment(Pos.CENTER);

    }

    public void renderDeck(){
        deck.getChildren().clear();
        //get player active deck, example
        List<Card> activeDeck = GameState.getGameState().getCurrentPlayer().getActiveDeck();

        int emptyCard = 6 - activeDeck.size();
        System.out.println(emptyCard);

        for (Card card : activeDeck) {
            CardComponent cardComponent = new CardComponent(card, false);
            cardComponent.getStyleClass().add("card-hover");
            cardComponent.setOnMouseClicked(event -> {
                VBox previousSelected = parent.getSelectedCardDeck();

                if (previousSelected != event.getSource() ){
                    if (previousSelected != null){
                        previousSelected.setScaleX(1);
                        previousSelected.setScaleY(1);
                        previousSelected.getStyleClass().remove("card-choose");
                        previousSelected.getStyleClass().add("card-hover");
                    }
                    parent.setSelectedCardDeck((CardComponent) event.getSource());
                    parent.getSelectedCardDeck().setScaleY(1.08);
                    parent.getSelectedCardDeck().setScaleX(1.08);
                    parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                    parent.getSelectedCardDeck().getStyleClass().remove("card-hover");
                    parent.notifyCardsInBoard("choose");
                } else {
                    previousSelected.setScaleX(1);
                    previousSelected.setScaleY(1);
                    previousSelected.getStyleClass().remove("card-choose");
                    previousSelected.getStyleClass().add("card-hover");
                    parent.setSelectedCardDeck(null);
                    parent.notifyCardsInBoard("unchoose");

                }
            });
            deck.getChildren().add(cardComponent);
        }

        for (int i = 0; i < emptyCard; i++){
            CardComponent cardComponent = new CardComponent(null, false);
            deck.getChildren().add(cardComponent);
        }
    }

}
