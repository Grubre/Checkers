package com.checkers.connection;

import com.checkers.lobby.GameLobby;
import com.checkers.lobby.LobbyRouter;

public class ConnectionController {

    Connection connection;
    LobbyRouter router;
    GameLobby lobby;

    ConnectionController(Connection connection, LobbyRouter router) {
        this.connection = connection;
        this.router = router;
        connection.setController(this);
    }

    public void processMessage(String message) {
        if(message.startsWith("NEWLOBBY")) {
            int id = router.createNewLobby();
            lobby = router.getLobby(id);
            connection.send("Created lobby with id " + id);
        }
        else if(message.startsWith("JOINLOBBY")) {
            lobby = router.getLobby(Integer.parseInt(message.substring(10)));
            connection.send("Connected to lobby");
        }
        else if(lobby != null) {
            lobby.processMessage(message);
        }
    }
}
