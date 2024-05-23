package com.ooopppp;

import com.ooopppp.tubes_oop_2.Entity.ExternalSaveLoadFile;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import com.ooopppp.tubes_oop_2.Entity.Player;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;

import java.io.File;

public class SaveLoadXML extends ExternalSaveLoadFile {

    @Override
    public String getExt() {
        return ".xml";
    }

    @Override
    public String getName() {
        return "com.ooopppp.SaveLoadXML";
    }

    @Override
    public void loadCustomFile(String folderPath) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(GameData.class, Player.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Load players
            Player player1 = (Player) unmarshaller.unmarshal(new File(folderPath + "/player1.xml"));
            Player player2 = (Player) unmarshaller.unmarshal(new File(folderPath + "/player2.xml"));
            GameData.clear();
            GameData.getGameData().addPlayer(player1);
            GameData.getGameData().addPlayer(player2);

            // Load game state
            GameData loadedGameData = (GameData) unmarshaller.unmarshal(new File(folderPath + "/gamestate.xml"));
            GameData.getGameData().setTurn(loadedGameData.getTurn());
            GameData.getGameData().setStore(loadedGameData.getStore());

        } catch (JAXBException e) {

            throw new IOException("Failed to load XML data");
        }
    }

    @Override
    public void saveCustomFile(String folderPath) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(GameData.class, Player.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Save players
            marshaller.marshal(GameData.getGameData().getPlayers()[0], new File(folderPath + "/player1.xml"));
            marshaller.marshal(GameData.getGameData().getPlayers()[1], new File(folderPath + "/player2.xml"));

            // Save game state
            marshaller.marshal(GameData.getGameData(), new File(folderPath + "/gamestate.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
            throw new IOException("Failed to save XML data");
        }
    }
}