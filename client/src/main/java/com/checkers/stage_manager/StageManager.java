package com.checkers.stage_manager;

import com.checkers_core.Board.Color;
import com.checkers.choose_lobby.ChooseLobbyController;
import com.checkers.connecting_menu.ConnectingMenuController;
import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.game_creation.GameCreationController;
import com.checkers.main_menu.MainMenuController;
import com.checkers.playmode_menu.PlaymodeMenuController;
import com.checkers.scenes.GameController;
import com.checkers_core.BasicPawnFactory;
import com.checkers_core.BasicVariant;
import com.checkers_core.VariantStartDescription;

public class StageManager{

    StageController currentController;
    
    MainMenuController mainMenuController;
    PlaymodeMenuController playmodeMenuController;
    
    ConnectingMenuController connectingMenuController;
    ChooseLobbyController chooseLobbyController;

    GameCreationController gameCreationController;

    public void switchToGame(VariantStartDescription desc) {
        new GameController(new BasicVariant(desc.getWidth(), desc.getHeight(), new BasicPawnFactory()), Color.BLACK).setCurrent();
    }

    public void switchToGameCreationMenu() {
        if (gameCreationController == null) {
            gameCreationController = new GameCreationController(this);
        }
        changeControllerIfNeeded(gameCreationController);
    }

    public void switchToPlayModeMenu() {
        if (playmodeMenuController == null) {
            playmodeMenuController = new PlaymodeMenuController(this);
        }
        changeControllerIfNeeded(playmodeMenuController);
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
            if (currentController != null) {
                currentController.deactivate();
            }
            currentController = controller;
            controller.activate();
        }
    }

    
}
