package com.ooopppp.tubes_oop_2.Entity;

import com.ooopppp.tubes_oop_2.Helper.Observer;

import java.util.ArrayList;
import java.util.List;

public class Farm {

    private LivingBeing[][] grid;
    private List<Observer> plantObserver;
    private int row;
    private int col;
    public Farm(int row, int col){
        grid = new LivingBeing[row][col];
        plantObserver = new ArrayList<>(20);
        this.row = row;
        this.col = col;
    }

    public Farm(){

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public LivingBeing[][] getGrid() {
        return grid;
    }

    public void set(int row, int col, LivingBeing item) {
        grid[row][col] = item;
    }

    public synchronized LivingBeing get(int row, int col){
        return grid[row][col];
    }

    public synchronized LivingBeing take(int row, int col){
        LivingBeing data = grid[row][col];
        grid[row][col] = null;
        return data;
    }

    public void printBoard(){
        System.out.println("halo");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                if (grid[i][j] != null){
                    System.out.print("[" +grid[i][j].getName() + "] ");
                } else {
                    System.out.print("[   ]");

                }
            }
            System.out.println();

        }
    }

    public void remove(LivingBeing livingBeing){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                if (grid[i][j] != null && grid[i][j].equals(livingBeing)){
                    grid[i][j] = null;
                    break;
                }
            }
        }
    }

    public void addPlantObserver(Observer obs){
        plantObserver.add(obs);
    }

    public void removePlantObserver(Observer obs){
        plantObserver.remove(obs);
    }

    public void notifyPlant(){
        for (Observer obs: plantObserver){
            obs.update("");
        }
    }

}
