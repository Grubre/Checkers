package com.checkers.stage_manager;

import com.checkers_core.Board.Color;
import com.checkers.choose_lobby.ChooseLobbyController;
import com.checkers.connecting_menu.ConnectingMenuController;
import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.game_creation.GameCreationController;
import com.checkers.game_creation.MultiGameCreationController;
import com.checkers.game_creation.SingleGameCreationController;
import com.checkers.main_menu.MainMenuController;
import com.checkers.playmode_menu.PlaymodeMenuController;
import com.checkers.game.GameController;
import com.checkers.game.MultiGameController;
import com.checkers_core.BasicPawnFactory;
import com.checkers_core.BasicVariantRuleFactory;
import com.checkers_core.BasicBoard;
import com.checkers_core.VariantStartDescription;

public class StageManager{

    StageController currentController;
    
    MainMenuController mainMenuController;
    PlaymodeMenuController playmodeMenuController;
    
    ConnectingMenuController connectingMenuController;
    ChooseLobbyController chooseLobbyController;

    GameCreationController gameCreationController;
    GameController gameController;

    public void switchToSingleGame(VariantStartDescription desc) {
        System.out.println(desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor());
        gameController = new GameController(this, desc);
        changeControllerIfNeeded(gameController);
    }

    public void switchToMultiGame(VariantStartDescription desc, ServerConnection connection) {
        System.out.println(desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor());
        gameController = new MultiGameController(this, desc, connection);
        changeControllerIfNeeded(gameController);
    }

    public void switchToSingleGameCreationMenu() {
        gameCreationController = new SingleGameCreationController(this);
        changeControllerIfNeeded(gameCreationController);
    }

    public void switchToMultiGameCreationMenu(ServerConnection connection) {
        gameCreationController = new MultiGameCreationController(this, connection);
        changeControllerIfNeeded(gameCreationController);
    }
    
    public void switchToPlayModeMenu() {
        playmodeMenuController = new PlaymodeMenuController(this);
        changeControllerIfNeeded(playmodeMenuController);
    }

    public void switchToChooseLobbyMenu(ServerConnection connection) {
        chooseLobbyController = new ChooseLobbyController(this, connection);
        changeControllerIfNeeded(chooseLobbyController);
    }

    public void switchToConnectingMenu() {
        connectingMenuController = new ConnectingMenuController(this);
        changeControllerIfNeeded(connectingMenuController);  
    }

    public void switchToMainMenu() {
        mainMenuController = new MainMenuController(this);
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
