package com.checkers.lobby;

import java.util.Map;
import java.util.TreeMap;

import com.checkers.comm.command.JoinGameCommand;
import com.checkers.comm.command.NewGameCommand;
import com.checkers.resp.response.GameConnectionSuccessfulResponse;
import com.checkers.resp.response.GameConnectionUnsuccessfulResponse;

public class Hub extends Lobby {
    Map<Integer, GameLobby> openLobbies = new TreeMap<>();
    int internalIdCounter = 1;

    public int createLobby() {
        GameLobby newLobby = new GameLobby(this);

        int gameId = internalIdCounter;
        internalIdCounter++;

        openLobbies.put(gameId, newLobby);

        return gameId;
    }

    public Lobby getLobby(int gameId) {
        return openLobbies.get(gameId);
    }

    public void closeLobby(int gameId) {
        openLobbies.remove(gameId);
    }

    public void closeLobby(Lobby lobby) {
        openLobbies.values().remove(lobby);
    }

    @Override
    public void visitNewGame(NewGameCommand command) {
        int newGameId = createLobby();
        int playerId = command.getPlayerId();

        Lobby newLobby = getLobby(newGameId);
    
        transferPlayerTo(playerId, command.getSource(), newLobby);

        sendToPlayer(playerId, new GameConnectionSuccessfulResponse(newGameId));
    }

    @Override
    public void visitJoinGame(JoinGameCommand command) {
        int gameId = command.getGameId();
        int playerId = command.getPlayerId();
        
        Lobby lobby = getLobby(gameId);

        if (lobby != null) {
            transferPlayerTo(playerId, command.getSource(), lobby);
            sendToPlayer(playerId, new GameConnectionSuccessfulResponse(gameId));
        }
        else {
            sendToPlayer(playerId, new GameConnectionUnsuccessfulResponse());
        }
        
    }
}
