package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Helper.Observer;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CardComponent extends VBox implements Observer {
    private Card cardData;
    private Text textName;
    private boolean isInBoard;
    public CardComponent(Card cardData, boolean isInBoard){
        super();
        this.isInBoard = isInBoard;
        this.cardData = cardData;
        this.getStyleClass().add("card");
        this.setPrefHeight(108);
        this.setPrefWidth(90);
        this.setMaxHeight(108);
        this.setMaxWidth(90);
        this.setAlignment(Pos.CENTER);

        textName = new Text();

        buildCard();

    }

    public boolean isInBoard() {
        return isInBoard;
    }

    public Card getCardData() {
        return cardData;
    }

    public void setCardData(Card cardData) {
        this.cardData = cardData;
        buildCard();
    }

    private void buildCard(){
        this.getChildren().clear();
        if (cardData != null){
            textName.setText(cardData.getName());
            this.getChildren().add(textName);
        }
    }

    @Override
    public void update(String data) {
        if (data.equals("choose")){

            if (cardData == null && isInBoard){
                this.getStyleClass().addAll("card-hover", "board-empty");
            } else if (cardData != null && isInBoard){
                this.getStyleClass().addAll("board-fill");
            }
        } else {
            if (cardData == null && isInBoard){
                this.getStyleClass().removeAll("card-hover", "board-empty");
            } else if (cardData != null && isInBoard){
                this.getStyleClass().removeAll("board-fill");
            }
        }
    }
}
