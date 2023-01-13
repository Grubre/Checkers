package com.checkers.connecting_menu;

import java.io.IOException;
import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;

public class ConnectingMenuController implements StageController {

    StageManager manager;
    ConnectingMenuView view;

    public ConnectingMenuController(StageManager manager) {
        this.manager = manager;
        this.view = new ConnectingMenuView(this);
    }

    @Override
    public void activate() {
        view.setCurrent();

        try {
            ServerConnection connection = new ServerConnection("localhost", 58901);
            manager.switchToChooseLobbyMenu(connection);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void backToMain() {
        manager.switchToMainMenu();
    }

    @Override
    public void deactivate() {
    }
    
}
