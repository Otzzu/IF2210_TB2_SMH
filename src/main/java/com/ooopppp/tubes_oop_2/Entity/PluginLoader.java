package com.ooopppp.tubes_oop_2.Entity;

public class PluginLoader implements Plugin{
    private Object legacyPlugin;

    public PluginLoader(Object legacyPlugin) {
        this.legacyPlugin = legacyPlugin;
    }

    @Override
    public void loadData() {

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
