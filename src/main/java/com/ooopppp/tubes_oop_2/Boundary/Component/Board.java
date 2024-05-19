package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.Farm;
import com.ooopppp.tubes_oop_2.Entity.GameState;
import com.ooopppp.tubes_oop_2.Helper.Observer;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board extends GridPane {

    //array of card data
    private final int row;
    private final int col;
    private MainView parent;
    private DeckContainer deckContainer;

    public Board(int col, int row, MainView parent, DeckContainer deckContainer){
        super();
        this.deckContainer = deckContainer;
        this.parent = parent;
        this.row = row;
        this.col = col;
        this.setPrefHeight(492);
        this.setMaxHeight(492);
        this.setPrefWidth(530);
        this.setHgap(20);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER_LEFT);
        setupGrid();
        populateGrid();
    }

    private void setupGrid() {
        for (int i = 0; i < this.col; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHalignment(HPos.CENTER);
            columnConstraints.setPercentWidth(100.0 / this.col);
            this.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < this.row; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setValignment(VPos.CENTER);
            rowConstraints.setPercentHeight(100.0 / this.row);
            this.getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        //get data ladang player , example

        Farm farm = GameState.getGameState().getCurrentPlayer().getFarm();

        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {

                CardComponent card = new CardComponent(farm.get(j , i), true);
                int finalJ = j;
                int finalI = i;
                card.setOnMouseClicked(e -> {
                    if (parent.getSelectedCardDeck() != null && ((CardComponent)e.getSource()).getCardData() == null){
                        Card data = parent.getSelectedCardDeck().getCardData();
                        GameState.getGameState().getCurrentPlayer().moveCardToFarm(data.getId(), finalJ, finalI);
                        CardComponent current = (CardComponent) e.getSource();
                        current.getStyleClass().removeAll("card-hover", "board-empty");
                        current.setCardData(data);
                        parent.getSelectedCardDeck().setCardData(null);
                        parent.getSelectedCardDeck().getStyleClass().removeAll("card-choose", "card-hover");
                        parent.getSelectedCardDeck().setScaleX(1);
                        parent.getSelectedCardDeck().setScaleY(1);
                        parent.notifyCardsInBoard("unchoose");
                        deckContainer.renderDeck();
                        parent.setSelectedCardDeck(null);
                    }
                });

                card.setOnMouseEntered(e -> {
                    if (((CardComponent)e.getSource()).getCardData() != null){
                        ImageCursor img = new ImageCursor(new Image(Objects.requireNonNull(getClass().getResource("/com/ooopppp/tubes_oop_2/img/no-cursor.png")).toExternalForm()));
                        ((CardComponent)e.getSource()).setCursor(img);
                    }

                });

                card.setOnMouseExited(e -> {
                    ((CardComponent)e.getSource()).setCursor(Cursor.DEFAULT);
                });


                parent.addObserver(card);
                this.add(card, i, j);
            }
        }
    }
}
