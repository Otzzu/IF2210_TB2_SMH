package com.ooopppp.tubes_oop_2.Entity;

public class ItemAdded extends Item{

    private int addedWeight;
    private int addedAge;

    public ItemAdded(String name, int addedWeight, int addedAge, String image) {
        super(name, image);
        this.addedWeight = addedWeight;
        this.addedAge = addedAge;
    }
}
