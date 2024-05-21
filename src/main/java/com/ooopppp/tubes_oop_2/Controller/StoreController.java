package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.SellDialog;
import com.ooopppp.tubes_oop_2.Boundary.StoreView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StoreController {
    private StoreView storeview;

    public StoreController(StoreView storeview){
        this.storeview = storeview;
    }

    public void attachEventsJualButton() {
        storeview.getButtonJual().setOnAction(e -> {
            SellDialog selldialog = new SellDialog();
            selldialog.showDialog(storeview.getStage());
        });
    }
}
