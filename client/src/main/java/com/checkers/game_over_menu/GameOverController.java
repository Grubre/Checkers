package com.checkers.game_over_menu;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;

/*
 * Kontroler to ekranu końca gry
 */
public class GameOverController implements StageController{
    private StageManager stageManager;
    private GameOverView view;

    /**
     * Kontruktor
     * @param manager Menadżer scen
     * @param youWon Czy użytkownik wygrał grę
     */
    public GameOverController(StageManager manager, boolean youWon)
    {
        this.stageManager = manager;

        view = new GameOverView(this, youWon);
    }

    /*
     * Wróć do głównego menu
     */
    public void goBackToPlayMenu()
    {
        stageManager.switchToPlayModeMenu();
    }

    @Override
    public void activate() {
        view.setCurrent();
    }

    @Override
    public void deactivate() {
        //
    }
    
}
