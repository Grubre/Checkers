package com.checkers.lobby;

import java.util.ArrayList;
import java.util.List;

import com.checkers.Connection;

public class MainLobby implements AbstractLobby {

    List<GameLobby> lobbies = new ArrayList<GameLobby>();

    List<Connection> notInGame = new ArrayList<Connection>();

    @Override
    public String processMessage(String message) {
        return "MainLobby got: " + message;
    }
    
}
