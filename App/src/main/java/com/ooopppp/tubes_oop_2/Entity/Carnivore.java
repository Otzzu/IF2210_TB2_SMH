package com.ooopppp.tubes_oop_2.Entity;

import java.util.Arrays;
import java.util.List;

public class Carnivore extends Animal{
    public Carnivore(String name, Product harvestResult, int weightToHarvest, String image) {
        super(name, harvestResult, weightToHarvest, image);
    }

    public Carnivore() {
        super();
    }

    @Override
    public void eat(Product product) {
        List<String> canEat = Arrays.asList(
                "Sirip Hiu", "Susu", "Daging Domba", "Daging Kuda", "Telur",
                "Daging Beruang"
        );

        if (!canEat.contains(product.getName())){
            throw new IllegalArgumentException("Carnivore cannot eat that!!!");
        }

        weight += product.getAddedWeight();
    }
}
