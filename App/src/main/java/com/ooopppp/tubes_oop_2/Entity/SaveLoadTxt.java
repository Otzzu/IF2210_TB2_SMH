package com.ooopppp.tubes_oop_2.Entity;

public class SaveLoadTxt implements SaveLoadFile{
    private String folderPath;
    @Override
    public void loadData(String folderPath) {
        this.folderPath = folderPath;
        loadGameState();
        loadPlayer();
        loadPlayer();
    }

    @Override
    public void saveData(String folderName) {
        this.folderPath = folderPath;
        saveGameState();
        savePlayer();
        savePlayer();
    }

    public void loadPlayer() {

    }

    public void loadGameState() {

    }

    public void savePlayer() {

    }

    public void saveGameState() {

    }
}
