package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import com.ooopppp.tubes_oop_2.Boundary.Component.DeckContainer;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

import java.util.List;

public class DeckController {
    private DeckContainer deckContainer;
    private MainView parent;

    public DeckController(DeckContainer deck, MainView parent) {
        this.deckContainer = deck;
        this.parent = parent;
    }
    public void setupDraggableCard(CardComponent card){
        card.setOnDragDetected(event -> {
            CardComponent cardComponent = (CardComponent) event.getSource();
            Dragboard db = cardComponent.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("VBox");
            db.setContent(content);
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
            }
            event.consume();
        });

        card.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                ((CardComponent) event.getSource()).setVisible(false);
            }
            event.consume();

        });
    }

    public void updateDeckLabel(){
        deckContainer.getCountCardDeck().setText(String.format("Deck\n%d/40", GameData.getGameData().getCurrentPlayer().getDeck().getAllDeck().size()));
    }

    public void renderDeck(){
        deckContainer.getDeck().getChildren().clear();
        //get player active deck, example
        List<Card> activeDeck = GameData.getGameData().getCurrentPlayer().getDeck().getActiveDeck();

        int emptyCard = 6 - activeDeck.size();

        for (Card card : activeDeck) {
            CardComponent cardComponent = new CardComponent(card, false);
            cardComponent.getStyleClass().add("card-hover");

            setupDraggableCard(cardComponent);

            cardComponent.setOnMouseClicked(event -> {
                CardComponent previousSelected = parent.getSelectedCardDeck();

                if (previousSelected != event.getSource() ){
                    parent.notifyCardsInBoard("unchoose");
                    if (previousSelected != null){
                        previousSelected.setScaleX(1);
                        previousSelected.setScaleY(1);
                        previousSelected.getStyleClass().remove("card-choose");
                        if (!previousSelected.isInBoard()) {
                            previousSelected.getStyleClass().add("card-hover");
                        }
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
            deckContainer.getDeck().getChildren().add(cardComponent);
        }

        for (int i = 0; i < emptyCard; i++){
            CardComponent cardComponent = new CardComponent(null, false);
            deckContainer.getDeck().getChildren().add(cardComponent);
        }
    }
}
