// package com.ooopppp.tubes_oop_2.Entity;

// import java.io.BufferedWriter;
// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.List;
// import java.util.Map;

// public class SaveLoadTxt implements SaveLoadFile {
//     private String folderPath;

//     @Override
//     public void loadData(String folderPath) {
//         this.folderPath = folderPath;
//         loadGameState();
//         loadPlayer();
//         loadPlayer();
//     }

//     @Override
//     public void loadPlayer() {

//     }

//     @Override
//     public void loadGameState() {

//     }

//     public void saveData(String folderPath) {
//         this.folderPath = folderPath;
//         File directory = new File(folderPath);
//         if (directory.exists()) {
//             // Delete all files in the directory if it exists
//             for (File file : directory.listFiles()) {
//                 file.delete();
//             }
//         } else {
//             // Create the directory if it does not exist
//             directory.mkdirs();
//         }
//         savePlayer(directory);
//         saveGameState(directory);

//     }

//     @Override
//     public void savePlayer(File directory) {
//         try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File(directory, "player1.txt")))) {
//             writer1.write(GameData.getGameData().getPlayers()[0].getGulden() + "\n");
//             writer1.write(GameData.getGameData().getPlayers()[0].getDeck().getAllDeck().size() + "\n");
//             writer1.write(GameData.getGameData().getPlayers()[0].getDeck().getActiveDeckCount() + "\n");
//             for (int i = 0; i < 6; i++) {
//                 char prefix = (char) ('A' + i);
//                 if (GameData.getGameData().getPlayers()[0].getDeck().getActiveDeck()[i] != null) {
//                     writer1.write(prefix + "01" + " " + GameData.getGameData().getPlayers()[0].getDeck().getActiveDeck()[i].getName().toUpperCase().replace(" ", "_") + "\n");
//                 }
//             }
//             writer1.write(GameData.getGameData().getPlayers()[0].getFarm() + "\n");
//             for (int i = 0; i < GameData.getGameData().getPlayers()[0].getFarm().getRow(); i++) {
//                 for (int j = 0; j < GameData.getGameData().getPlayers()[0].getFarm().getCol(); j++) {
//                     char prefix = (char) ('A' + i);
//                     if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j] != null) {
//                         writer1.write(prefix + "0" + j + " " + GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j].getName().toUpperCase().replace(" ", "_"));
//                         if (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j] instanceof Animal) {
//                             writer1.write(" " + ((Animal) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j]).getWeight());
//                         } else {
//                             writer1.write(" " + ((Plant) GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j]).getAge());
//                         }
//                         writer1.write(" " + (GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j].getActiveItem().size()) + " ");
//                         for (Map.Entry<String, Integer> entry : GameData.getGameData().getPlayers()[0].getFarm().getGrid()[i][j].getActiveItem().entrySet()) {
//                             for (int k = 0; k < entry.getValue(); k++) {
//                                 writer1.write(entry.getKey());
//                                 if (k < entry.getValue() - 1) {
//                                     writer1.write(" ");
//                                 }
//                             }
//                         }
//                     }
//                     writer1.newLine();
//                 }
//             }

//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         // Save data in player2.txt, overwrite if it already exists
//         try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File(directory, "player2.txt")))) {
//             writer2.write(GameData.getGameData().getPlayers()[1].getGulden() + "\n");
//             writer2.write(GameData.getGameData().getPlayers()[1].getDeck().getAllDeck().size() + "\n");
//             writer2.write(GameData.getGameData().getPlayers()[1].getDeck().getActiveDeckCount() + "\n");
//             for (int i = 0; i < 6; i++) {
//                 char prefix = (char) ('A' + i);
//                 if (GameData.getGameData().getPlayers()[1].getDeck().getActiveDeck()[i] != null) {
//                     writer2.write(prefix + "01" + " " + GameData.getGameData().getPlayers()[1].getDeck().getActiveDeck()[i].getName().toUpperCase().replace(" ", "_") + "\n");
//                 }
//             }
//             writer2.write(GameData.getGameData().getPlayers()[1].getFarm() + "\n");
//             for (int i = 0; i < GameData.getGameData().getPlayers()[1].getFarm().getRow(); i++) {
//                 for (int j = 0; j < GameData.getGameData().getPlayers()[1].getFarm().getCol(); j++) {
//                     char prefix = (char) ('A' + i);
//                     if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j] != null) {
//                         writer2.write(prefix + "0" + j + " " + GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j].getName().toUpperCase().replace(" ", "_"));
//                         if (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j] instanceof Animal) {
//                             writer2.write(" " + ((Animal) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j]).getWeight());
//                         } else {
//                             writer2.write(" " + ((Plant) GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j]).getAge());
//                         }
//                         writer2.write(" " + (GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j].getActiveItem().size()) + " ");
//                         for (Map.Entry<String, Integer> entry : GameData.getGameData().getPlayers()[1].getFarm().getGrid()[i][j].getActiveItem().entrySet()) {
//                             for (int k = 0; k < entry.getValue(); k++) {
//                                 writer2.write(entry.getKey());
//                                 if (k < entry.getValue() - 1) {
//                                     writer2.write(" ");
//                                 }
//                             }
//                         }
//                     }
//                     writer2.newLine();
//                 }
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     @Override
//     public void saveGameState(File directory) {
//         try (BufferedWriter writer3 = new BufferedWriter(new FileWriter(new File(directory, "gamestate.txt")))) {
//             writer3.write(GameData.getGameData().getTurn() + "\n");
//             writer3.write(GameData.getGameData().getStore().getItemSells().size() + "\n");
//             for (Map.Entry<String, List<Product>> entry : GameData.getGameData().getStore().getItemSells().entrySet()) {
//                 writer3.write(entry.getKey().toUpperCase().replace(" ", "_") + " " + entry.getValue().size() + "\n");
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
