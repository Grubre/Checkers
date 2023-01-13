package com.checkers.stage_manager;

import com.checkers.choose_lobby.ChooseLobbyController;
import com.checkers.connecting_menu.ConnectingMenuController;
import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.main_menu.MainMenuController;

public class StageManager{

    StageController currentController;
    MainMenuController mainMenuController;
    ConnectingMenuController connectingMenuController;
    ChooseLobbyController chooseLobbyController;

    public void switchToPlayBoard() {
        // TODO Auto-generated method stub   
    }

    public void switchToGameCreationMenu() {
        System.out.println("Game Creation");
    }

    public void switchToChooseLobbyMenu(ServerConnection connection) {
        if (chooseLobbyController == null) {
            chooseLobbyController = new ChooseLobbyController(this, connection);
        }
        changeControllerIfNeeded(chooseLobbyController);
    }

    public void switchToConnectingMenu() {
        if (connectingMenuController == null) {
            connectingMenuController = new ConnectingMenuController(this);
        }
        changeControllerIfNeeded(connectingMenuController);  
    }

    public void switchToMainMenu() {
        if (mainMenuController == null) {
            mainMenuController = new MainMenuController(this);
        }
        changeControllerIfNeeded(mainMenuController);
        
    }

    private void changeControllerIfNeeded(StageController controller) {
        if (controller != currentController) {
            currentController.deactivate();
            currentController = controller;
            controller.activate();
        }
    }

    
}
