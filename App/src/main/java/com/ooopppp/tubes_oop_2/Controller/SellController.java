package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.SellDialog;
import com.ooopppp.tubes_oop_2.Boundary.StoreView;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Product;
import javafx.stage.Stage;

public class SellController {
    private SellDialog sellDialog;
    private StoreView storeView;

    public SellController(SellDialog sellDialog, StoreView storeView){
        this.sellDialog = sellDialog;
        this.storeView = storeView;
    }

    public void handleCancelButton(){
        storeView.getBtnSound().stop();
        storeView.getBtnSound().play();
        ((Stage) sellDialog.getScene().getWindow()).close();
    }

    public void attachEventsOkButton() {
        storeView.getBtnSound().stop();
        storeView.getBtnSound().play();
        Product selectedProduct = sellDialog.getSelectedProduct();

        boolean removed = GameData.getGameData().getCurrentPlayer().getDeck().removeFromActiveDeck(selectedProduct);
        if (removed) {
            GameData.getGameData().getStore().addItems(selectedProduct);
            GameData.getGameData().getCurrentPlayer().setGulden(GameData.getGameData().getCurrentPlayer().getGulden() + selectedProduct.getPrice());
            storeView.getController().refresh();
        }
        ((Stage) sellDialog.getScene().getWindow()).close();
    }


}
