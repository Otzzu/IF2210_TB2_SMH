package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.Board;
import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import com.ooopppp.tubes_oop_2.Boundary.Component.ErrorDialog;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class BoardController {
    private Board board;
    private MainView parent;
    public BoardController(Board board, MainView parent) {
        this.board = board;
        this.parent = parent;
        attachEvents();
    }

    public void attachEvents(){
        board.setOnDragOver(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString() &&
                    event.getDragboard().getString().equals("VBox") && parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan")) {
                event.acceptTransferModes(TransferMode.MOVE);

            }
            event.consume();

        });
    }

    public void setupDropTargetCard(CardComponent card, int finalI, int finalJ) {
        card.setOnDragOver(event -> {
            CardComponent cardComponent = (CardComponent) event.getSource();
            if (event.getGestureSource() != cardComponent &&
                    event.getDragboard().hasString() &&
                    event.getDragboard().getString().equals("VBox") ) {
                if (cardComponent.getCardData() == null && parent.getSelectedCardDeck().getCardData() instanceof LivingBeing){
                    event.acceptTransferModes(TransferMode.MOVE);
                    cardComponent.setScaleX(1.05);
                    cardComponent.setScaleY(1.05);
                }
            }
            event.consume();
        });

        card.setOnDragExited(e -> {
            CardComponent cardComponent = (CardComponent) e.getSource();

            cardComponent.setScaleX(1);
            cardComponent.setScaleY(1);
        });

        card.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString() && db.getString().equals("VBox")) {
                Card data = parent.getSelectedCardDeck().getCardData();
                CardComponent current = (CardComponent) event.getSource();

                if (parent.getSelectedCardDeck() != null ){
                    if (((CardComponent)event.getSource()).getCardData() == null && data instanceof LivingBeing){
                        current.getStyleClass().removeAll("card-hover", "board-empty");
                        parent.getSelectedCardDeck().setCardData(null);
                        parent.getSelectedCardDeck().getStyleClass().removeAll("card-choose", "card-hover");
                        parent.getSelectedCardDeck().setScaleX(1);
                        parent.getSelectedCardDeck().setScaleY(1);
                        parent.notifyCardsInBoard("unchoose");
                        if (!parent.getSelectedCardDeck().isInBoard()){
                            GameData.getGameData().getCurrentPlayer().moveCardToFarm(data.getId(), finalI, finalJ);
                            parent.getDeckContainer().getController().renderDeck();
                        } else {
                            GameData.getGameData().getCurrentPlayer().moveCardInFarm(data.getId(), finalI, finalJ);
                            this.populateGrid(false);
                        }
                        current.setCardData(data);
                    } else if (((CardComponent)event.getSource()).getCardData() != null) {
                        try {
                            if (parent.getSelectedCardDeck().getCardData() instanceof Product product){
                                if (current.getCardData() instanceof Animal animal){
                                    GameData.getGameData().getCurrentPlayer().giveEat(product, animal);
                                    parent.getDeckContainer().getController().renderDeck();
                                    parent.getBoard().getController().populateGrid(false);
                                } else if (current.getCardData() instanceof Plant){
                                    throw new Exception("Plant cannot eat");
                                }
                            }
                        } catch (Exception exception){
                            ErrorDialog.showErrorDialog(parent.getStage(), exception.getMessage());

                        }
                    }


                    parent.setSelectedCardDeck(null);

                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }


    public void setupDraggableCard(CardComponent card){
        card.setOnDragDetected(event -> {

            CardComponent cardComponent = (CardComponent) event.getSource();
            Dragboard db = cardComponent.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("VBox");
            db.setContent(content);
            VBox previousSelected = parent.getSelectedCardDeck();

            if (previousSelected != event.getSource() && cardComponent.getCardData() != null){
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
                ((CardComponent) event.getSource()).setVisible(false);  // Hide or remove the original
            }
            event.consume();

        });
    }

    public void populateGrid(boolean enemy) {
        board.getChildren().clear();
        parent.clearObserver();
        Farm farm;
        if (!enemy){
            farm = GameData.getGameData().getCurrentPlayer().getFarm();
        } else {
            farm = GameData.getGameData().getAnotherPlayer().getFarm();
        }

        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {

                CardComponent card = new CardComponent(farm.get(i, j), true);
                if (!enemy){
                    this.setupDropTargetCard(card, i, j);
                    this.setupDraggableCard(card);
                    this.setUpOnClickCard(card, i, j);
                    parent.addObserver(card);
                }

                board.add(card, j, i);
            }
        }
    }

    public void setUpOnClickCard(CardComponent card, int finalI, int finalJ){
        card.setOnMouseClicked(e -> {
            CardComponent current = (CardComponent) e.getSource();

            if (parent.getSelectedCardDeck() != null && current.getCardData() == null){
                Card data = parent.getSelectedCardDeck().getCardData();

                current.getStyleClass().removeAll("card-hover", "board-empty");
                parent.getSelectedCardDeck().setCardData(null);
                parent.getSelectedCardDeck().getStyleClass().removeAll("card-choose", "card-hover");
                parent.getSelectedCardDeck().setScaleX(1);
                parent.getSelectedCardDeck().setScaleY(1);
                parent.notifyCardsInBoard("unchoose");
                if (!parent.getSelectedCardDeck().isInBoard()){
                    GameData.getGameData().getCurrentPlayer().moveCardToFarm(data.getId(), finalI, finalJ);
                    parent.getDeckContainer().getController().renderDeck();
                } else {
                    GameData.getGameData().getCurrentPlayer().moveCardInFarm(data.getId(), finalI, finalJ);
                    this.populateGrid(false);
                };
                current.setCardData(data);

                parent.setSelectedCardDeck(null);
            } else if (current.getCardData() != null){
                if (e.getClickCount() == 2){

                    CardComponent previousSelected = parent.getSelectedCardDeck();

                    if (previousSelected != current ){
                        parent.notifyCardsInBoard("unchoose");

                        if (previousSelected != null){
                            previousSelected.setScaleX(1);
                            previousSelected.setScaleY(1);
                            previousSelected.getStyleClass().remove("card-choose");
                            if (!previousSelected.isInBoard()) {
                                previousSelected.getStyleClass().add("card-hover");
                            }
                        }
                        parent.setSelectedCardDeck(current);
                        parent.getSelectedCardDeck().setScaleY(1.08);
                        parent.getSelectedCardDeck().setScaleX(1.08);
                        parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                        parent.notifyCardsInBoard("choose");
                        parent.getSelectedCardDeck().getStyleClass().remove("board-fill");

                    } else {
                        previousSelected.setScaleX(1);
                        previousSelected.setScaleY(1);
                        previousSelected.getStyleClass().remove("card-choose");
                        parent.setSelectedCardDeck(null);
                        parent.notifyCardsInBoard("unchoose");

                    }
                } else if (e.getClickCount() == 1){
                    // SHOW DESKRIPS MODAL
                    if (parent.getSelectedCardDeck().getCardData() != null){
                        try {
                            if (parent.getSelectedCardDeck().getCardData() instanceof Product product){
                                if (current.getCardData() instanceof Animal animal){
                                    GameData.getGameData().getCurrentPlayer().giveEat(product, animal);
                                    parent.getDeckContainer().getController().renderDeck();
                                    parent.getBoard().getController().populateGrid(false);
                                } else if (current.getCardData() instanceof Plant){
                                    throw new Exception("Plant cannot eat");
                                }
                            }
                        } catch (Exception exception){
//                            ErrorDialog.getInstance().showError(exception.getMessage());

                        }
                    }

                }
            }



        });
    }


}
