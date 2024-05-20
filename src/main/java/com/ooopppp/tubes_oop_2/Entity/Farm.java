package com.ooopppp.tubes_oop_2.Entity;

public class Farm {

    private LivingBeing[][] grid;
    private int row;
    private int col;
    public Farm(int row, int col){
        grid = new LivingBeing[row][col];
        this.row = row;
        this.col = col;
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

    public LivingBeing get(int row, int col){
        return grid[row][col];
    }

    public LivingBeing take(int row, int col){
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
}
