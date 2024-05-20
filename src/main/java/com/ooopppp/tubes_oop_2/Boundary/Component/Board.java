package com.ooopppp.tubes_oop_2.Boundary.Component;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Controller.BoardController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Board extends GridPane {

    //array of card data
    private final int row;
    private final int col;
    private BoardController controller;

    public Board(int col, int row, MainView parent){
        super();
        this.controller = new BoardController(this, parent);
        this.row = row;
        this.col = col;
        this.setPrefHeight(492);
        this.setMaxHeight(492);
        this.setPrefWidth(530);
        this.setHgap(20);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER_LEFT);
        setupGrid();
        controller.populateGrid();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
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



}
