package com.ooopppp;

import org.yaml.snakeyaml.DumperOptions;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import org.yaml.snakeyaml.Yaml;
import com.ooopppp.tubes_oop_2.Entity.*;

public class SaveLoadYAML implements ExternalSaveLoadFile {

    private Yaml yaml;

    public SaveLoadYAML() {
        try{
            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            yaml = new Yaml(options);
        } catch (Exception e){
            System.out.println("adadadad " + e.getMessage());
        }

    }

    @Override
    public String getExt() {
        return ".yaml";
    }

    @Override
    public String getName() {
        return "com.ooopppp.SaveLoadXML";
    }

    @Override
    public void saveCustomFile(String folderPath) throws IOException {
        saveGameState(folderPath + "/gamestate.yaml");
        savePlayerData(folderPath, 1); // Save data for player 1
        savePlayerData(folderPath, 2); // Save data for player 2
    }

    private void saveGameState(String filePath) throws IOException {
        GameData gameData = GameData.getGameData();
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("current_turn", gameData.getTurn());

        Map<String, Integer> itemsCount = new LinkedHashMap<>();
        gameData.getStore().getItemSells().forEach((key, value) -> itemsCount.put(key, value.size()));
        data.put("items_in_store", itemsCount);

        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(data, writer);
        }
    }

    private void savePlayerData(String folderPath, int playerIndex) throws IOException {
        GameData gameData = GameData.getGameData();
        Player player = gameData.getPlayers()[playerIndex - 1]; // Adjust index for 0-based array

        // Prepare player-specific data for YAML serialization
        Map<String, Object> playerData = new LinkedHashMap<>();
        playerData.put("gulden", player.getGulden());
        playerData.put("active_deck_count", player.getDeck().getActiveDeckCount());
        playerData.put("total_deck", player.getDeck().getAllDeck().size()); // Assuming getTotalCards() exists
         // Assuming getActiveDeck() returns an array

        // Active deck details: card locations and types in the deck
        int i = 0;
        List<Map<String, String>> activeDeckDetails = new ArrayList<>();
        for (Card card : player.getDeck().getActiveDeck()) { // Assuming getActiveDeck() returns an array
            if (card != null) {
                Map<String, String> cardDetails = new LinkedHashMap<>();
                cardDetails.put("location", getLetter(i));
                cardDetails.put("name", card.getName()); // Assuming getType() method exists
                activeDeckDetails.add(cardDetails);

            }
            i++;
        }
        playerData.put("active_deck", activeDeckDetails);

        int count = 0;
        // Farm details: each card in the farm and its details
        List<Map<String, Object>> farmDetails = new ArrayList<>();
        for (int k = 0; k < player.getFarm().getRow(); k++) {
            for (int j = 0; j < player.getFarm().getCol(); j++) {
                LivingBeing being = player.getFarm().get(k, j); // Assuming get method returns a LivingBeing
                if (being != null) {
                    Map<String, Object> beingDetails = new LinkedHashMap<>();
                    int temp = k + 1;
                    beingDetails.put("location", getLetter(j) + "0" + temp);
                    beingDetails.put("name", being.getName()); // Assuming getType() method exists
                    if(being instanceof Animal){
                        beingDetails.put("umur_berat", ((Animal) being).getWeight());
                    }else{
                        beingDetails.put("umur_berat",((Plant) being).getAge());
                    }
                    beingDetails.put("active_item", being.getActiveItem());


                    // Assuming getStats() provides details like age/weight
                    farmDetails.add(beingDetails);
                    count++;
                }
            }
        }
        playerData.put("cardInFarm",count);

        playerData.put("farm", farmDetails);

        // Writing player data to YAML
        try (FileWriter writer = new FileWriter(folderPath + "/player" + playerIndex + ".yaml")) {
            yaml.dump(playerData, writer);
        }
    }

    public void loadCustomFile(String folderPath) throws IOException {

        Yaml yaml = new Yaml();

        // Load game state
        try (FileReader reader = new FileReader(Paths.get(folderPath, "/gamestate.yaml").toString())) {
            Map<String, Object> gameState = yaml.load(reader);
            GameData gameData = GameData.getGameData();
            gameData.setTurn((Integer) gameState.get("current_turn"));
            Map<String, Integer> itemsInStore = (Map<String, Integer>) gameState.get("items_in_store");
            int current = gameData.getTurn()%2 == 0 ? 1 : 0;
            gameData.setCurrentPlayer(gameData.getPlayers()[current]);
            // Assuming the Store has a method to clear and set new items
//            gameData.getStore().clearItems();
            gameData.getStore().getItemSells().clear();
            CardFactory cardFactory = new CardFactory();
            itemsInStore.forEach((key, value) -> {
                for (int i = 0; i < value; i++) {
                    gameData.getStore().addItems((Product) cardFactory.createCard(formatCardType(key)));
                }
            });
        } catch (IOException e) {
            throw new IOException("Failed to load game state from YAML", e);
        }

        // Load player data
        for (int i = 1; i <= 2; i++) {
            try (FileReader reader = new FileReader(Paths.get(folderPath, "/player" + i + ".yaml").toString())) {
                Map<String, Object> playerData = yaml.load(reader);
                System.out.println(Arrays.toString(GameData.getGameData().getPlayers()));
                Player player = GameData.getGameData().getPlayers()[i - 1];

                // Load basic attributes
                player.setGulden((Integer) playerData.get("gulden"));
                player.getDeck().generateDeck((int)playerData.get("total_deck"));
                // Load the deck

                CardFactory cardFact =  new CardFactory();
                // Load active deck details
                player.getDeck().setActiveDeckEmpty();
                List<Map<String, Object>> activeDeckDetails = (List<Map<String, Object>>) playerData.get("active_deck");
                for (Map<String, Object> cardDetails : activeDeckDetails) {
                    player.getDeck().getActiveDeck()[getNumber((String) cardDetails.get("location"))] = cardFact.createCard(formatCardType((String) cardDetails.get("name")));
                }

                // Load farm details
                List<Map<String, Object>> farmDetails = (List<Map<String, Object>>) playerData.get("farm");
                player.setFarm(new Farm(4,5));
                Farm farm = player.getFarm();
                // Assuming a method to clear the farm
                for (Map<String, Object> beingDetails : farmDetails) {
                    String location = (String) beingDetails.get("location");
                    int col = location.charAt(0) - 'A';
                    int row = Integer.parseInt(location.substring(2)) - 1;
                    String name = formatCardType((String) beingDetails.get("name"));


                    LivingBeing being = (LivingBeing) cardFact.createCard(name);
                    if (cardFact.createCard(name) instanceof Animal) { // Determine if it's an Animal or Plant by checking a unique attribute
                        ((Animal) being).setWeight((Integer) beingDetails.get("umur_berat"));
                    } else {
                        ((Plant) being).setAge((Integer) beingDetails.get("umur_berat"));
                        farm.addPlantObserver((Plant) being);
                    }
                    farm.set(row, col, being); // Assuming a method to place beings at specific locations

                }
            } catch (IOException e) {
                throw new IOException("Failed to load player " + i + " data from YAML", e);
            }
        }
    }

    public static String getLetter(int index) {
        if (index < 0 || index > 5) {
            return null; // Return null if the index is out of the acceptable range
        }
        return String.valueOf((char) ('A' + index));
    }

    public static int getNumber(String column) {
        int number = 0;
        int length = column.length();
        for (int i = 0; i < length; i++) {
            number = number * 26 + (column.charAt(i) - 'A');
        }
        return number;
    }

    public static String formatCardType(String input) {
        return input.toUpperCase().replace(' ', '_');
    }
}