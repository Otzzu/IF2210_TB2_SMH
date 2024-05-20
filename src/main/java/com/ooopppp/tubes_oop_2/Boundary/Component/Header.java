package com.ooopppp.tubes_oop_2.Boundary.Component;

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

import java.util.Objects;

public class Header extends HBox {
    private VBox moneyContainer;
    private VBox turnContainer;
    private VBox timerContainer;
    private Text moneyPlayer1;
    private Text moneyPlayer2;
    private Text turnNumber;
    private Button buttonNext;
    private boolean showTimer;
    private Timeline timeline;

    public Header(boolean showTimer) {
        super();
        this.showTimer = showTimer;
        initializeComponents();
        this.setAlignment(Pos.CENTER);
    }

    private void initializeComponents() {
        setUpPlayerMoney();
        setUpTurnContainer();
        if (showTimer) {
            setUpTimerContainer();  // Conditionally set up the timer container
        }
        setUpNextButton();
        arrangeComponents();
    }

    private void setUpPlayerMoney() {
        moneyContainer = new VBox(10);
        moneyContainer.getStyleClass().add("container-point");
        moneyContainer.setPadding(new Insets(20));
        moneyContainer.setPrefWidth(325);
        moneyContainer.setMaxHeight(100);

        Text player1 = new Text("Player 1");
        Text player2 = new Text("Player 2");
        moneyPlayer1 = new Text("400");
        moneyPlayer2 = new Text("400");

        player1.getStyleClass().add("text-point");
        player2.getStyleClass().add("text-point");
        moneyPlayer1.getStyleClass().add("text-point");
        moneyPlayer2.getStyleClass().add("text-point");

        Image coinImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/coin.png")));
        ImageView imageView1 = new ImageView(coinImage);
        imageView1.setFitHeight(25);
        imageView1.setFitWidth(25);

        ImageView imageView2 = new ImageView(coinImage);
        imageView2.setFitHeight(25);
        imageView2.setFitWidth(25);

        HBox player1Container = new HBox(player1, new Region(), moneyPlayer1, imageView1);
        player1Container.setAlignment(Pos.CENTER);
        HBox.setHgrow(player1Container.getChildren().get(1), Priority.ALWAYS); // Space between elements

        HBox player2Container = new HBox(player2, new Region(), moneyPlayer2, imageView2);
        player2Container.setAlignment(Pos.CENTER);
        HBox.setHgrow(player2Container.getChildren().get(1), Priority.ALWAYS); // Space between elements

        moneyContainer.getChildren().addAll(player1Container, player2Container);
    }

    private void setUpTurnContainer() {
        turnContainer = new VBox(2);
        turnContainer.getStyleClass().add("circular-turn");
        turnContainer.setPadding(new Insets(10));
        turnContainer.setAlignment(Pos.CENTER);
        turnContainer.setPrefSize(110, 110);

        Text turnText = new Text("Turn");
        turnNumber = new Text("2");

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
        Text timerValue = new Text("30.0s");  // Start at 30 seconds

        timerText.getStyleClass().add("text-black");
        timerValue.getStyleClass().add("text-black");

        timerText.setStyle("-fx-font-size: 24px");
        timerValue.setStyle("-fx-font-size: 22px");

        timerContainer.getChildren().addAll(timerText, timerValue);
        startCountdown(timerValue);
    }


    private void startCountdown(Text timerValue) {
        final int[] timeRemaining = {3000};  // 30 seconds * 100 to represent in hundredths of a second (3000 hundredths)
        final Timeline[] timelineHolder = new Timeline[1];  // Array to hold the timeline

        timelineHolder[0] = new Timeline(
                new KeyFrame(Duration.millis(10), event -> {  // Update every 10 milliseconds
                    timeRemaining[0]--;
                    int seconds = timeRemaining[0] / 100;
                    int milliseconds = timeRemaining[0] % 100;
                    timerValue.setText(String.format("%02d:%02d", seconds, milliseconds));  // Format as SS:MS
                    if (timeRemaining[0] <= 0) {
                        timelineHolder[0].stop();  // Stop the timeline using the reference from the array
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

    private void arrangeComponents() {
        Region spaceBetween = new Region();
        HBox.setHgrow(spaceBetween, Priority.ALWAYS);  // Space between money and turn containers

        Region spaceBetweenTurnAndTimer = new Region();
        HBox.setHgrow(spaceBetweenTurnAndTimer, Priority.ALWAYS);  // Space between turn and timer containers

        Region spaceBetweenTimerAndButton = new Region();
        HBox.setHgrow(spaceBetweenTimerAndButton, Priority.ALWAYS);  // Space between timer container and next button

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
    }

}
