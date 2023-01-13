package com.checkers.playmode_menu;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;

public class PlaymodeMenuController implements StageController {

    PlayModeMenuView view = new PlayModeMenuView(this);
    StageManager manager;


    public PlaymodeMenuController(StageManager manager) {
        this.manager = manager;
    }

    public void singleplayer() {
        manager.switchToGameCreationMenu();
    }

    public void multiplayer() {
        manager.switchToConnectingMenu();
    }

    public void back() {
        manager.switchToMainMenu();
    }


    @Override
    public void activate() {
        view.setCurrent();
    }

    @Override
    public void deactivate() {
    }
    
}
