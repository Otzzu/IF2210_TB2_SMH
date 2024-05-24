 package com.ooopppp.tubes_oop_2.Entity;

 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.List;
 import java.util.Map;
 import java.util.StringJoiner;

 public class SaveLoadTxt implements SaveLoadFile {
     private String folderPath;

     @Override
     public void loadData(String folderPath) {
         this.folderPath = folderPath;
         GameData.clear();
         loadGameState(folderPath);
         loadPlayer(folderPath);
     }

     public void loadPlayer(String folderPath) {
         // pathnya disesuain
         Path path1 = Paths.get(folderPath, "player1.txt");
         try {
             Player player = new Player("player1");
             GameData.getGameData().addPlayer(player);
             List<String> lines = Files.readAllLines(path1);
             GameData.getGameData().getPlayers()[0].setGulden(Integer.parseInt(lines.get(0)));
             GameData.getGameData().getPlayers()[0].getDeck().generateDeck(Integer.parseInt(lines.get(1)));
             CardFactory newCard = new CardFactory();
             int var2 = Integer.parseInt(lines.get(2));
             for (int i = 3; i < 3 + var2; i++) {
                 String line = lines.get(i);
                 String[] parts = line.split(" ");

                 if (parts[0].equals("A01")) {
                     GameData.getGameData().getPlayers()[0].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 0);
                 } else if (parts[0].equals("B01")) {
                     GameData.getGameData().getPlayers()[0].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 1);
                 } else if (parts[0].equals("C01")) {
                     GameData.getGameData().getPlayers()[0].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 2);
                 } else if (parts[0].equals("D01")) {
                     GameData.getGameData().getPlayers()[0].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 3);
                 } else if (parts[0].equals("E01")) {
                     GameData.getGameData().getPlayers()[0].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 4);
                 } else if (parts[0].equals("F01")) {
                     GameData.getGameData().getPlayers()[0].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 5);
                 }
             }

             for (int i = 4 + var2; i < 4 + var2 + Integer.parseInt(lines.get((3 + var2))); i++) {
                 String line = lines.get(i);
                 String[] parts = line.split(" ");
                 for (int j = 0; j < parts.length; j++) {
                     char col = parts[0].charAt(0);
                     char rowc = parts[0].charAt(2);
                     int row = rowc - '0';
                     if (col == 'A') {
                         GameData.getGameData().getPlayers()[0].getFarm().set(row - 1, 0, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][0] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][0]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][0].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][0]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][0].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     } else if (col == 'B') {
                         GameData.getGameData().getPlayers()[0].getFarm().set(row - 1, 1, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][1] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][1]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][1].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][1]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][1].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     } else if (col == 'C') {
                         GameData.getGameData().getPlayers()[0].getFarm().set(row - 1, 2, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][2] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][2]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][2].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][2]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][2].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     } else if (col == 'D') {
                         GameData.getGameData().getPlayers()[0].getFarm().set(row - 1, 3, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][3] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][3]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][3].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][3]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][3].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     } else if (col == 'E') {
                         GameData.getGameData().getPlayers()[0].getFarm().set(row - 1, 4, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][4] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][4]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][4].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][4]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[0].getFarm().getGrid()[row - 1][4].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     }
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
             System.out.println("Failed to read the game state from the file.");
         }

         Path path2 = Paths.get(folderPath, "player2.txt");
         try {
             List<String> lines = Files.readAllLines(path2);
             Player player2 = new Player("player2");
             GameData.getGameData().addPlayer(player2);
             GameData.getGameData().getPlayers()[1].setGulden(Integer.parseInt(lines.get(0)));
             GameData.getGameData().getPlayers()[1].getDeck().generateDeck(Integer.parseInt(lines.get(1)));
             CardFactory newCard = new CardFactory();

             int var2 = Integer.parseInt(lines.get(2));
             for (int i = 3; i < 3 + var2; i++) {
                 String line = lines.get(i);
                 String[] parts = line.split(" ");

                 if (parts[0].equals("A01")) {
                     GameData.getGameData().getPlayers()[1].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 0);
                 } else if (parts[0].equals("B01")) {
                     GameData.getGameData().getPlayers()[1].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 1);
                 } else if (parts[0].equals("C01")) {
                     GameData.getGameData().getPlayers()[1].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 2);
                 } else if (parts[0].equals("D01")) {
                     GameData.getGameData().getPlayers()[1].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 3);
                 } else if (parts[0].equals("E01")) {
                     GameData.getGameData().getPlayers()[1].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 4);
                 } else if (parts[0].equals("F01")) {
                     GameData.getGameData().getPlayers()[1].getDeck().moveOneCardToActiveDeck(newCard.createCard(parts[1]), 5);
                 }
             }

             for (int i = 4 + var2; i < 4 + var2 + Integer.parseInt(lines.get((3 + var2))); i++) {
                 String line = lines.get(i);
                 String[] parts = line.split(" ");
                 for (int j = 0; j < parts.length; j++) {
                     char col = parts[0].charAt(0);
                     char rowc = parts[0].charAt(2);
                     int row = rowc - '0';
                     if (col == 'A') {
                         GameData.getGameData().getPlayers()[1].getFarm().set(row - 1, 0, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][0] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][0]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][0].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][0]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][0].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     } else if (col == 'B') {
                         GameData.getGameData().getPlayers()[1].getFarm().set(row - 1, 1, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][1] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][1]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][1].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][1]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][1].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     } else if (col == 'C') {
                         GameData.getGameData().getPlayers()[1].getFarm().set(row - 1, 2, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][2] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][2]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][2].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][2]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][2].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     } else if (col == 'D') {
                         GameData.getGameData().getPlayers()[1].getFarm().set(row - 1, 3, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][3] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][3]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][3].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][3]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][3].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     } else if (col == 'E') {
                         GameData.getGameData().getPlayers()[1].getFarm().set(row - 1, 4, (LivingBeing) newCard.createCard(parts[1]));
                         if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][4] instanceof Animal) {
                             ((Animal) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][4]).setWeight(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][4].addItem((Item) newCard.createCard(parts[k]));
                             }
                         } else {
                             ((Plant) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][4]).setAge(Integer.parseInt(parts[2]));
                             for (int k = 4; k < 4 + Integer.parseInt(parts[3]); k++) {
                                 GameData.getGameData().getPlayers()[1].getFarm().getGrid()[row - 1][4].addItem((Item) newCard.createCard(parts[k]));
                             }
                         }
                     }
                 }
             }
        } catch (IOException e) {
             e.printStackTrace();
             System.out.println("Failed to read the game state from the file.");
        }
    }

     public void loadGameState(String folderPath) {
         // pathnya disesuain
         Path path = Paths.get(folderPath, "gamestate.txt");
         try {
             List<String> lines = Files.readAllLines(path);
             GameData.getGameData().setTurn(Integer.parseInt(lines.get(0)));
//             Store newStore = new Store();

             if(Integer.parseInt(lines.get(1)) != 0) {
                 CardFactory newCard = new CardFactory();
                 for (int i = 2; i < 2 + Integer.parseInt(lines.get(1)); i++) {
                     String line = lines.get(i);
                     String[] parts = line.split(" ");
                     for (int j = 0; j < Integer.parseInt(parts[1]); j++) {
                         GameData.getGameData().getStore().addItems((Product) newCard.createCard((parts[0])));
                     }
                 }
             }



         } catch (IOException e) {
             e.printStackTrace();
             System.out.println("Failed to read the game state from the file.");
         }

     }

     @Override
     public void saveData(String folderPath) {
         this.folderPath = folderPath;
         File directory = new File(folderPath);
         if (directory.exists()) {
             // Delete all files in the directory if it exists
             for (File file : directory.listFiles()) {
                 file.delete();
             }
         } else {
             // Create the directory if it does not exist
             directory.mkdirs();
         }
         savePlayer(directory);
         saveGameState(directory);

     }

     public void savePlayer(File directory) {
         // pathnya disesuain
         try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File(directory, "player1.txt")))) {
             writer1.write(GameData.getGameData().getPlayers()[0].getGulden() + "\n");
             writer1.write(GameData.getGameData().getPlayers()[0].getDeck().getAllDeck().size() + "\n");
             writer1.write(GameData.getGameData().getPlayers()[0].getDeck().getActiveDeckCount() + "\n");
             for (int i = 0; i < 6; i++) {
                 char prefix = (char) ('A' + i);
                 if (GameData.getGameData().getPlayers()[0].getDeck().getActiveDeck()[i] != null) {
                     writer1.write(prefix + "01" + " " + GameData.getGameData().getPlayers()[0].getDeck().getActiveDeck()[i].getName().toUpperCase().replace(" ", "_") + "\n");
                 }
             }
             writer1.write(GameData.getGameData().getPlayers()[0].getFarm().getCount() + "\n");
             for (int i = 0; i < GameData.getGameData().getPlayers()[0].getFarm().getRow(); i++) {
                 for (int j = 0; j < GameData.getGameData().getPlayers()[0].getFarm().getCol(); j++) {
                     char prefix = (char) ('A' + j);
                     if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j] != null) {
                         writer1.write(prefix + "0" + (i + 1) + " " + GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j].getName().toUpperCase().replace(" ", "_"));
                         if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j] instanceof Animal) {
                             writer1.write(" " + ((Animal) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j]).getWeight());
                         } else {
                             writer1.write(" " + ((Plant) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j]).getAge());
                         }
                         StringBuilder result = new StringBuilder();
                         if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j] != null && GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j].getActiveItem().values().stream().mapToInt(Integer::intValue).sum() == 0){
                            writer1.write(" " + (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j].getActiveItem().values().stream().mapToInt(Integer::intValue).sum()));
                         } else {
                            writer1.write(" " + (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j].getActiveItem().values().stream().mapToInt(Integer::intValue).sum() + " "));
                         }
                         for (Map.Entry<String, Integer> entry : GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j].getActiveItem().entrySet()) {
                             for (int k = 0; k < entry.getValue(); k++) {
                                 result.append(entry.getKey().toUpperCase().replace(" ", "_"));
                                 if (k < entry.getValue()) {
                                     result.append(" ");
                                 }
                             }
                         }
                         if (!result.isEmpty()) {
                             result.setLength(result.length() - 1);
                         }
                         writer1.write(result.toString());
                         writer1.newLine();
                     }
                 }
             }

         } catch (IOException e) {
             e.printStackTrace();
         }

         // Save data in player2.txt, overwrite if it already exists
         // pathnya disesuain
         try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File(directory, "player2.txt")))) {
             writer2.write(GameData.getGameData().getPlayers()[1].getGulden() + "\n");
             writer2.write(GameData.getGameData().getPlayers()[1].getDeck().getAllDeck().size() + "\n");
             writer2.write(GameData.getGameData().getPlayers()[1].getDeck().getActiveDeckCount() + "\n");
             for (int i = 0; i < 6; i++) {
                 char prefix = (char) ('A' + i);
                 if (GameData.getGameData().getPlayers()[1].getDeck().getActiveDeck()[i] != null) {
                     writer2.write(prefix + "01" + " " + GameData.getGameData().getPlayers()[1].getDeck().getActiveDeck()[i].getName().toUpperCase().replace(" ", "_") + "\n");
                 }
             }
             writer2.write(GameData.getGameData().getPlayers()[1].getFarm().getCount() + "\n");
             for (int i = 0; i < GameData.getGameData().getPlayers()[1].getFarm().getRow(); i++) {
                 for (int j = 0; j < GameData.getGameData().getPlayers()[1].getFarm().getCol(); j++) {
                     char prefix = (char) ('A' + j);
                     if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j] != null) {
                         writer2.write(prefix + "0" + (i + 1) + " " + GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j].getName().toUpperCase().replace(" ", "_"));
                         if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j] instanceof Animal) {
                             writer2.write(" " + ((Animal) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j]).getWeight());
                         } else {
                             writer2.write(" " + ((Plant) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j]).getAge());
                         }
                         StringBuilder result = new StringBuilder();
                         if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j] != null && GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j].getActiveItem().values().stream().mapToInt(Integer::intValue).sum() == 0){
                             writer2.write(" " + (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j].getActiveItem().values().stream().mapToInt(Integer::intValue).sum()));
                         } else {
                             writer2.write(" " + (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j].getActiveItem().values().stream().mapToInt(Integer::intValue).sum() + " "));
                         }
                         for (Map.Entry<String, Integer> entry : GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j].getActiveItem().entrySet()) {
                             for (int k = 0; k < entry.getValue(); k++) {
                                 result.append(entry.getKey().toUpperCase().replace(" ", "_"));
                                 if (k < entry.getValue()) {
                                     result.append(" ");
                                 }
                             }
                         }
                         if (!result.isEmpty()) {
                             result.setLength(result.length() - 1);
                         }
                         writer2.write(result.toString());
                         writer2.newLine();
                     }
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public void saveGameState(File directory) {
         // pathnya disesuain
         try (BufferedWriter writer3 = new BufferedWriter(new FileWriter(new File(directory, "gamestate.txt")))) {
             writer3.write(GameData.getGameData().getTurn() + "\n");
             writer3.write(GameData.getGameData().getStore().getItemSells().size() + "\n");
             for (Map.Entry<String, List<Product>> entry : GameData.getGameData().getStore().getItemSells().entrySet()) {
                 writer3.write(entry.getKey().toUpperCase().replace(" ", "_") + " " + entry.getValue().size() + "\n");
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
