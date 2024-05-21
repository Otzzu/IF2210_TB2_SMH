package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.BuyDialog;
import com.ooopppp.tubes_oop_2.Boundary.StoreView;
import com.ooopppp.tubes_oop_2.Entity.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import  java.util.List;

public class BuyController {
    private BuyDialog buyDialog;
    private  StoreView storeView;

    public BuyController(BuyDialog buyDialog, StoreView storeView){
        this.buyDialog = buyDialog;
        this.storeView = storeView;
    }

    public void attachEventsBuyButton(){
        System.out.println("masuk buy");
        Player currentPlayer = GameData.getGameData().getCurrentPlayer();
        int currentActiveDeckSize = currentPlayer.getDeck().getActiveDeckCount();
        System.out.println(currentActiveDeckSize);
        if (currentActiveDeckSize < 6) {
            System.out.println(currentActiveDeckSize);
            List<Card> newInsert = new ArrayList<>();
            Store tempStore = GameData.getGameData().getStore();

            // Get the product name from the dialog
            String productName = buyDialog.getProduct().getName();

            // Get the product from the store
            Product selectedProduct = tempStore.takeItem(productName);

            if (selectedProduct != null && currentPlayer.getGulden() >= selectedProduct.getPrice() ) {
                newInsert.add(selectedProduct);
                currentPlayer.getDeck().moveToActiveDeck(newInsert);
                currentPlayer.setGulden(currentPlayer.getGulden() - selectedProduct.getPrice());
            }else {
                tempStore.addItems(selectedProduct);
            }
        }

        ((Stage) buyDialog.getScene().getWindow()).close();
        storeView.refresh();
    }



}
