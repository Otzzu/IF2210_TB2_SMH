package com.ooopppp.tubes_oop_2.Entity;

import com.ooopppp.tubes_oop_2.Helper.Observer;

import java.util.*;

public class Player {

    private String name;
    private Deck deck;
    private Farm farm;
    private int gulden;

    public Player(String name){
        farm = new Farm(4, 5);
        deck = new Deck();
        gulden = 0;
        this.name = name;
        CardFactory factory = new CardFactory();

        deck.generateDeck(40);
    }

    public String getName() {
        return name;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Deck getDeck() {
        return deck;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        return name.equals(player.name);
    }

    public void moveCardToFarm(int idCard, int row, int col){
        Optional<Card> chooseCard = Arrays.stream(deck.getActiveDeck()).filter(Objects::nonNull).filter(c -> c.getId() == idCard).findFirst();

        if (chooseCard.isEmpty()){
            System.out.println("Empty");
            return;
        }

        if (!(chooseCard.get() instanceof LivingBeing)){
            System.out.println("Bukan makhluk");
            return;
        }

        if (chooseCard.get() instanceof Plant){
            farm.addPlantObserver((Observer) chooseCard.get());
        }

        farm.set(row, col, (LivingBeing) chooseCard.get());
        deck.removeFromActiveDeck(chooseCard.get());
        farm.printBoard();
    }

    public void giveEat(Product product, Animal animal){
        animal.eat(product);
        deck.removeFromActiveDeck(product);
    }

    public void harvestLivingBeing(LivingBeing livingBeing) {
        if (deck.getActiveDeckCount() == 6){
            throw new IllegalStateException("Active deck full!!");
        }
        if (livingBeing instanceof Plant) {
            Plant plant = (Plant) livingBeing;
            System.out.println("curr age: " + plant.getAge());
            System.out.println("harvest age: " + plant.getAgeToHarvest());
            if (plant.getAge() >= plant.getAgeToHarvest()) {
                System.out.println("ping");
                Product product = plant.harvest();
                deck.moveToActiveDeck(Collections.singletonList(product));
                farm.remove(plant);
            } else {
                throw new IllegalStateException("Tanaman belum siap dipanen");
            }
        } else if (livingBeing instanceof Animal) {
            Animal animal = (Animal) livingBeing;
            if (animal.getWeight() >= animal.getWeightToHarvest()) {
                Product product = animal.harvest();
                deck.moveToActiveDeck(Collections.singletonList(product));
                farm.remove(animal);
            } else {
                throw new IllegalStateException("Hewan belum siap dipanen");
            }
        }
    }

    public void giveItemToOwn(Item item, LivingBeing livingBeing){
        if (item.isForEnemy()){
            throw new IllegalArgumentException("This item cannot be used here");
        }
        if (item.getName().equals("Instant Harvest")){
            if (livingBeing instanceof Plant) {
                Plant plant = (Plant) livingBeing;
                plant.setAge(plant.getAgeToHarvest());
            }
            else if (livingBeing instanceof Animal) {
                Animal animal = (Animal) livingBeing;
                animal.setWeight(animal.getWeightToHarvest());
            }
            deck.removeFromActiveDeck(item);

            try {
                harvestLivingBeing(livingBeing);
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                throw e;
            }
            return;
        }
        giveItemToLivingBeing(item, livingBeing);
    }

    public void giveItemToEnemy(Item item, LivingBeing livingBeing, Player enemy){
        if (!item.isForEnemy()){
            throw new IllegalArgumentException("This item cannot be used here");
        }

        if (livingBeing.haveProtect()){
            throw new IllegalArgumentException("This card have protection");
        }

        if (item.getName().equals("Destroy")){
            enemy.getFarm().remove(livingBeing);
            if (livingBeing instanceof Plant){
                enemy.getFarm().removePlantObserver((Plant) livingBeing);
            }
            deck.removeFromActiveDeck(item);
            return;
        }

        giveItemToLivingBeing(item, livingBeing);
    }

    private void giveItemToLivingBeing(Item item, LivingBeing livingBeing){
        livingBeing.useItem(item);
        deck.removeFromActiveDeck(item);
    }

    public int getGulden() {
        return gulden;
    }

    public void setGulden(int gulden) {
        this.gulden = gulden;
    }
}
