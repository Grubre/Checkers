package com.checkers.lobby;

public class GameLobby implements AbstractLobby {

    @Override
    public String processMessage(String message) {
        return "GameLobby got: " + message;
    }
    
}
