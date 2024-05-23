package com.ooopppp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooopppp.tubes_oop_2.Entity.ExternalSaveLoadFile;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Player;
import com.ooopppp.tubes_oop_2.Entity.Product;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveLoadJSON extends ExternalSaveLoadFile {

    public SaveLoadJSON() {
        super();
    }

    @Override
    public String getName() {
        return "com.ooopppp.SaveLoadJSON";
    }

    @Override
    public void loadCustomFile(String folderPath) throws IOException {
        GameData.clear();

        ObjectMapper mapper = new ObjectMapper();
        Player player1 = mapper.readValue(new File(folderPath + "/player1.json"), Player.class);
        GameData.getGameData().addPlayer(player1);
        Player player2 = mapper.readValue(new File(folderPath + "/player2.json"), Player.class);
        GameData.getGameData().addPlayer(player2);
        Map<String, Object> data = mapper.readValue(new File(folderPath + "/gamestate.json"), Map.class);
        GameData.getGameData().setTurn((Integer)data.get("current_turn"));
        GameData.getGameData().getStore().setItemSells((Map<String, List<Product>>) data.get("store"));
    }

    @Override
    public void saveCustomFile(String folderPath) throws IOException {

        Object player = GameData.getGameData().getPlayers()[0];
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(folderPath + "/player1.json"), player);
        Object player2 = GameData.getGameData().getPlayers()[1];
        mapper.writeValue(new File(folderPath + "/player2.json"), player2);
        Map<String, Object> data = new HashMap<>();
        GameData gameData = GameData.getGameData();
        data.put("current_turn",gameData.getTurn() );
        data.put("store", gameData.getStore().getItemSells());
        mapper.writeValue(new File(folderPath + "/gamestate.json"), gameData);
    }
}