package com.checkers.stage_manager;

import com.checkers.choose_lobby.ChooseLobbyController;
import com.checkers.connecting_menu.ConnectingMenuController;
import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.game_creation.GameCreationController;
import com.checkers.game_creation.OnlineGameCreationController;
import com.checkers.game_creation.LocalGameCreationController;
import com.checkers.game_over_menu.GameOverController;
import com.checkers.main_menu.MainMenuController;
import com.checkers.playmode_menu.PlaymodeMenuController;
import com.checkers.game.GameController;
import com.checkers.game.OnlineGameController;
import com.checkers.game.LocalGameController;
import com.checkers_core.VariantStartDescription;

/**
 * Menadżer scen. Działa jako mediator, który kontroluje zmienianie pomiędzy konkretnymi scenami.
 * Ogranicza przez to sprzężenia między nimi
 */
public class StageManager{

    private StageController currentController;

    private MainMenuController mainMenuController;
    private PlaymodeMenuController playmodeMenuController;
    
    private ConnectingMenuController connectingMenuController;
    private ChooseLobbyController chooseLobbyController;

    private GameCreationController gameCreationController;
    private GameController gameController;

    private GameOverController gameOverController;

    
    /** 
     * Zmień obecną scenę na lokalną grę
     * @param desc
     */
    public void switchToLocalGame(VariantStartDescription desc) {
        System.out.println(desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor());
        gameController = new LocalGameController(this, desc);
        changeControllerIfNeeded(gameController);
    }

    
    /** 
     * Zmień obecną scenę na grę online
     * @param desc
     * @param connection
     */
    public void switchToOnlineGame(VariantStartDescription desc, ServerConnection connection) {
        System.out.println(desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor());
        gameController = new OnlineGameController(this, desc, connection);
        changeControllerIfNeeded(gameController);
    }

    /**
     * Zmień obecną scenę na tworzenie gry lokalnej
     */
    public void switchToLocalGameCreationMenu() {
        gameCreationController = new LocalGameCreationController(this);
        changeControllerIfNeeded(gameCreationController);
    }

    
    /** 
     * Zmień obecną scenę na tworzenie gry online
     * @param connection
     */
    public void switchToOnlineGameCreationMenu(ServerConnection connection) {
        gameCreationController = new OnlineGameCreationController(this, connection);
        changeControllerIfNeeded(gameCreationController);
    }
    
    /**
     * Zmień obecną scenę na menu wybrania trybu gry
     */
    public void switchToPlayModeMenu() {
        playmodeMenuController = new PlaymodeMenuController(this);
        changeControllerIfNeeded(playmodeMenuController);
    }
    
    /** 
     * Zmień obecną scenę na menu wybierania lobby
     * @param connection
     */
    public void switchToChooseLobbyMenu(ServerConnection connection) {
        chooseLobbyController = new ChooseLobbyController(this, connection);
        changeControllerIfNeeded(chooseLobbyController);
    }

    /**
     * Zmień obecną scenę na ekran łączenia z serwerem
     */
    public void switchToConnectingMenu() {
        connectingMenuController = new ConnectingMenuController(this);
        changeControllerIfNeeded(connectingMenuController);  
    }

    /**
     * Zmień obecną scenę na główne menu
     */
    public void switchToMainMenu() {
        mainMenuController = new MainMenuController(this);
        changeControllerIfNeeded(mainMenuController);
    }
    
    /**
     * Zmień obecną scenę na ekran końca gry 
     * @param youWon Czy użytkownik wygrał grę?
     */
    public void switchToGameOver(boolean youWon) {
        gameOverController = new GameOverController(this, youWon);
        changeControllerIfNeeded(gameOverController);
    }

    
    /** 
     * Zmień kontroler
     * @param controller
     */
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
