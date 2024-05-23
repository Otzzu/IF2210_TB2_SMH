package com.ooopppp.tubes_oop_2.Entity;

public interface SaveLoadFile {
    void loadData(String folderName);
    void loadPlayer();
    void loadGameState();
    void savePlayer();
    void saveGameState();
}
