package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.AttackPopup;
import com.ooopppp.tubes_oop_2.Boundary.Component.MessageDialog;
import com.ooopppp.tubes_oop_2.Boundary.Component.Header;
import com.ooopppp.tubes_oop_2.Boundary.Component.WinDialog;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.ShuffleView;
import com.ooopppp.tubes_oop_2.Entity.Card;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Player;

import java.util.Random;
import java.util.List;

public class HeaderController {

    private Header header;
    private MainView parent;

    public HeaderController(Header header, MainView parent) {
        this.header = header;
        this.parent = parent;
    }

    public void attachEventsButtonNext(){
        header.getButtonNext().setOnAction(e -> {
            if(header.isTimerRunning()){
                return;
            }


            parent.getBtnSound().stop();
            parent.getBtnSound().play();
            GameData gameData = GameData.getGameData();
            gameData.setCurrentPlayer(gameData.getAnotherPlayer());
            gameData.getCurrentPlayer().getFarm().notifyPlant();
            gameData.getAnotherPlayer().getFarm().notifyPlant();
            gameData.addTurn();
            header.getTurnNumber().setText(String.valueOf(gameData.getTurn()));
            parent.setSelectedCardDeck(null);
            parent.getBoard().getController().populateGrid(false);
            parent.getDeckContainer().getController().renderDeck();
            changePlayerTextColor();
            if(gameData.getTurn() == 20){
               Player[] players = gameData.getPlayers();
               int winnedId = 0;
               int maxGulden = 0;
               for (int i = 0; i < 2; i++){
                   if(players[i].getGulden() > maxGulden){
                       maxGulden = players[i].getGulden();
                       winnedId = i;
                   }
               }
               WinDialog winDialog = new WinDialog(parent.getStage(), players[winnedId].getName());
               winDialog.showDialog();
               return;

            }
            List<Card> cards = GameData.getGameData().getCurrentPlayer().getDeck().shuffleCard();
//            cards.add(new CardComponent(new Card("Beruang", "/bear.png"), false));
//            cards.add(new CardComponent(new Card("Beruang", "/bear.png"), false));
//            cards.add(new CardComponent(new Card("Beruang", "/bear.png"), false));
//            cards.add(new CardComponent(new Card("Beruang", "/bear.png"), false));
            gameData.getCurrentPlayer().getDeck().shuffleCard();

            ShuffleView.showView(cards, parent); // This will block input to other windows until closed
            Random random = new Random();
            int randomNumber = random.nextInt(4); // Will generate either 0 or 1

            if (randomNumber == 0) {

                header.initializeComponents(true);
                parent.getController().highlightAttackAreas();
                AttackPopup.showView(parent);
            }else{
                header.initializeComponents(false);
            }
        });
    }

    public void changePlayerTextColor(){
        if (GameData.getGameData().getTurn() % 2 == 0){
            header.getPlayer2Text().getStyleClass().remove("text-white");
            header.getPlayer2Text().getStyleClass().add("text-green");
            header.getPlayer1Text().getStyleClass().remove("text-green");
            header.getPlayer1Text().getStyleClass().add("text-white");
        } else {
            header.getPlayer1Text().getStyleClass().remove("text-white");
            header.getPlayer1Text().getStyleClass().add("text-green");
            header.getPlayer2Text().getStyleClass().remove("text-green");
            header.getPlayer2Text().getStyleClass().add("text-white");

        }
    }

}
