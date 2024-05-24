package com.ooopppp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ooopppp.tubes_oop_2.Entity.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveLoadJSON implements ExternalSaveLoadFile {

    public SaveLoadJSON() {
        super();
    }

    @Override
    public String getName() {
        return "com.ooopppp.SaveLoadJSON";
    }

    @Override
    public String getExt(){
        return ".json";
    }

    public Map<String, Object> changePlayerToMap(Player player){
        Map<String, Object> dataPlayer1 = new HashMap<>();
        dataPlayer1.put("gulden", player.getGulden());
        dataPlayer1.put("jumlah_deck", player.getDeck().getAllDeck().size());
        List<Object> list = new ArrayList<>();

        Card[] activeDeck = player.getDeck().getActiveDeck();
        for (int i = 0 ; i < 6; i++){
            if (activeDeck[i] != null){
                Map<String, Object> dataPlayer1Lokasi = new HashMap<>();
                dataPlayer1Lokasi.put("index", i);
                dataPlayer1Lokasi.put("nama", activeDeck[i].getName().toUpperCase().replace(" ", "_"));
                list.add(dataPlayer1Lokasi);
            }
        }

        dataPlayer1.put("deck_aktif", list);

        List<Object> list2 = new ArrayList<>();

        LivingBeing[][] ladang = player.getFarm().getGrid();

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                if (ladang[i][j] != null){
                    Map<String, Object> dataPlayer1Lokasi = new HashMap<>();
                    dataPlayer1Lokasi.put("index_i", i);
                    dataPlayer1Lokasi.put("index_j", j);
                    dataPlayer1Lokasi.put("nama", ladang[i][j].getName().toUpperCase().replace(" ", "_"));
                    dataPlayer1Lokasi.put("nilai", ladang[i][j] instanceof Plant ? ((Plant) ladang[i][j]).getAge() : ((Animal) ladang[i][j]).getWeight());
                    dataPlayer1Lokasi.put("kartu_aktif", ladang[i][j].getActiveItem());
                    list2.add(dataPlayer1Lokasi);
                }
            }
        }

        dataPlayer1.put("ladang", list2);

        return dataPlayer1;
    }

    public Player loadMapToPlayer(Map<String, Object> player1, String name){
        Player playerObj = new Player(name);
        playerObj.setGulden((Integer)player1.get("gulden"));
        System.out.println(playerObj.getGulden());
        playerObj.getDeck().generateDeck((Integer) player1.get("jumlah_deck"));

        CardFactory cardFactory = new CardFactory();
        List<Map<String, Object>> activeDeck = (List<Map<String, Object>>) player1.get("deck_aktif");
        for (Map<String, Object> stringObjectMap : activeDeck) {
            Integer index = (Integer) stringObjectMap.get("index");
            String nama = (String) stringObjectMap.get("nama");
            Card c = cardFactory.createCard(nama);
            playerObj.getDeck().getActiveDeck()[index] = c;
        }

        List<Map<String, Object>> ladang = (List<Map<String, Object>>) player1.get("ladang");
        for (Map<String, Object> stringObjectMap : ladang) {
            Integer indexI = (Integer) stringObjectMap.get("index_i");
            Integer indexJ = (Integer) stringObjectMap.get("index_j");
            String nama = (String) stringObjectMap.get("nama");
            Card c = cardFactory.createCard(nama);
            Integer value = (Integer) stringObjectMap.get("nilai");
            if (c instanceof Animal){
                ((Animal) c).setWeight(value);
            } else {
                ((Plant) c).setAge(value);
                playerObj.getFarm().addPlantObserver((Plant) c);
            }

            Map<String, Integer> itemAktif = (Map<String, Integer>) player1.get("kartu_aktif");
            ((LivingBeing) c).setActiveItem(itemAktif);
            playerObj.getFarm().getGrid()[indexI][indexJ] = (LivingBeing) c;
        }

        return  playerObj;
    }

    @Override
    public void loadCustomFile(String folderPath) throws IOException {
        GameData.clear();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        Map<String, Object> player1 = mapper.readValue(new File(folderPath + "/player1.json"), Map.class);
        Map<String, Object> player2 = mapper.readValue(new File(folderPath + "/player2.json"), Map.class);

        Player playerObj1 = loadMapToPlayer(player1, "player1");
        Player playerObj2 = loadMapToPlayer(player2, "player2");

        GameData.getGameData().addPlayer(playerObj1);
        GameData.getGameData().addPlayer(playerObj2);

        Map<String, Object> data = mapper.readValue(new File(folderPath + "/gamestate.json"), Map.class);
        GameData.getGameData().setTurn((Integer)data.get("current_turn"));
        Map<String, List<Object>> store = (Map<String, List<Object>>) data.get("store");

        CardFactory cardFactory = new CardFactory();
        for (String s : store.keySet()){
            int many = store.get(s).size();
            for (int i = 0; i < many; i++){
                GameData.getGameData().getStore().addItems((Product) cardFactory.createCard(s.toUpperCase().replace(" ", "_")));
            }
        }
    }

    @Override
    public void saveCustomFile(String folderPath) throws IOException {

        Player player = GameData.getGameData().getPlayers()[0];
        Player player2 = GameData.getGameData().getPlayers()[1];



        Map<String, Object> dataPlayer1 = changePlayerToMap(player);
        Map<String, Object> dataPlayer2 = changePlayerToMap(player2);



        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);


        mapper.writeValue(new File(folderPath + "/player1.json"), dataPlayer1);
        mapper.writeValue(new File(folderPath + "/player2.json"), dataPlayer2);
        Map<String, Object> data = new HashMap<>();
        GameData gameData = GameData.getGameData();
        data.put("current_turn",gameData.getTurn() );
        data.put("store", gameData.getStore().getItemSells());
        mapper.writeValue(new File(folderPath + "/gamestate.json"), data);
    }
}