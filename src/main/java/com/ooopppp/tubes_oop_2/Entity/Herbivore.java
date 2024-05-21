package com.ooopppp.tubes_oop_2.Entity;

import java.util.Arrays;
import java.util.List;

public class Herbivore extends Animal{

    public Herbivore(String name, Product harvestResult, int weightToHarvest, String image) {
        super(name, harvestResult, weightToHarvest, image);
    }

    public void eat(Product product) {
        List<String> canEat = Arrays.asList(
                "Jagung", "Labu", "Stroberi"
        );

        if (!canEat.contains(product.getName())){
            throw new IllegalArgumentException("Herbivore cannot eat that!!!");
        }

        weight += product.getAddedWeight();
    }
}
