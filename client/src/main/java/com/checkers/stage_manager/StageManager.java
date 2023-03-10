package com.checkers.stage_manager;

import com.checkers.choose_lobby.ChooseLobbyController;
import com.checkers.connecting_menu.ConnectingMenuController;
import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.game_creation.GameCreationController;
import com.checkers.game_creation.MultiGameCreationController;
import com.checkers.game_creation.SingleGameCreationController;
import com.checkers.game_over_menu.GameOverController;
import com.checkers.main_menu.MainMenuController;
import com.checkers.playmode_menu.PlaymodeMenuController;
import com.checkers.game.GameController;
import com.checkers.game.MultiGameController;
import com.checkers.game.ReplayGameController;
import com.checkers.game.SingleGameController;
import com.checkers_core.VariantStartDescription;

public class StageManager{

    StageController currentController;
    
    MainMenuController mainMenuController;
    PlaymodeMenuController playmodeMenuController;
    
    ConnectingMenuController connectingMenuController;
    ChooseLobbyController chooseLobbyController;

    GameCreationController gameCreationController;
    GameController gameController;

    GameOverController gameOverController;

    public void switchToSingleGame(VariantStartDescription desc) {
        System.out.println(desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor());
        gameController = new SingleGameController(this, desc);
        changeControllerIfNeeded(gameController);
    }

    public void switchToMultiGame(VariantStartDescription desc, ServerConnection connection) {
        System.out.println(desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor());
        gameController = new MultiGameController(this, desc, connection);
        changeControllerIfNeeded(gameController);
    }

    public void switchToReplayGame(VariantStartDescription desc, ServerConnection connection) {
        System.out.println(desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor());
        gameController = new ReplayGameController(this, desc, connection);
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

    public void switchToGameOver(boolean youWon) {
        gameOverController = new GameOverController(this, youWon);
        changeControllerIfNeeded(gameOverController);
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
