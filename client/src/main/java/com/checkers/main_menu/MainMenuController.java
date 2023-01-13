package com.checkers.main_menu;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;
import javafx.application.Platform;

public class MainMenuController implements StageController{
    MainMenuView view;
    StageManager manager;
    
    public MainMenuController(StageManager manager) {
        this.manager = manager;
        this.view  = new MainMenuView(this);
    }

    @Override
    public void activate() {
        view.setCurrent();
    }

    public void exitGame() {
        Platform.exit();
    }
    
    public void playGame() {
        manager.switchToConnectingMenu();
    }

    @Override
    public void deactivate() {   
    }
}
