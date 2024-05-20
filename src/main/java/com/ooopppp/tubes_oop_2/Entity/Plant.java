package com.ooopppp.tubes_oop_2.Entity;

public class Plant extends LivingBeing{
    private int age;
    private int ageToHarvest;
    public Plant(String name, Product harvestResult, int ageToHarvest, String image) {
        super(name, harvestResult, image);
        this.ageToHarvest = ageToHarvest;
    }

    public void seed(){
        this.name = this.harvestResult.name;
        this.image = this.harvestResult.image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
