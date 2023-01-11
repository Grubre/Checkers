package com.checkers.lobby;

import java.util.Map;
import java.util.TreeMap;

import com.checkers.comm.command.DisconnectCommand;
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
    public Void visitNewGame(NewGameCommand command) {
        int newGameId = createLobby();
        int playerId = command.getPlayerId();

        Lobby newLobby = getLobby(newGameId);
    
        sendToPlayer(playerId, new GameConnectionSuccessfulResponse(newGameId));
        
        transferPlayerTo(playerId, command.getSource(), newLobby);

        return null;
    }

    @Override
    public Void visitJoinGame(JoinGameCommand command) {
        int gameId = command.getGameId();
        int playerId = command.getPlayerId();
        
        Lobby lobby = getLobby(gameId);

        if (lobby != null) {
            sendToPlayer(playerId, new GameConnectionSuccessfulResponse(gameId));
            transferPlayerTo(playerId, command.getSource(), lobby);
        }
        else {
            sendToPlayer(playerId, new GameConnectionUnsuccessfulResponse());
        }

        return null;
    }

    @Override
    public Void visitDisconnect(DisconnectCommand command) {
        removePlayer(command.getPlayerId());

        return null;
    }
}
