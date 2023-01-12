package com.checkers.stage_manager;

import com.checkers.controller.StageController;
import com.checkers.main_menu.MainMenuController;

public class StageManager implements StageManagerInterface {

    StageController currentController;
    MainMenuController mainMenuController;

    @Override
    public void switchToPlayBoard() {
        // TODO Auto-generated method stub   
    }

    @Override
    public void switchToChooseLobbyMenu() {
        // TODO Auto-generated method stub
    }

    @Override
    public void switchToConnectingMenu() {
        // TODO Auto-generated method stub   
    }

    @Override
    public void switchToMainMenu() {
        if (mainMenuController == null) {
            mainMenuController = new MainMenuController(this);
        }
        changeControllerIfNeeded(mainMenuController);
        
    }

    private void changeControllerIfNeeded(StageController controller) {
        if (controller != currentController) {
            currentController = controller;
            controller.activate();
        }
    }

    
}
