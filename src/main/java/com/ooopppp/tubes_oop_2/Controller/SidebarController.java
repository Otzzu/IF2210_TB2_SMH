package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.Component.Sidebar;
import com.ooopppp.tubes_oop_2.Boundary.MainView;

public class SidebarController {
    private Sidebar sidebar;
    private MainView parent;

    public SidebarController(Sidebar sidebar, MainView parent) {
        this.sidebar = sidebar;
        this.parent = parent;
    }

    public void attachEventsLadangButton(){
        sidebar.getButtonLadang().setOnAction(e -> {
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
        });
    }

    public void attachEventsTokoButton() {
        sidebar.getButtonToko().setOnAction(e -> {
            parent.getBtnSound().stop();
            parent.getBtnSound().play();
            parent.switchToStoreView(parent.getStage());
        });
    }

}
