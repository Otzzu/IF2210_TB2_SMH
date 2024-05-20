package com.ooopppp.tubes_oop_2.Entity;

public class Herbivore extends Animal{

    public Herbivore(String name, Product harvestResult, int weightToHarvest, String image) {
        super(name, harvestResult, weightToHarvest, image);
    }

    @Override
    public void eat(int addedWeight) {

    }
}
