package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Controller.HeaderController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class Header extends HBox {
    private VBox moneyContainer;
    private VBox turnContainer;
    private Text moneyPlayer1;
    private Text moneyPlayer2;
    private Text turnNumber;
    private Button buttonNext;
    private Text player1Text;
    private Text player2Text;
    private HeaderController controller;


    public Header(MainView parent){
        super();
        controller = new HeaderController(this, parent);

        moneyContainer = new VBox();
        moneyContainer.getStyleClass().add("container-point");
        moneyContainer.setPrefWidth(325);
        moneyContainer.setMaxHeight(100);

        player1Text = new Text("Player 1");
        player2Text = new Text("Player 2");
        moneyPlayer1 = new Text("400");
        moneyPlayer2 = new Text("400");

        player1Text.getStyleClass().add("text-point");
        player2Text.getStyleClass().add("text-point");
        moneyPlayer1.getStyleClass().add("text-point");
        moneyPlayer2.getStyleClass().add("text-point");

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        Region space2 = new Region();
        HBox.setHgrow(space2, Priority.ALWAYS);

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/coin.png")));
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/coin.png")));

        ImageView imageView = new ImageView(image);
        ImageView imageView2 = new ImageView(image2);

        imageView.setFitHeight(25);
        imageView.setFitWidth(25);

        imageView2.setFitHeight(25);
        imageView2.setFitWidth(25);

        HBox player1Container = new HBox();
        player1Container.getChildren().addAll(player1Text, space, moneyPlayer1, imageView);
        player1Container.setAlignment(Pos.CENTER);

        HBox player2Container = new HBox();
        player2Container.getChildren().addAll(player2Text, space2, moneyPlayer2, imageView2);
        player2Container.setAlignment(Pos.CENTER);

        moneyContainer.getChildren().addAll(player1Container, player2Container);
        moneyContainer.setSpacing(10);
        moneyContainer.setPadding(new Insets(20, 20, 20, 20));

        turnContainer = new VBox();
        Text turnText = new Text("Turn");
        turnNumber = new Text("1");

        turnText.getStyleClass().add("text-white");
        turnNumber.getStyleClass().add("text-white");

        turnText.setStyle("-fx-font-size: 20px");
        turnNumber.setStyle("-fx-font-size: 40px");

        turnContainer.getStyleClass().add("circular-turn");
        turnContainer.setPadding(new Insets(10,10,10,10));
        turnContainer.setSpacing(2);
        turnContainer.setPrefWidth(110);
        turnContainer.setPrefHeight(110);
        turnContainer.setMaxWidth(110);
        turnContainer.setMaxHeight(110);
        turnContainer.setAlignment(Pos.CENTER);
        turnContainer.getChildren().addAll(turnText, turnNumber);

        buttonNext = new Button("Next");
        buttonNext.getStyleClass().add("button-dark-brown");
        buttonNext.setPrefWidth(130);

        Region space3 = new Region();
        space3.setMinWidth(60);

        Region space4 = new Region();
        HBox.setHgrow(space4, Priority.ALWAYS);

        HBox boxLeft = new HBox();
        boxLeft.getChildren().addAll(moneyContainer, turnContainer);
        boxLeft.setSpacing(80);
        boxLeft.setAlignment(Pos.CENTER);

        this.getChildren().addAll(boxLeft, space4, buttonNext,space3);
        this.setAlignment(Pos.CENTER);

        controller.attachEventsButtonNext();
        controller.changePlayerTextColor();
    }

    public Text getTurnNumber() {
        return turnNumber;
    }

    public Button getButtonNext() {
        return buttonNext;
    }

    public Text getPlayer1Text() {
        return player1Text;
    }

    public Text getPlayer2Text() {
        return player2Text;
    }

    public HeaderController getController() {
        return controller;
    }
}
