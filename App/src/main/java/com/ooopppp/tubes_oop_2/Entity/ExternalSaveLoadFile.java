package com.ooopppp.tubes_oop_2.Entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface ExternalSaveLoadFile {

    String getExt();
    String getName();

    void loadCustomFile(String stringPath) throws IOException;

    void saveCustomFile(String stringPath) throws IOException;


}
