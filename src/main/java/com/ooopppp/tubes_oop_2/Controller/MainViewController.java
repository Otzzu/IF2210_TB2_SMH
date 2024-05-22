package com.ooopppp.tubes_oop_2.Controller;

import java.net.URL;

import com.ooopppp.tubes_oop_2.Boundary.Component.CardComponent;
import com.ooopppp.tubes_oop_2.Boundary.MainView;

import com.ooopppp.tubes_oop_2.Entity.Farm;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class MainViewController {

    private MainView mainView;
    private List<int[]> attackPositions;
    public MainViewController(MainView mainView) {
        this.mainView = mainView;
        attackPositions = new ArrayList<>();
    }

    public void loadSound(){
        URL dangerSoundUrl = getClass().getResource("/com/ooopppp/tubes_oop_2/sound/danger.mp3");
        URL btnSoundUrl = getClass().getResource("/com/ooopppp/tubes_oop_2/sound/button-click.mp3");
        if (btnSoundUrl == null) {
            System.out.println("Resource not found.");

            return;
        }
        if(dangerSoundUrl == null){
            System.out.println("Resource not found.");

            return;
        }


        Media media = new Media(btnSoundUrl.toExternalForm());
        mainView.setBtnSound(new MediaPlayer(media));
        mainView.getBtnSound().setVolume(50);

        Media media1 = new Media(dangerSoundUrl.toExternalForm());
        mainView.setDangerSound(new MediaPlayer(media1));
        mainView.getDangerSound().setVolume(50);
    }

//    public void highlightAttackAreas() {
//        int count = 0;
//        for (int row = 0; row < mainView.getBoard().getRowCount(); row++) {
//            for (int col = 0; col < mainView.getBoard().getColumnConstraints().size(); col++) {
//                CardComponent card = (CardComponent) mainView.getBoard().getChildren().get(row * mainView.getBoard().getColumnConstraints().size() + col);
//                if (isAttackPosition(count)) {
//                    card.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
//                    card.setStyle("-fx-border-color: red; -fx-border-width: 4;");
//                } else {
//                    card.setBackground(Background.EMPTY);
//                    card.setStyle("");
//                }
//                count++;
//            }
//        }
//    }
//
//    private boolean isAttackPosition(int position) {
//        int[] attackPositions = new int[]{5, 6, 7, 10, 11, 12};
//
//        for (int pos : attackPositions) {
//            if (position == pos) {
//                return true;
//            }
//        }
//        return false;
//    }

    public void highlightAttackAreas() {
        attackPositions.clear(); // Clear previous positions
        mainView.getDangerSound().stop();
        mainView.getDangerSound().play();
        int rows = mainView.getBoard().getRowCount();
        int cols = mainView.getBoard().getColumnConstraints().size();

        clearPreviousHighlights(rows, cols);

        Random random = new Random();
        int attackAreaCount = random.nextInt(6) + 1;

        // Determine the configuration
        int[] shape = determineShape(attackAreaCount);

        // Determine the location
        int[][] positions = determineLocation(shape, rows, cols);

        // Highlight the attack areas and store the positions
        for (int[] pos : positions) {
            attackPositions.add(new int[]{pos[0], pos[1]});
        }

        // Render attack areas
        renderAttack();
    }

    public void renderAttack() {
        int cols = mainView.getBoard().getColumnConstraints().size();
        for (int[] pos : attackPositions) {
            int row = pos[0];
            int col = pos[1];
            int index = row * cols + col; // Calculate the index based on row and column
            CardComponent card = (CardComponent) mainView.getBoard().getChildren().get(index);
            card.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            card.setStyle("-fx-border-color: red; -fx-border-width: 4;");
        }
    }

    private void clearPreviousHighlights(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int index = row * cols + col;
                CardComponent card = (CardComponent) mainView.getBoard().getChildren().get(index);
                card.setBackground(Background.EMPTY);
                card.setStyle("");
            }
        }
    }

    private int[] determineShape(int attackAreaCount) {
        Random random = new Random();
        switch (attackAreaCount) {
            case 1:
                return new int[]{1, 1};
            case 2:
                return random.nextBoolean() ? new int[]{1, 2} : new int[]{2, 1};
            case 3:
                return new int[]{1, 3}; // 1x3 shape
            case 4:
                return random.nextBoolean() ? new int[]{1, 4} : new int[]{2, 2};
            case 5:
                return new int[]{1, 5}; // 1x5 shape
            case 6:
                return random.nextBoolean() ? new int[]{3,2} : new int[]{2, 3}; // 1x6 or 2x3 shape
            default:
                return new int[]{1, 1};
        }
    }

    private int[][] determineLocation(int[] shape, int rows, int cols) {
        Random random = new Random();
        int shapeRows = shape[0];
        int shapeCols = shape[1];

        int startRow = random.nextInt(rows - shapeRows + 1);
        int startCol = random.nextInt(cols - shapeCols + 1);

        int[][] positions = new int[shapeRows * shapeCols][2];
        int index = 0;
        for (int i = 0; i < shapeRows; i++) {
            for (int j = 0; j < shapeCols; j++) {
                positions[index++] = new int[]{startRow + i, startCol + j};
            }
        }

        return positions;
    }

    public List<int[]> getAttackPositions() {
        return attackPositions;
    }

    public void clearAttackAreas() {
        Farm farm = GameData.getGameData().getCurrentPlayer().getFarm();
        for (int[] pos : attackPositions) {
            int row = pos[0];
            int col = pos[1];
            farm.take(row, col);
        }
        attackPositions.clear();
        mainView.getBoard().getController().populateGrid(false); // Refresh the grid
    }



public void setUpBearAttack(){
        mainView.getHeader().initializeComponents(true);
        highlightAttackAreas();
    }
}
