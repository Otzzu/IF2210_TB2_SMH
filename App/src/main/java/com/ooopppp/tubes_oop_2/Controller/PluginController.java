package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.PluginDialog;
import com.ooopppp.tubes_oop_2.Entity.GameData;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class PluginController {
    private PluginDialog pluginDialog;
    private File fileChoose;

    public PluginController(PluginDialog pluginDialog) {
        this.pluginDialog = pluginDialog;
    }

    public void handleChoose(Label folderLabel){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(pluginDialog.getDialogStage());

        if (selectedFile != null) {
            folderLabel.setText("File plugin   :   " + selectedFile.getName());
            System.out.println("File selected: " + selectedFile.getAbsolutePath());
            fileChoose = selectedFile;
        }
    }

    public void handleClick(){
        try {
            if (fileChoose == null) {
                return;
            }
            GameData.getGameData().getPluginManager().loadNewPlugin(fileChoose);

        } catch (Exception e){
            System.out.println("jjajaj");
            e.printStackTrace();
        }
    }
}
