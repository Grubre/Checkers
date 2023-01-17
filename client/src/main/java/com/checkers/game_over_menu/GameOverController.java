package com.checkers.game_over_menu;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;

public class GameOverController implements StageController{
    private StageManager stageManager;

    private GameOverView view;
    public GameOverController(StageManager manager, boolean youWon)
    {
        this.stageManager = manager;

        view = new GameOverView(this, youWon);
    }

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
