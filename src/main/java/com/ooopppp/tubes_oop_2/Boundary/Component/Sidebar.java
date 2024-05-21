package com.ooopppp.tubes_oop_2.Boundary.Component;


import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Controller.SidebarController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {
    private Button buttonLadang;
    private Button buttonToko;
    private Button buttonSaveState;
    private Button buttonLoadState;
    private Button buttonLoadPlugin;
    private SidebarController controller;

    public Sidebar(MainView parent){
        super();
        controller = new SidebarController(this, parent);

        buttonLadang = new Button("Ladang Lawan");
        buttonToko = new Button("Toko");
        buttonSaveState = new Button("Save State");
        buttonLoadState = new Button("Load State");
        buttonLoadPlugin = new Button("Load Plugin");

        buttonLadang.getStyleClass().add("button-brown");
        buttonToko.getStyleClass().add("button-brown");
        buttonSaveState.getStyleClass().add("button-brown");
        buttonLoadState.getStyleClass().add("button-brown");
        buttonLoadPlugin.getStyleClass().add("button-brown");

        buttonLadang.setPrefWidth(250);
        buttonToko.setPrefWidth(250);
        buttonSaveState.setPrefWidth(250);
        buttonLoadState.setPrefWidth(250);
        buttonLoadPlugin.setPrefWidth(250);

        this.setAlignment(Pos.CENTER_RIGHT);
        this.setSpacing(30);
        this.setPrefHeight(492);
        this.setPrefWidth(280);
        this.setMaxHeight(492);
        this.getChildren().addAll(buttonLadang, buttonToko, buttonSaveState, buttonLoadState, buttonLoadPlugin);

        controller.attachEventsLadangButton();
        controller.attachEventsTokoButton();
    }

    public Button getButtonLadang() {
        return buttonLadang;
    }

    public Button getButtonToko() {
        return buttonToko;
    }

    public Button getButtonSaveState() {
        return buttonSaveState;
    }

    public Button getButtonLoadState() {
        return buttonLoadState;
    }

    public Button getButtonLoadPlugin() {
        return buttonLoadPlugin;
    }
}
