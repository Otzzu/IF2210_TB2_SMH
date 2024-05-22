package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.Board;
import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import com.ooopppp.tubes_oop_2.Boundary.Component.MessageDialog;
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
                    event.getDragboard().getString().equals("VBox")) {
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
                System.out.println(parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan"));
                if (parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan") && ((cardComponent.getCardData() == null && parent.getSelectedCardDeck().getCardData() instanceof LivingBeing) || (cardComponent.getCardData() != null && (parent.getSelectedCardDeck().getCardData() instanceof Product || parent.getSelectedCardDeck().getCardData() instanceof Item)))){
                    event.acceptTransferModes(TransferMode.MOVE);
                    cardComponent.setScaleX(1.05);
                    cardComponent.setScaleY(1.05);
                } else if (parent.getSidebar().getButtonLadang().getText().equals("Ladangku") && cardComponent.getCardData() != null && parent.getSelectedCardDeck().getCardData() instanceof Item){
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
                if (parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan")){
                    if (parent.getSelectedCardDeck() != null ){
                        if (current.getCardData() == null && data instanceof LivingBeing){
                            current.getStyleClass().removeAll("card-hover", "board-empty", "board-fill");
                            parent.getSelectedCardDeck().setCardData(null);
                            parent.getSelectedCardDeck().getStyleClass().removeAll("card-choose", "card-hover", "board-fill");
                            parent.getSelectedCardDeck().setScaleX(1);
                            parent.getSelectedCardDeck().setScaleY(1);
                            parent.notifyCardsInBoard("unchoose-all");
                            if (!parent.getSelectedCardDeck().isInBoard()){
                                GameData.getGameData().getCurrentPlayer().moveCardToFarm(data.getId(), finalI, finalJ);
                                parent.getDeckContainer().getController().renderDeck();
                            } else {
                                GameData.getGameData().getCurrentPlayer().moveCardInFarm(data.getId(), finalI, finalJ);
                                this.populateGrid(false);
                            }
                            current.setCardData(data);
                        } else if (current.getCardData() != null) {
                            try {
                                if (data instanceof Product product){
                                    if (current.getCardData() instanceof Animal animal){
                                        GameData.getGameData().getCurrentPlayer().giveEat(product, animal);
                                        parent.getDeckContainer().getController().renderDeck();
                                        parent.getBoard().getController().populateGrid(false);
                                        animal.print();
                                    } else if (current.getCardData() instanceof Plant){
                                        throw new Exception("Plant cannot eat");
                                    }
                                } else if (data instanceof Item item){
                                    LivingBeing livingBeing = (LivingBeing)current.getCardData();
                                    GameData.getGameData().getCurrentPlayer().giveItemToOwn(item, livingBeing);
                                    parent.getDeckContainer().getController().renderDeck();
                                    parent.getBoard().getController().populateGrid(false);
                                    livingBeing.print();
                                }
                            } catch (Exception exception){
                                MessageDialog.showErrorDialog(parent.getStage(), exception.getMessage());
                                parent.getDeckContainer().getController().renderDeck();
                                parent.getBoard().getController().populateGrid(false);
                            }
                        }

                        parent.setSelectedCardDeck(null);

                    }
                } else {
                    if (parent.getSelectedCardDeck() != null ){
                        try {
                            if (current.getCardData() != null && data instanceof Item item){
                                LivingBeing livingBeing = (LivingBeing)current.getCardData();
                                GameData.getGameData().getCurrentPlayer().giveItemToEnemy(item, livingBeing, GameData.getGameData().getAnotherPlayer());
                                parent.getDeckContainer().getController().renderDeck();
                                parent.getBoard().getController().populateGrid(true);
                                livingBeing.print();

                            } else {
                                throw new Exception("This card cannot use here");
                            }
                        } catch (Exception exception){
                            MessageDialog.showErrorDialog(parent.getStage(), exception.getMessage());
                            parent.getDeckContainer().getController().renderDeck();
                            parent.getBoard().getController().populateGrid(true);
                        }

                        parent.setSelectedCardDeck(null);
                    }
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
                    this.setupDraggableCard(card);
                }
                this.setupDropTargetCard(card, i, j);
                this.setUpOnClickCard(card, i, j);

                parent.addObserver(card);


                board.add(card, j, i);
            }
        }
        if(parent.getController().getAttackPositions().size() != 0){
            parent.getController().renderAttack();
        }

    }

    public void setUpOnClickCard(CardComponent card, int finalI, int finalJ){
        card.setOnMouseClicked(e -> {
            CardComponent current = (CardComponent) e.getSource();

            if (parent.getSelectedCardDeck() != null && current.getCardData() == null && parent.getSelectedCardDeck().getCardData() instanceof LivingBeing){
                Card data = parent.getSelectedCardDeck().getCardData();

                current.getStyleClass().removeAll("card-hover", "board-empty","board-fill");
                parent.getSelectedCardDeck().setCardData(null);
                parent.getSelectedCardDeck().getStyleClass().removeAll("card-choose", "card-hover", "board-fill");
                parent.getSelectedCardDeck().setScaleX(1);
                parent.getSelectedCardDeck().setScaleY(1);
                parent.notifyCardsInBoard("unchoose-all");
                if (!parent.getSelectedCardDeck().isInBoard()){
                    GameData.getGameData().getCurrentPlayer().moveCardToFarm(data.getId(), finalI, finalJ);
                    parent.getDeckContainer().getController().renderDeck();
                } else {
                    GameData.getGameData().getCurrentPlayer().moveCardInFarm(data.getId(), finalI, finalJ);
                    this.populateGrid(false);
                };
                current.setCardData(data);

                parent.setSelectedCardDeck(null);
                return;
            }

            if (current.getCardData() != null){
                if (e.getClickCount() == 2){

                    CardComponent previousSelected = parent.getSelectedCardDeck();

                    if (previousSelected != current ){
//                        parent.notifyCardsInBoard("unchoose");
                        parent.notifyCardsInBoard("unchoose-all");

                        if (previousSelected != null){
                            previousSelected.setScaleX(1);
                            previousSelected.setScaleY(1);
                            previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                            if (!previousSelected.isInBoard()) {
                                previousSelected.getStyleClass().add("card-hover");
                            }
                        }
                        parent.setSelectedCardDeck(current);
                        parent.getSelectedCardDeck().setScaleY(1.08);
                        parent.getSelectedCardDeck().setScaleX(1.08);
                        parent.getSelectedCardDeck().getStyleClass().add("card-choose");
                        parent.notifyCardsInBoard("choose");
                        parent.getSelectedCardDeck().getStyleClass().removeAll("board-fill", "card-hover");

                    } else {
                        previousSelected.setScaleX(1);
                        previousSelected.setScaleY(1);
                        previousSelected.getStyleClass().removeAll("card-choose", "board-fill");
                        parent.setSelectedCardDeck(null);
//                        parent.notifyCardsInBoard("unchoose");
                        parent.notifyCardsInBoard("unchoose-all");


                    }
                } else if (e.getClickCount() == 1){
                    if (parent.getSelectedCardDeck() != null && parent.getSelectedCardDeck().getCardData() != null){
                        if (parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan")){
                            try {
                                if (parent.getSelectedCardDeck().getCardData() instanceof Product product){
                                    if (current.getCardData() instanceof Animal animal){
                                        GameData.getGameData().getCurrentPlayer().giveEat(product, animal);
                                        parent.getDeckContainer().getController().renderDeck();
                                        parent.getBoard().getController().populateGrid(false);
                                        animal.print();
                                    } else if (current.getCardData() instanceof Plant){
                                        throw new Exception("Plant cannot eat");
                                    }
                                } else if (parent.getSelectedCardDeck().getCardData() instanceof Item item){
                                    LivingBeing livingBeing = (LivingBeing)current.getCardData();
                                    GameData.getGameData().getCurrentPlayer().giveItemToOwn(item, livingBeing);
                                    parent.getDeckContainer().getController().renderDeck();
                                    parent.getBoard().getController().populateGrid(false);
                                    livingBeing.print();
                                }
                            } catch (Exception exception){
                                MessageDialog.showErrorDialog(parent.getStage(), exception.getMessage());
                                parent.getDeckContainer().getController().renderDeck();
                                parent.getBoard().getController().populateGrid(false);
                            }
                        } else {
                            if (parent.getSelectedCardDeck() != null ){
                                if (current.getCardData() != null && parent.getSelectedCardDeck().getCardData() instanceof Item item){
                                    try {
                                        LivingBeing livingBeing = (LivingBeing)current.getCardData();
                                        GameData.getGameData().getCurrentPlayer().giveItemToEnemy(item, livingBeing, GameData.getGameData().getAnotherPlayer());
                                        parent.getDeckContainer().getController().renderDeck();
                                        parent.getBoard().getController().populateGrid(true);
                                        livingBeing.print();
                                    } catch (Exception exception){
                                        MessageDialog.showErrorDialog(parent.getStage(), exception.getMessage());
                                        parent.getDeckContainer().getController().renderDeck();
                                        parent.getBoard().getController().populateGrid(true);
                                    }
                                }

                                parent.setSelectedCardDeck(null);

                            }
                        }

                    } else if (parent.getSelectedCardDeck() == null && parent.getSelectedCardDeck().getCardData() != null) {
                        //SHOW INFO
                        if (parent.getSidebar().getButtonLadang().getText().equals("Ladang Lawan")){//buat ladang sendiri

                        } else { //buat ladang lawan

                        }
                    }

                }
            }
        });
    }


}
