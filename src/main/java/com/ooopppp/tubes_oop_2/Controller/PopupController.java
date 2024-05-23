package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.TanamanPopup;
import com.ooopppp.tubes_oop_2.Boundary.Component.HewanPopUp;
import com.ooopppp.tubes_oop_2.Boundary.Component.MessageDialog;
import com.ooopppp.tubes_oop_2.Entity.Player;
import com.ooopppp.tubes_oop_2.Entity.Plant;
import com.ooopppp.tubes_oop_2.Entity.Animal;
import com.ooopppp.tubes_oop_2.Entity.LivingBeing;
import javafx.stage.Stage;

public class PopupController {
    private Player player;
    private BoardController boardController;

    public PopupController(Player player, BoardController boardController) {
        this.player = player;
        this.boardController = boardController;
    }

    public void showTanamanPopup(Stage stage, String name, int age, String activeItem, String imagePath) {
        TanamanPopup popup = new TanamanPopup(stage, name, age, activeItem, imagePath);
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

    public void showHewanPopup(Stage stage, String name, int weight, String activeItem, String imagePath, Animal animal) {
        HewanPopUp popup = new HewanPopUp(stage, name, weight, activeItem, imagePath, animal);
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
