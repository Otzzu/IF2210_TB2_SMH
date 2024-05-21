package com.ooopppp.tubes_oop_2.Entity;

import com.ooopppp.tubes_oop_2.Helper.Observer;

public class Plant extends LivingBeing implements Observer {
    private int age;
    private int ageToHarvest;
    public Plant(String name, Product harvestResult, int ageToHarvest, String image) {
        super(name, harvestResult, image);
        this.ageToHarvest = ageToHarvest;
    }

    public void ripped(){
        this.name = this.harvestResult.name;
        this.image = this.harvestResult.image;
    }

    public int getAge() {
        return age;
    }

    public void addAge(int addedAge){
        age += addedAge;
        if (age >= ageToHarvest){
            ripped();
        }
    }

    @Override
    public void update(String data) {
        addAge(1);
    }
}
