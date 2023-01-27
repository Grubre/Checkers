package com.checkers.main_menu;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;
import javafx.application.Platform;

/*
 * Kontroler głównego menu
 */
public class MainMenuController implements StageController{
    private MainMenuView view;
    private StageManager manager;
    
    /**
     * Konstruktor
     * @param manager Menadżer scen
     */
    public MainMenuController(StageManager manager) {
        this.manager = manager;
        this.view  = new MainMenuView(this);
    }

    @Override
    public void activate() {
        view.setCurrent();
    }

    /*
     * Wyjdź z aplikacji
     */
    public void exitGame() {
        Platform.exit();
    }
    
    /*
     * Przejdż do ekranu wybrania gry
     */
    public void playGame() {
        manager.switchToPlayModeMenu();
    }
}
