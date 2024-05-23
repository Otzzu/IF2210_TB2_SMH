package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.Plant;
import com.ooopppp.tubes_oop_2.Helper.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class CardComponent extends VBox implements Observer {
    private Card cardData;
    private Text textName;
    private ImageView imageView;

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
        this.setPadding(new Insets(10,10,10,10));

        imageView = new ImageView();
        imageView.setFitHeight(45);
        imageView.setFitWidth(45);
        textName = new Text();
        textName.getStyleClass().add("text-card");
        textName.setWrappingWidth(55);
        textName.setTextAlignment(TextAlignment.CENTER);

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
//            System.out.println(cardData.getName());
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img" + cardData.getImage())));
            Region space = new Region();
            VBox.setVgrow(space, Priority.ALWAYS);
            imageView.setImage(image);
            textName.setText(cardData.getName().replace(" ", "\n"));
            this.getChildren().addAll(imageView, space, textName);
        }
    }

    @Override
    public void update(String data) {
        if (data.equals("unchoose-all")){
            this.getStyleClass().removeAll("board-fill", "card-hover", "board-empty");
            return;
        }
        if (data.equals("choose")){
            if (cardData == null && isInBoard){
                this.getStyleClass().addAll("card-hover", "board-empty");
            } else if (cardData != null && isInBoard){
                this.getStyleClass().addAll("board-fill");
            }
        } else if (data.equals("choose2")){
            if (cardData != null && isInBoard){
                this.getStyleClass().addAll("card-hover", "board-empty");
            } else if (cardData == null && isInBoard){
                this.getStyleClass().addAll("board-fill");
            }
        } else if (data.equals("choose3")){
            this.getStyleClass().addAll("board-fill");
        }
    }

    public void setImageView(double width, double height) {
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
    }
}
