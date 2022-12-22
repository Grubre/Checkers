package com.checkers.lobby;

import java.util.ArrayList;
import java.util.List;

import com.checkers.connection.ConnectionController;

public class GameLobby {

    List<ConnectionController> players = new ArrayList<ConnectionController>();

    public void processMessage(String message) {

    }

    public void addPlayer(ConnectionController player) {
        players.add(player);
    }
}
