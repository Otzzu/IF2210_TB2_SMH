package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.TanamanPopup;
import com.ooopppp.tubes_oop_2.Boundary.Component.HewanPopUp;
import com.ooopppp.tubes_oop_2.Boundary.Component.MessageDialog;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Entity.Player;
import com.ooopppp.tubes_oop_2.Entity.Plant;
import com.ooopppp.tubes_oop_2.Entity.Animal;
import com.ooopppp.tubes_oop_2.Entity.LivingBeing;
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
        TanamanPopup popup = new TanamanPopup(stage, plant.getName(), plant.getAge(), plant.getFormattedActiveItems(), plant.getImage(), plant, parent);
        popup.setOnHarvest(livingBeing -> {
            try {
                player.harvestLivingBeing(livingBeing);
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
        HewanPopUp popup = new HewanPopUp(stage, animal.getName(), animal.getWeight(), animal.getFormattedActiveItems(), animal.getImage(), animal, parent);

        popup.setOnHarvest(livingBeing -> {
            try {
                player.harvestLivingBeing(livingBeing);
                boardController.handleHarvestSuccess(livingBeing);
                stage.close();
            } catch (IllegalStateException e) {
                MessageDialog.showErrorDialog(stage, e.getMessage());
            }
        });
        popup.show();
    }
}
