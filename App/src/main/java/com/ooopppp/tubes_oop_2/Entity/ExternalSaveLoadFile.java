package com.ooopppp.tubes_oop_2.Entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ExternalSaveLoadFile {

    Map<String, String> nameKodeMap;
    public ExternalSaveLoadFile() {
        nameKodeMap = new HashMap<>();
        nameKodeMap.put("", "");
    }

    public abstract String getName();

    public abstract void loadCustomFile(String stringPath) throws IOException;

    public abstract void saveCustomFile(String stringPath) throws IOException;


}
