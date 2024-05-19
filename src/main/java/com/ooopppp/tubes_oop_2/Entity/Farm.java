package com.ooopppp.tubes_oop_2.Entity;

public class Farm {

    private LivingBeing[][] grid;
    public Farm(int row, int col){
        grid = new LivingBeing[row][col];

    }

    public LivingBeing[][] getGrid() {
        return grid;
    }

    public void set(int row, int col, LivingBeing item) {
        grid[row][col] = item;
    }

    public LivingBeing get(int row, int col){
        return grid[row][col];
    }
}
