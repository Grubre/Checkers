package com.checkers.playmode_menu;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;

/*
 * Kontroler menu wybieraniu trybu gry
 */
public class PlaymodeMenuController implements StageController {

    private PlayModeMenuView view = new PlayModeMenuView(this);
    private StageManager manager;

    /**
     * Konstruktor
     * @param manager
     */
    public PlaymodeMenuController(StageManager manager) {
        this.manager = manager;
    }

    /*
     * Przejdź do gry lokalnej
     */
    public void local() {
        manager.switchToLocalGameCreationMenu();
    }

    /*
    * Przejdź do gry online
    */
    public void online() {
        manager.switchToConnectingMenu();
    }

    /*
     * Przejdź z powrotem do głównego menu
     */
    public void back() {
        manager.switchToMainMenu();
    }


    @Override
    public void activate() {
        view.setCurrent();
    }
}
