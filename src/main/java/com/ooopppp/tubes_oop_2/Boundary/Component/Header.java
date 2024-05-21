package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.ShuffleView;
import com.ooopppp.tubes_oop_2.Controller.HeaderController;
import com.ooopppp.tubes_oop_2.Entity.Card;
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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Header extends HBox {
    private VBox moneyContainer;
    private VBox turnContainer;
    private VBox timerContainer;
    private Text moneyPlayer1;
    private Text moneyPlayer2;
    private Text turnNumber;
    private Button buttonNext;
    private Timeline timeline;

    private Text player1Text;
    private Text player2Text;
    private HeaderController controller;


    public Header(MainView parent) {
        super();
        controller = new HeaderController(this, parent);

        initializeComponents(false);
        this.setAlignment(Pos.CENTER);
    }

    public void initializeComponents(boolean showTimer) {
        setUpPlayerMoney();
        setUpTurnContainer();
        if (showTimer) { // muncul di beruang doang
            setUpTimerContainer();
        }
        setUpNextButton();
        arrangeComponents(showTimer);
    }

    private void setUpPlayerMoney() {
        moneyContainer = new VBox(10);
        moneyContainer.getStyleClass().add("container-point");
        moneyContainer.setPadding(new Insets(20));
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

        Image coinImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/coin.png")));
        ImageView imageView1 = new ImageView(coinImage);
        imageView1.setFitHeight(25);
        imageView1.setFitWidth(25);

        ImageView imageView2 = new ImageView(coinImage);
        imageView2.setFitHeight(25);
        imageView2.setFitWidth(25);

        HBox player1Container = new HBox(player1Text, new Region(), moneyPlayer1, imageView1);
        player1Container.setAlignment(Pos.CENTER);
        HBox.setHgrow(player1Container.getChildren().get(1), Priority.ALWAYS);

        HBox player2Container = new HBox(player2Text, new Region(), moneyPlayer2, imageView2);
        player2Container.setAlignment(Pos.CENTER);
        HBox.setHgrow(player2Container.getChildren().get(1), Priority.ALWAYS);

        moneyContainer.getChildren().addAll(player1Container, player2Container);
    }

    private void setUpTurnContainer() {
        turnContainer = new VBox(2);
        turnContainer.getStyleClass().add("circular-turn");
        turnContainer.setPadding(new Insets(10));
        turnContainer.setAlignment(Pos.CENTER);
        turnContainer.setPrefSize(110, 110);

        Text turnText = new Text("Turn");
        turnNumber = new Text("1");

        turnText.getStyleClass().add("text-white");
        turnNumber.getStyleClass().add("text-white");

        turnText.setStyle("-fx-font-size: 20px");
        turnNumber.setStyle("-fx-font-size: 40px");

        turnContainer.getChildren().addAll(turnText, turnNumber);
    }

    private void setUpTimerContainer() {
        timerContainer = new VBox(2);
        timerContainer.getStyleClass().add("circular-timer");
        timerContainer.setPadding(new Insets(10));
        timerContainer.setAlignment(Pos.CENTER);
        timerContainer.setPrefSize(110, 110);
        timerContainer.setMaxWidth(130);
        timerContainer.setMaxHeight(130);

        Text timerText = new Text("Timer");
        Text timerValue = new Text("30.0s");  // hrsnya ganti random 30 - 60

        timerText.getStyleClass().add("text-black");
        timerValue.getStyleClass().add("text-black");

        timerText.setStyle("-fx-font-size: 24px");
        timerValue.setStyle("-fx-font-size: 22px");

        timerContainer.getChildren().addAll(timerText, timerValue);
        startCountdown(timerValue);
    }


    private void startCountdown(Text timerValue) {
        final int[] timeRemaining = {3000};
        final Timeline[] timelineHolder = new Timeline[1];

        timelineHolder[0] = new Timeline(
                new KeyFrame(Duration.millis(10), event -> {
                    timeRemaining[0]--;
                    int s = timeRemaining[0] / 100;
                    int ms = timeRemaining[0] % 100;
                    timerValue.setText(String.format("%02d:%02d", s, ms)); // format s:ms (12:63)
                    if (timeRemaining[0] <= 0) {
                        timelineHolder[0].stop();
                        timerValue.setText("00:00");
                    }
                })
        );
        timelineHolder[0].setCycleCount(Timeline.INDEFINITE);
        timelineHolder[0].play();
    }


    private void setUpNextButton() {
        buttonNext = new Button("Next");
        buttonNext.getStyleClass().add("button-dark-brown");
        buttonNext.setPrefWidth(130);
    }

    private void arrangeComponents(boolean showTimer) {
        Region spaceBetween = new Region();
        HBox.setHgrow(spaceBetween, Priority.ALWAYS);  // space money and turn

        Region spaceBetweenTurnAndTimer = new Region();
        HBox.setHgrow(spaceBetweenTurnAndTimer, Priority.ALWAYS);  // space turn and timer

        Region spaceBetweenTimerAndButton = new Region();
        HBox.setHgrow(spaceBetweenTimerAndButton, Priority.ALWAYS);  // space timer and next button

        HBox leftBox = new HBox();
        leftBox.getChildren().addAll(moneyContainer);
        leftBox.setAlignment(Pos.CENTER);

        HBox middleBox = new HBox();
        middleBox.getChildren().addAll(turnContainer);
        if (showTimer) {
            middleBox.getChildren().addAll(spaceBetweenTurnAndTimer, timerContainer);
        }
        middleBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(middleBox, Priority.ALWAYS);  // Space added so that it balances out with left and right

        this.getChildren().addAll(leftBox, spaceBetween, middleBox, spaceBetweenTimerAndButton, buttonNext);
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
