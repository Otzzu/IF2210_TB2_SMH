package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.TanamanPopup;
import com.ooopppp.tubes_oop_2.Boundary.Component.HewanPopUp;
import com.ooopppp.tubes_oop_2.Boundary.Component.MessageDialog;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.*;
import com.ooopppp.tubes_oop_2.Main;
import javafx.stage.Stage;

public class PopupController {
    private Player player;
    private BoardController boardController;
    private MainView parent;

    public PopupController(Player player, BoardController boardController, MainView parent) {
        this.player = player;
        this.boardController = boardController;
        this.parent = parent;
    }

    public void showTanamanPopup(Plant plant) {
        Stage stage = new Stage();
        System.out.println( "ggg: " + plant.getName());
        TanamanPopup popup;
        if (!plant.isReadyToHarvest()){
            popup = new TanamanPopup(stage, plant.getName(), plant.getAge(), plant.getAgeToHarvest(), plant.getFormattedActiveItems(), plant.getImage(), plant, parent);
        } else {
            popup = new TanamanPopup(stage, plant.harvest().getName(), plant.getAge(), plant.getAgeToHarvest(), plant.getFormattedActiveItems(), plant.harvest().getImage(), plant, parent);

        }
        popup.setOnHarvest(livingBeing -> {
            try {
                GameData.getGameData().getCurrentPlayer().harvestLivingBeing(livingBeing);
                boardController.handleHarvestSuccess(livingBeing);
                stage.close();
            } catch (IllegalStateException e) {
                MessageDialog.showErrorDialog(stage, e.getMessage());
            }
        });
        popup.show();
    }

    public void showHewanPopup(Animal animal) {
        Stage stage = new Stage();
        HewanPopUp popup = new HewanPopUp(stage, animal.getName(), animal.getWeight(), animal.getWeightToHarvest(), animal.getFormattedActiveItems(), animal.getImage(), animal, parent);

        popup.setOnHarvest(livingBeing -> {
            try {
                GameData.getGameData().getCurrentPlayer().harvestLivingBeing(livingBeing);
                boardController.handleHarvestSuccess(livingBeing);
                stage.close();
            } catch (IllegalStateException e) {
                MessageDialog.showErrorDialog(stage, e.getMessage());
            }
        });
        popup.show();
    }
}
