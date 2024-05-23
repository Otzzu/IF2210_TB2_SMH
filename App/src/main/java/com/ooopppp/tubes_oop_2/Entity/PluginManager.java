package com.ooopppp.tubes_oop_2.Entity;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class PluginManager {

    private Map<String, SaveLoadFile> saveLoadFiles;

    public PluginManager() {
        saveLoadFiles = new HashMap<>();
        saveLoadFiles.put(".txt", new SaveLoadTxt());
    }

    public List<String> getAllTypePlugin(){
        return new ArrayList<>(saveLoadFiles.keySet());
    }

    public void loadNewPlugin(File file) throws Exception {
        URL jarURL = file.toURI().toURL();
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{jarURL});

        String className = "";
        try (JarFile jarFile = new JarFile(file)) {
            Manifest manifest = jarFile.getManifest();
            if (manifest != null) {
                Attributes attrs = manifest.getMainAttributes();  // Get the main manifest attributes
                System.out.println("Manifest attributes:");
                className = attrs.getValue("Plugin-Class");
            } else {
                throw new Exception("Invalid jar");
            }
        }
        Class<?> plugClass = Class.forName(className, true, loader);
        System.out.println(plugClass);
        ExternalSaveLoadFile plugin = (ExternalSaveLoadFile) plugClass.getDeclaredConstructor().newInstance();

        saveLoadFiles.put(plugin.getExt(), new SaveLoadFileAdapter(plugin));
    }

    public SaveLoadFile getSaveLoadFiles(String string) {
        return saveLoadFiles.get(string);
    }
}
