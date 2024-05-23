package com.ooopppp.tubes_oop_2.Entity;

import java.io.IOException;

public class SaveLoadFileAdapter  implements SaveLoadFile {

    private ExternalSaveLoadFile externalSaveLoadFile;

    public SaveLoadFileAdapter(ExternalSaveLoadFile externalSaveLoadFile) {
        this.externalSaveLoadFile = externalSaveLoadFile;
    }

    @Override
    public void loadData(String folderName) throws IOException {
        externalSaveLoadFile.loadCustomFile(folderName);
    }

    @Override
    public void saveData(String folderName) throws IOException {
        externalSaveLoadFile.saveCustomFile(folderName);
    }


}
