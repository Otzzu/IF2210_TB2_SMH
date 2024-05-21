package com.ooopppp.tubes_oop_2.Entity;

import com.ooopppp.tubes_oop_2.Helper.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Player {

    private String name;
    private Deck deck;
    private Farm farm;
//    private List<Card> activeDeck;
    public Player(String name){
        farm = new Farm(4, 5);
        deck = new Deck();
//        activeDeck = new ArrayList<>(6);
        this.name = name;
        CardFactory factory = new CardFactory();
        deck.getActiveDeck()[0] = (factory.createCard("AYAM"));
        deck.getActiveDeck()[1] = (factory.createCard("DOMBA"));
        deck.getActiveDeck()[2] = (factory.createCard("ACCELERATE"));
        deck.getActiveDeck()[3] = (factory.createCard("BIJI_LABU"));
        deck.getActiveDeck()[4] = (factory.createCard("SUSU"));

        farm.set(0, 1, (LivingBeing) factory.createCard("DOMBA"));
        farm.set(0, 2, (LivingBeing) factory.createCard("HIU_DARAT"));
        farm.set(1, 1, (LivingBeing) factory.createCard("KUDA"));

        deck.generateDeck(40);
    }

    public String getName() {
        return name;
    }

    public Farm getFarm() {
        return farm;
    }

    public Deck getDeck() {
        return deck;
    }

//    public List<Card> getActiveDeck() {
//        return activeDeck;
//    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        return name.equals(player.name);
    }

    public void moveCardToFarm(int idCard, int row, int col){
        Optional<Card> chooseCard = Arrays.stream(deck.getActiveDeck()).filter(c -> c.getId() == idCard).findFirst();

        if (chooseCard.isEmpty()){
            return;
        }

        if (!(chooseCard.get() instanceof LivingBeing)){
            return;
        }

        if (chooseCard.get() instanceof Plant){
            farm.addPlantObserver((Observer) chooseCard.get());
        }


        farm.set(row, col, (LivingBeing) chooseCard.get());

        for (int i = 0 ; i < 6; i++){
            if (deck.getActiveDeck()[i] != null && deck.getActiveDeck()[i].equals(chooseCard.get())){
                deck.getActiveDeck()[i] = null;
            }
        }
        farm.printBoard();

    }

    public void giveEat(Product product, Animal animal){
        animal.eat(product);

        for (int i = 0 ; i < 6; i++){
            if (deck.getActiveDeck()[i].equals(product)){
                deck.getActiveDeck()[i] = null;
            }
        }
    }
}
