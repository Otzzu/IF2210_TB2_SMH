package com.ooopppp.tubes_oop_2.Entity;

import java.util.Arrays;
import java.util.List;

public class Omnivore extends Animal{
    public Omnivore(String name, Product harvestResult, int weightToHarvest, String image) {
        super(name, harvestResult, weightToHarvest, image);
    }

    @Override
    public void eat(Product product) {
        weight += product.getAddedWeight();
    }
}
