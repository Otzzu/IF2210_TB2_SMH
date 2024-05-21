package com.ooopppp.tubes_oop_2.Controller;

import com.ooopppp.tubes_oop_2.Boundary.GenericDialog;

public class DialogController {
    private GenericDialog dialog;

    public DialogController(GenericDialog dialog) {
        this.dialog = dialog;
    }

    public void saveGame(String format, String folder) {
        // Implementation to save the game
        System.out.println("save");
        System.out.println("Format " + format);
        System.out.println("folder " + folder);
        // Example: Write to file, update database, etc.
    }

    public void loadGame(String format, String folder) {
        // Implementation to load the game
        System.out.println("load");
        System.out.println("Format" + format);
        System.out.println("folder " + folder);
        // Example: Read from file, query database, etc.
    }
}
