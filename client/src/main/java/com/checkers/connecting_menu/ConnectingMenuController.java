package com.checkers.connecting_menu;

import java.io.IOException;
import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;

/*
 * Kontroler do menu, które pokazuje się podczas oczekiwania na połączenie z serwerem
 */
public class ConnectingMenuController implements StageController {

    private StageManager manager;
    private ConnectingMenuView view;

    /**
     * Konstruktor
     * @param manager Manadżer scen
     */
    public ConnectingMenuController(StageManager manager) {
        this.manager = manager;
        this.view = new ConnectingMenuView(this);
    }

    /*
     * Tutaj następuje łączenie się z serwere, na samym początku pojawienia się sceny
     */
    @Override
    public void activate() {
        view.setCurrent();

        try {
            ServerConnection connection = new ServerConnection("localhost", 58901);
            manager.switchToChooseLobbyMenu(connection);
        } catch (IOException e) {
            e.printStackTrace();
            backToMain();
        }
    }

    /*
     * Powrót do głównego menu
     */
    public void backToMain() {
        manager.switchToMainMenu();
    }
}
