package com.ooopppp.tubes_oop_2.Entity;

import java.util.Map;

public class LivingBeing extends Card{
    protected Map<Item, Integer> activeItem;
    protected Product harvestResult;
    public LivingBeing(String name, Product harvestResult, String image){
        super(name, image);
        this.harvestResult = harvestResult;
    }

    public Product harvest(){
        return harvestResult;
    }
}
