package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.SellDialog;
import com.ooopppp.tubes_oop_2.Boundary.StoreView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class StoreController {
    private StoreView storeview;

    public StoreController(StoreView storeview){
        this.storeview = storeview;
    }

    public void loadSound(){
        URL btnSoundUrl = getClass().getResource("/com/ooopppp/tubes_oop_2/sound/button-click.mp3");
        if (btnSoundUrl == null) {
            System.out.println("Resource not found.");

            return;
        }

        Media media = new Media(btnSoundUrl.toExternalForm());
        storeview.setBtnSound(new MediaPlayer(media));
        storeview.getBtnSound().setVolume(50);
    }
    public void attachEventsJualButton() {
        storeview.getBtnSound().stop();
        storeview.getBtnSound().play();

        SellDialog selldialog = new SellDialog();
        selldialog.showDialog(storeview.getStage());

    }

    public void attachEventsBackButton() {
        storeview.getBtnSound().stop();
        storeview.getBtnSound().play();

        MainView mainview = new MainView(storeview.getStage());
        storeview.getStage().getScene().setRoot(mainview);


    }
}
