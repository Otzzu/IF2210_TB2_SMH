package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import com.ooopppp.tubes_oop_2.Boundary.Component.DeckContainer;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.*;
import javafx.scene.image.Image;
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
            CardComponent previousSelected = parent.getSelectedCardDeck();
            if (parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan")){
                if (cardComponent.getCardData() instanceof LivingBeing){
                    if (previousSelected != event.getSource() ){
                        parent.notifyCardsInBoard("unchoose-all");
                        if (previousSelected != null){
                            previousSelected.setScaleX(1);
                            previousSelected.setScaleY(1);
                            previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                            previousSelected.getStyleClass().add("card-hover");
                        }
                        parent.setSelectedCardDeck((CardComponent) event.getSource());
                        parent.getSelectedCardDeck().setScaleY(1.08);
                        parent.getSelectedCardDeck().setScaleX(1.08);
                        parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                        parent.getSelectedCardDeck().getStyleClass().removeAll("card-hover", "board-fill");
                        parent.notifyCardsInBoard("choose");
                    }
                } else if (cardComponent.getCardData() instanceof Product || cardComponent.getCardData() instanceof Item){
                    if (previousSelected != event.getSource() ){
                        parent.notifyCardsInBoard("unchoose-all");
                        if (previousSelected != null){
                            previousSelected.setScaleX(1);
                            previousSelected.setScaleY(1);
                            previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                            previousSelected.getStyleClass().add("card-hover");
                        }
                        parent.setSelectedCardDeck((CardComponent) event.getSource());
                        parent.getSelectedCardDeck().setScaleY(1.08);
                        parent.getSelectedCardDeck().setScaleX(1.08);
                        parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                        parent.getSelectedCardDeck().getStyleClass().removeAll("card-hover", "board-fill");
                        parent.notifyCardsInBoard("choose2");

                    }
                }
            } else {
                if (cardComponent.getCardData() instanceof Item){
                    if (previousSelected != event.getSource()) {
//                        parent.notifyCardsInBoard("unchoose2");
//                        parent.notifyCardsInBoard("unchoose3");
                        parent.notifyCardsInBoard("unchoose-all");

                        if (previousSelected != null) {
                            previousSelected.setScaleX(1);
                            previousSelected.setScaleY(1);
                            previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                            if (!previousSelected.isInBoard()) {
                                previousSelected.getStyleClass().add("card-hover");
                            }
                        }
                        parent.setSelectedCardDeck((CardComponent) event.getSource());
                        parent.getSelectedCardDeck().setScaleY(1.08);
                        parent.getSelectedCardDeck().setScaleX(1.08);
                        parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                        parent.getSelectedCardDeck().getStyleClass().removeAll("card-hover", "board-fill");
                        parent.notifyCardsInBoard("choose2");
                    }
                } else {
                    if (previousSelected != event.getSource()) {
                        parent.notifyCardsInBoard("unchoose-all");
//                        parent.notifyCardsInBoard("unchoose3");
                        System.out.println("aaaa");
                        if (previousSelected != null) {
                            previousSelected.setScaleX(1);
                            previousSelected.setScaleY(1);
                            previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                            if (!previousSelected.isInBoard()) {
                                previousSelected.getStyleClass().add("card-hover");
                            }
                        }
                        parent.setSelectedCardDeck((CardComponent) event.getSource());
                        parent.getSelectedCardDeck().setScaleY(1.08);
                        parent.getSelectedCardDeck().setScaleX(1.08);
                        parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                        parent.getSelectedCardDeck().getStyleClass().removeAll("card-hover", "board-fill");
                        parent.notifyCardsInBoard("choose3");
                    }
                }
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
        Card[] activeDeck = GameData.getGameData().getCurrentPlayer().getDeck().getActiveDeck();

        for (Card card : activeDeck) {
            CardComponent cardComponent;
            if (card != null) {
                cardComponent = new CardComponent(card, false);
                cardComponent.getStyleClass().add("card-hover");

                setupDraggableCard(cardComponent);

                cardComponent.setOnMouseClicked(event -> {
                    CardComponent previousSelected = parent.getSelectedCardDeck();
                    CardComponent cardChoose = (CardComponent) event.getSource();

                    if (parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan")){
                        if (cardChoose.getCardData() instanceof LivingBeing) {
                            if (previousSelected != event.getSource()) {
//                                parent.notifyCardsInBoard("unchoose");
                                parent.notifyCardsInBoard("unchoose-all");

                                if (previousSelected != null) {
                                    previousSelected.setScaleX(1);
                                    previousSelected.setScaleY(1);
                                    previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                                    if (!previousSelected.isInBoard()) {
                                        previousSelected.getStyleClass().add("card-hover");
                                    }
                                }
                                parent.setSelectedCardDeck((CardComponent) event.getSource());
                                parent.getSelectedCardDeck().setScaleY(1.08);
                                parent.getSelectedCardDeck().setScaleX(1.08);
                                parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                                parent.getSelectedCardDeck().getStyleClass().removeAll("card-hover","board-fill");
                                parent.notifyCardsInBoard("choose");
                            } else {
                                previousSelected.setScaleX(1);
                                previousSelected.setScaleY(1);
                                previousSelected.getStyleClass().removeAll("card-choose","board-fill");
                                previousSelected.getStyleClass().add("card-hover");
                                parent.setSelectedCardDeck(null);
//                                parent.notifyCardsInBoard("unchoose");
                                parent.notifyCardsInBoard("unchoose-all");


                            }
                        } else if (cardChoose.getCardData() instanceof Product || cardChoose.getCardData() instanceof Item) {
                            if (previousSelected != event.getSource()) {
//                                parent.notifyCardsInBoard("unchoose2");
                                parent.notifyCardsInBoard("unchoose-all");
                                if (previousSelected != null) {
                                    previousSelected.setScaleX(1);
                                    previousSelected.setScaleY(1);
                                    previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                                    if (!previousSelected.isInBoard()) {
                                        previousSelected.getStyleClass().add("card-hover");
                                    }
                                }
                                parent.setSelectedCardDeck((CardComponent) event.getSource());
                                parent.getSelectedCardDeck().setScaleY(1.08);
                                parent.getSelectedCardDeck().setScaleX(1.08);
                                parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                                parent.getSelectedCardDeck().getStyleClass().removeAll("card-hover", "board-fill");
                                parent.notifyCardsInBoard("choose2");

                            } else {
                                previousSelected.setScaleX(1);
                                previousSelected.setScaleY(1);
                                previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                                previousSelected.getStyleClass().add("card-hover");
                                parent.setSelectedCardDeck(null);
//                                parent.notifyCardsInBoard("unchoose2");
                                parent.notifyCardsInBoard("unchoose-all");

                            }
                        }
                    } else {
                        if (cardChoose.getCardData() instanceof Item){
                            if (previousSelected != event.getSource()) {
//                                parent.notifyCardsInBoard("unchoose2");
//                                parent.notifyCardsInBoard("unchoose3");
                                parent.notifyCardsInBoard("unchoose-all");

                                if (previousSelected != null) {
                                    previousSelected.setScaleX(1);
                                    previousSelected.setScaleY(1);
                                    previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                                    if (!previousSelected.isInBoard()) {
                                        previousSelected.getStyleClass().add("card-hover");
                                    }
                                }
                                parent.setSelectedCardDeck((CardComponent) event.getSource());
                                parent.getSelectedCardDeck().setScaleY(1.08);
                                parent.getSelectedCardDeck().setScaleX(1.08);
                                parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                                parent.getSelectedCardDeck().getStyleClass().removeAll("card-hover", "board-fill");
                                parent.notifyCardsInBoard("choose2");
                            } else {
                                previousSelected.setScaleX(1);
                                previousSelected.setScaleY(1);
                                previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                                previousSelected.getStyleClass().add("card-hover");
                                parent.setSelectedCardDeck(null);
//                                parent.notifyCardsInBoard("unchoose2");
                                parent.notifyCardsInBoard("unchoose-all");

                            }
                        } else {
                            if (previousSelected != event.getSource()) {
//                                parent.notifyCardsInBoard("unchoose2");
//                                parent.notifyCardsInBoard("unchoose3");
                                parent.notifyCardsInBoard("unchoose-all");

                                if (previousSelected != null) {
                                    previousSelected.setScaleX(1);
                                    previousSelected.setScaleY(1);
                                    previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                                    if (!previousSelected.isInBoard()) {
                                        previousSelected.getStyleClass().add("card-hover");
                                    }
                                }
                                parent.setSelectedCardDeck((CardComponent) event.getSource());
                                parent.getSelectedCardDeck().setScaleY(1.08);
                                parent.getSelectedCardDeck().setScaleX(1.08);
                                parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                                parent.getSelectedCardDeck().getStyleClass().removeAll("card-hover", "board-fill");
                                parent.notifyCardsInBoard("choose3");
                            } else {
                                previousSelected.setScaleX(1);
                                previousSelected.setScaleY(1);
                                previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                                previousSelected.getStyleClass().add("card-hover");
                                parent.setSelectedCardDeck(null);
//                                parent.notifyCardsInBoard("unchoose3");
                                parent.notifyCardsInBoard("unchoose-all");

                            }
                        }
                    }
                });
            } else {
                cardComponent = new CardComponent(null, false);
            }

            deckContainer.getDeck().getChildren().add(cardComponent);
        }
    }
}
