package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.BuyDialog;
import com.ooopppp.tubes_oop_2.Boundary.Component.ItemComponent;
import com.ooopppp.tubes_oop_2.Boundary.Component.SellDialog;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.StoreView;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Product;
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
import java.util.List;
import java.util.Map;

public class StoreController {
    private StoreView storeview;
    private Stage stage;


    public StoreController(StoreView storeview, Stage stage){
        this.storeview = storeview;
        this.stage = stage;
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
        selldialog.setController(new SellController(selldialog,storeview));
        selldialog.showDialog(stage);


    }

    public void attachEventsBackButton() {
        storeview.getBtnSound().stop();
        storeview.getBtnSound().play();

        MainView mainview = new MainView(stage);
        stage.getScene().setRoot(mainview);


    }

    public void populateGrid(){
        storeview.getGrid().getChildren().clear();
        int col = 0;
        int row = 0;
        for (Map.Entry<String, List<Product>> entry : storeview.getItems().entrySet()) {
            String productName = entry.getKey();
            List<Product> productList = entry.getValue();
            if (productList.isEmpty()) continue;

            // Assuming all products with the same name have the same price and added weight
            Product sampleProduct = productList.get(0);
            int quantity = productList.size();

            ItemComponent item = new ItemComponent(sampleProduct, quantity);
            storeview.getGrid().add(item, col, row);
            col++;
            if (col == 3) { // 3 columns per row
                col = 0;
                row++;
            }
            item.setOnMouseClicked(e -> {
                storeview.getBtnSound().stop();
                storeview.getBtnSound().play();
                BuyDialog buyDialog = new BuyDialog(sampleProduct, quantity);
                buyDialog.setController(new BuyController(buyDialog, storeview, stage));
                buyDialog.showDialog(stage);
            });
        }
    }

    public void refresh() {
        storeview.setItems(GameData.getGameData().getStore().getItemSells());
        populateGrid();
    }
}
