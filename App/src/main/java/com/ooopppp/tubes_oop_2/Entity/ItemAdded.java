package com.ooopppp.tubes_oop_2.Entity;

public class ItemAdded extends Item{

    private int addedWeight;
    private int addedAge;

    public ItemAdded(String name, int addedWeight, int addedAge, String image, boolean isForEnemy) {
        super(name, image, isForEnemy);
        this.addedWeight = addedWeight;
        this.addedAge = addedAge;
    }

    public int getAddedWeight() {
        return addedWeight;
    }

    public int getAddedAge() {
        return addedAge;
    }
}
