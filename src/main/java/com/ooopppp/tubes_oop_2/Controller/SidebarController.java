package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.Sidebar;
import com.ooopppp.tubes_oop_2.Boundary.GenericDialog;
import com.ooopppp.tubes_oop_2.Boundary.MainView;
import com.ooopppp.tubes_oop_2.Boundary.PluginDialog;

public class SidebarController {
    private Sidebar sidebar;
    private MainView parent;

    public SidebarController(Sidebar sidebar, MainView parent) {
        this.sidebar = sidebar;
        this.parent = parent;
    }

    public void handleLadangButton(){
        parent.getBtnSound().stop();
        parent.getBtnSound().play();
        String text = sidebar.getButtonLadang().getText();
        if (text.equals("Ladang Lawan")){
            parent.getBoard().getController().populateGrid(true);
            sidebar.getButtonLadang().setText("Ladangku");
        } else {
            parent.getBoard().getController().populateGrid(false);
            sidebar.getButtonLadang().setText("Ladang Lawan");
        }
        parent.setSelectedCardDeck(null);
        parent.getDeckContainer().getController().renderDeck();
    }

    public void handleSaveButton(){
        parent.getBtnSound().stop();
        parent.getBtnSound().play();
        GenericDialog.showGenericDialog(parent.getStage(), "Save");
    }

    public void handleLoadButton(){
        parent.getBtnSound().stop();
        parent.getBtnSound().play();
        GenericDialog.showGenericDialog(parent.getStage(), "Load");
    }

    public void handleTokoButton() {
        parent.getBtnSound().stop();
        parent.getBtnSound().play();
        parent.switchToStoreView(parent.getStage());
    }

    public void handlePluginButton(){
        parent.getBtnSound().stop();
        parent.getBtnSound().play();
        PluginDialog.showPluginDialog(parent.getStage());
    }
}
