package com.ooopppp.tubes_oop_2.Entity;

public class Item extends Card{
    private boolean isForEnemy;
    public Item(String name, String image, boolean isForEnemy) {
        super(name, image);
        this.isForEnemy = isForEnemy;
    }

    public boolean isForEnemy() {
        return isForEnemy;
    }
}
