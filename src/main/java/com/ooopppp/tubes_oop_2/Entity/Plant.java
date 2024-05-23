package com.ooopppp.tubes_oop_2.Entity;

import com.ooopppp.tubes_oop_2.Helper.Observer;

public class Plant extends LivingBeing implements Observer {
    private int age;
    private int ageToHarvest;

    public Plant(String name, Product harvestResult, int ageToHarvest, String image) {
        super(name, harvestResult, image);
        this.ageToHarvest = ageToHarvest;
    }

    public void ripped() {
        this.name = this.harvestResult.getName();
        this.image = this.harvestResult.getImage();
    }

    public int getAge() {
        return age;
    }

    public void addAge(int addedAge) {
        age += addedAge;
        if (age >= ageToHarvest) {
            ripped();
        } else if (age < 0) {
            age = 0;
        }
    }

    public int getAgeToHarvest() {
        return ageToHarvest;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Age: " + age);
    }

    @Override
    public void useItem(Item item) {
        super.useItem(item);
        if (item instanceof ItemAdded itemAdded) {
            addAge(itemAdded.getAddedAge());
        }
    }

    @Override
    public void update(String data) {
        addAge(1);
    }

    public boolean isReadyToHarvest() {
        return age >= ageToHarvest;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
