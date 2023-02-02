package com.checkers.lobby;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.LobbyListResponse;
import com.checkers_core.resp.response.Response;

public class Hub extends Lobby {
    Map<Integer, GameLobby> openLobbies = new TreeMap<>();
    int internalIdCounter = 1;

    public int createLobby(VariantStartDescription desc) {
        GameLobby newLobby = new GameLobby(this, desc);

        int gameId = internalIdCounter;
        internalIdCounter++;

        openLobbies.put(gameId, newLobby);

        return gameId;
    }

    public GameLobby getLobby(int gameId) {
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
        int newGameId = createLobby(command.getDesc());
        int playerId = command.getPlayerId();

        Lobby newLobby = getLobby(newGameId);
    
        sendToPlayer(playerId, new GameConnectionSuccessfulResponse(newGameId, command.getDesc(), playerId));
        
        transferPlayerTo(playerId, newLobby);

        return null;
    }

    @Override
    public Void visitJoinGame(JoinGameCommand command) {
        int gameId = command.getGameId();
        int playerId = command.getPlayerId();
        
        GameLobby lobby = getLobby(gameId);

        if (lobby != null) {
            sendToPlayer(playerId, new GameConnectionSuccessfulResponse(gameId, lobby.getAnotherColoredDesc(), playerId));
            transferPlayerTo(playerId, lobby);
        }
        else {
            sendToPlayer(playerId, new GameConnectionUnsuccessfulResponse());
        }

        return null;
    }

    public Void visitListLobby(ListLobbyCommand command) {

        Response resp = new LobbyListResponse(new ArrayList<>(openLobbies.keySet()));

        int playerId = command.getPlayerId();

        sendToPlayer(playerId, resp);

        return null;
    }

    @Override
    public Void visitDisconnect(DisconnectCommand command) {
        removePlayer(command.getPlayerId());

        return null;
    }
}
