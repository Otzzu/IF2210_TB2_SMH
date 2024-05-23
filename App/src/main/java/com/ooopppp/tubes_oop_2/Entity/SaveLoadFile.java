package com.ooopppp.tubes_oop_2.Entity;

import java.io.IOException;

public interface SaveLoadFile {
    void loadData(String folderName) throws IOException;
    void saveData(String folderName) throws IOException;
}
