package com.ooopppp.tubes_oop_2.Entity;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class PluginManager {

    private Map<String, SaveLoadFile> saveLoadFiles;

    public PluginManager() {
        saveLoadFiles = new HashMap<>();
    }

    public void loadNewPlugin(File file) throws Exception {
        URL jarURL = file.toURI().toURL();
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{jarURL});
        Class<?> plugClass = Class.forName("com.ooopppp.SaveLoadJSON", true, loader);
        System.out.println(plugClass);
        ExternalSaveLoadFile plugin = (ExternalSaveLoadFile) plugClass.getDeclaredConstructor().newInstance();
        saveLoadFiles.put("com.ooopppp.SaveLoadJSON", new SaveLoadFileAdapter(plugin));
    }

    public SaveLoadFile getSaveLoadFiles(String string) {
        return saveLoadFiles.get(string);
    }
}
