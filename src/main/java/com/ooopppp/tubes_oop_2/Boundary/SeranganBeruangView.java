package com.ooopppp.tubes_oop_2.Boundary;

import com.ooopppp.tubes_oop_2.Boundary.Component.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class SeranganBeruangView extends MainView {
    private static final int[] attackPositions = new int[]{5, 6, 7, 10, 11, 12};

    public SeranganBeruangView(Stage stage) {
        super(stage, true);
        highlightAttackAreas();
    }

    private boolean isAttackPosition(int position) {
        for (int pos : attackPositions) {
            if (position == pos) {
                return true;
            }
        }
        return false;
    }

    private void highlightAttackAreas() {
        int count = 0;
        for (int row = 0; row < board.getRowCount(); row++) {
            for (int col = 0; col < board.getColumnConstraints().size(); col++) {
                CardComponent card = (CardComponent) board.getChildren().get(row * board.getColumnConstraints().size() + col);
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
}
