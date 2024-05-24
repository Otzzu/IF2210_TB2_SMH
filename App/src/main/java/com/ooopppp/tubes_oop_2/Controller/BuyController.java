package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.BuyDialog;
import com.ooopppp.tubes_oop_2.Boundary.Component.MessageDialog;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.StoreView;
import com.ooopppp.tubes_oop_2.Entity.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import  java.util.List;

public class BuyController {
    private BuyDialog buyDialog;
    private  StoreView storeView;
    private Stage stage;

    public BuyController(BuyDialog buyDialog, StoreView storeView, Stage stage){
        this.buyDialog = buyDialog;
        this.storeView = storeView;
        this.stage = stage;
    }

    public void handleCancelButton(){
        storeView.getBtnSound().stop();
        storeView.getBtnSound().play();
        ((Stage) buyDialog.getScene().getWindow()).close();
    }

    public void attachEventsBuyButton()  {
        storeView.getBtnSound().stop();
        storeView.getBtnSound().play();
        System.out.println("masuk buy");
        Player currentPlayer = GameData.getGameData().getCurrentPlayer();
        int currentActiveDeckSize = currentPlayer.getDeck().getActiveDeckCount();
        System.out.println(currentActiveDeckSize);
        if (currentActiveDeckSize < 6) {
            System.out.println(currentActiveDeckSize);
            List<Card> newInsert = new ArrayList<>();
            // Get the product name from the dialog
            String productName = buyDialog.getProduct().getName();


            // Get the product from the store
            Product selectedProduct =  GameData.getGameData().getStore().takeItem(productName);

            if (selectedProduct == null){
                MessageDialog.showInfoDialog(stage, "Item not sell in store");
                ((Stage) buyDialog.getScene().getWindow()).close();
                storeView.getController().refresh();
                return;
            }
            if (currentPlayer.getGulden() >= selectedProduct.getPrice() ) {
                newInsert.add(selectedProduct);
                currentPlayer.getDeck().moveToActiveDeck(newInsert);
                currentPlayer.setGulden(currentPlayer.getGulden() - selectedProduct.getPrice());
            }else {
                MessageDialog.showInfoDialog(stage, "Money not enough");
                GameData.getGameData().getStore().addItems(selectedProduct);
            }
        } else {
            MessageDialog.showInfoDialog(stage, "Active deck full");

        }

        ((Stage) buyDialog.getScene().getWindow()).close();
        storeView.getController().refresh();
    }



}
