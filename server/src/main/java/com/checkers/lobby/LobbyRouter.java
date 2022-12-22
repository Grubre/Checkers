package com.checkers.lobby;

import java.util.ArrayList;
import java.util.List;

public class LobbyRouter {

    List<GameLobby> lobbies = new ArrayList<GameLobby>(); 

    public int createNewLobby() {
        lobbies.add(new GameLobby());
        return lobbies.size() - 1;
    }

    public GameLobby getLobby(int id) {
        return lobbies.get(id);
    }
    
}
