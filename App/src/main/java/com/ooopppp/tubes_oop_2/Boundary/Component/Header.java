package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.ShuffleView;
import com.ooopppp.tubes_oop_2.Controller.HeaderController;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Player;
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
import java.util.Random;

import javafx.application.Platform;

public class Header extends HBox {
    private VBox moneyContainer;
    private VBox turnContainer;
    private VBox timerContainer;
    private Text moneyPlayer1;
    private Text moneyPlayer2;
    private Text turnNumber;
    private Button buttonNext;
    private Thread timerThread;
    private boolean timerRunning = false;

    private Text player1Text;
    private Text player2Text;
    private HeaderController controller;
    private Player[] players;
    private MainView parent;
    private Text timerValue;


    public Header(MainView parent) {
        super();
        this.parent = parent;
        controller = new HeaderController(this, parent);
        players = GameData.getGameData().getPlayers();
        initializeComponents(false);
        this.setAlignment(Pos.CENTER);
    }

    public void initializeComponents(boolean showTimer) {
        this.getChildren().clear();
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



        player1Text = new Text(players[0].getName());
        player2Text = new Text(players[1].getName());
        moneyPlayer1 = new Text(String.valueOf(players[0].getGulden()));
        moneyPlayer2 = new Text(String.valueOf(players[1].getGulden()));


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
        turnNumber = new Text(String.valueOf(GameData.getGameData().getTurn()));

        turnText.getStyleClass().add("text-white");
        turnNumber.getStyleClass().add("text-white");

        turnText.setStyle("-fx-font-size: 20px");
        turnNumber.setStyle("-fx-font-size: 40px");

        turnContainer.getChildren().addAll(turnText, turnNumber);
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public void setUpTimerContainer() {
        timerContainer = new VBox(2);
        timerContainer.getStyleClass().add("circular-timer");
        timerContainer.setPadding(new Insets(10));
        timerContainer.setAlignment(Pos.CENTER);
        timerContainer.setPrefSize(110, 110);
        timerContainer.setMaxWidth(130);
        timerContainer.setMaxHeight(130);

        Text timerText = new Text("Timer");
        timerValue = new Text();
        timerText.getStyleClass().add("text-black");
        timerValue.getStyleClass().add("text-black");

        timerText.setStyle("-fx-font-size: 24px");
        timerValue.setStyle("-fx-font-size: 22px");

        timerContainer.getChildren().addAll(timerText, timerValue);
        Random random = new Random();
        int randomNumber = random.nextInt(30,61);
        startThreadedCountdown(randomNumber);
    }

    private void startThreadedCountdown(double initialTime) {
        disableAllButtons();
        if (timerThread != null && timerThread.isAlive()) {
            timerRunning = false;
            try {
                timerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        timerRunning = true;
        timerThread = new Thread(() -> {
            double timeRemaining = initialTime * 10; // Convert to tenths of a second
            while (timeRemaining > 0 && timerRunning) {
                timeRemaining--;
                double displayTime = timeRemaining / 10.0;
                Platform.runLater(() -> timerValue.setText(String.format("%.1fs", displayTime)));
                try {
                    Thread.sleep(100); // Sleep for 0.1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                timerRunning = false;
                timerValue.setText("0.0s");
                parent.getController().clearAttackAreas();
                enableAllButtons();
                this.getChildren().remove(timerContainer);
            });
        });
        timerThread.setDaemon(true);
        timerThread.start();
    }

    private void disableAllButtons() {

        parent.getSidebar().disableButtons(true);
    }

    private void enableAllButtons() {

        parent.getSidebar().disableButtons(false);
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
