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
    public void loadPlayer() {

    }

    @Override
    public void loadGameState() {

    }

    @Override
    public void savePlayer() {

    }

    @Override
    public void saveGameState() {

    }
}
