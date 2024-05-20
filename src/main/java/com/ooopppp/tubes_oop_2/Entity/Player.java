package com.ooopppp.tubes_oop_2.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {

    private Farm farm;
    private List<Card> deck;
    private List<Card> activeDeck;
    public Player(){
        farm = new Farm(4, 5);
        deck = new ArrayList<>(40);
        activeDeck = new ArrayList<>(6);

        activeDeck.add(new LivingBeing("deck"));
        activeDeck.add(new LivingBeing("deck2"));
        activeDeck.add(new LivingBeing("deck3"));

        farm.set(0, 0, new LivingBeing("board"));
        farm.set(0, 1, new LivingBeing("board1"));
        farm.set(0, 2, new LivingBeing("board2"));
        farm.set(1, 1, new LivingBeing("board3"));

    }

    public Farm getFarm() {
        return farm;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getActiveDeck() {
        return activeDeck;
    }

    public void moveCardInFarm(int idCard, int targetI, int targetJ){
        System.out.println("move " + idCard);
        LivingBeing data = null;
        for (int i = 0 ; i < this.getFarm().getRow(); i++){
            for (int j = 0; j < this.getFarm().getCol(); j++){
                if (this.getFarm().get(i, j) != null && this.getFarm().get(i, j).getId() == idCard ){
                    data = this.getFarm().take(i, j);
                    break;
                }
            }
        }

        if (data != null){
            this.getFarm().set(targetI, targetJ, data);
        }
        this.getFarm().printBoard();

    }
    public void moveCardToFarm(int idCard, int row, int col){
        Optional<Card> chooseCard = activeDeck.stream().filter(c -> c.getId() == idCard).findFirst();

        if (chooseCard.isEmpty()){
            return;
        }

        if (!(chooseCard.get() instanceof LivingBeing)){
            return;
        }

        System.out.println("halo");
        farm.set(row, col, (LivingBeing) chooseCard.get());
        activeDeck.remove(chooseCard.get());
        farm.printBoard();

    }
}
