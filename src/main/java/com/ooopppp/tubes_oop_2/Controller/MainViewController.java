package com.ooopppp.tubes_oop_2.Controller;

import java.net.URL;

import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import com.ooopppp.tubes_oop_2.Boundary.MainView;

import com.ooopppp.tubes_oop_2.Boundary.StoreView;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainViewController {

    private MainView mainView;

    public MainViewController(MainView mainView) {
        this.mainView = mainView;
    }

    public void loadSound(){
        URL btnSoundUrl = getClass().getResource("/com/ooopppp/tubes_oop_2/sound/button-click.mp3");
        if (btnSoundUrl == null) {
            System.out.println("Resource not found.");

            return;
        }

        Media media = new Media(btnSoundUrl.toExternalForm());
        mainView.setBtnSound(new MediaPlayer(media));
        mainView.getBtnSound().setVolume(50);
    }

    public void highlightAttackAreas() {
        int count = 0;
        for (int row = 0; row < mainView.getBoard().getRowCount(); row++) {
            for (int col = 0; col < mainView.getBoard().getColumnConstraints().size(); col++) {
                CardComponent card = (CardComponent) mainView.getBoard().getChildren().get(row * mainView.getBoard().getColumnConstraints().size() + col);
                if (isAttackPosition(count)) {
                    card.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    card.setStyle("-fx-border-color: red; -fx-border-width: 4;");
                } else {
                    card.setBackground(Background.EMPTY);
                    card.setStyle("");
                }
                count++;
            }
        }
    }

    private boolean isAttackPosition(int position) {
        int[] attackPositions = new int[]{5, 6, 7, 10, 11, 12};

        for (int pos : attackPositions) {
            if (position == pos) {
                return true;
            }
        }
        return false;
    }

    public void setUpBearAttack(){
        mainView.getHeader().initializeComponents(true);
        highlightAttackAreas();
    }

    public void switchToStoreView(Stage stage) {
        Parent storeView = new StoreView(stage);
        stage.getScene().setRoot(storeView);
    }
}
