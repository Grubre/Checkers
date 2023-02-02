package com.checkers.lobby;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.checkers.connection.BotConnection;
import com.checkers.database.DatabaseUtil;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.boards.Board;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.comm.command.ListReplaysCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.comm.command.WatchReplayCommand;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.LobbyListResponse;
import com.checkers_core.resp.response.Response;

public class Hub extends Lobby {
    Map<Integer, Lobby> openLobbies = new TreeMap<>();
    int internalIdCounter = 1;

    public int createLobby(VariantStartDescription desc) {
        int gameId = internalIdCounter;
        internalIdCounter++;

        GameLobby newLobby = new GameLobby(this, desc, gameId);

        synchronized(openLobbies) {
            openLobbies.put(gameId, newLobby);
        }

        return gameId;
    }

    public int createReplayLobby(int matchId) {
        int gameId = internalIdCounter;
        internalIdCounter++;

        RecordLobby newLobby = new RecordLobby(this, matchId, gameId);

        synchronized(openLobbies) {
            openLobbies.put(gameId, newLobby);
        }

        return gameId;
    }

    public Lobby getLobby(int gameId) {
        synchronized(openLobbies) {
            return openLobbies.get(gameId);
        }
    }

    public void closeLobby(int gameId) {
        synchronized(openLobbies) {
            openLobbies.remove(gameId);
        }
    }

    public void closeLobby(Lobby lobby) {
        synchronized(openLobbies) {
            openLobbies.values().remove(lobby);
        }
    }

    @Override
    public Void visitNewGame(NewGameCommand command) {
        int newGameId = createLobby(command.getDesc());
        int playerId = command.getPlayerId();

        Lobby newLobby = getLobby(newGameId);
        
        transferPlayerTo(playerId, newLobby);

        if (command.isWithBot()) {
            VariantStartDescription desc = command.getDesc();
            VariantStartDescription botDesc = new VariantStartDescription(desc.getWidth(), desc.getHeight(), desc.getName(), Board.Color.getOpposite(Board.Color.fromString(desc.getColor())).toString());
            BotConnection bot = new BotConnection(botDesc);
            newLobby.addPlayer(-1, bot);
        }

        return null;
    }

    @Override
    public Void visitJoinGame(JoinGameCommand command) {
        int gameId = command.getGameId();
        int playerId = command.getPlayerId();
        
        Lobby lobby = getLobby(gameId);

        if (lobby != null) {
            transferPlayerTo(playerId, lobby);
        }
        else {
            sendToPlayer(playerId, new GameConnectionUnsuccessfulResponse());
        }

        return null;
    }

    @Override
    public Void visitWatchReplayCommand(WatchReplayCommand command) {
        int newGameId = createReplayLobby(command.getMatchId());
        int playerId = command.getPlayerId();

        Lobby newLobby = getLobby(newGameId);
        
        transferPlayerTo(playerId, newLobby);

        return null;
    }

    @Override
    public Void visitListLobby(ListLobbyCommand command) {
        synchronized(openLobbies) {
            Response resp = new LobbyListResponse(new ArrayList<>(openLobbies.keySet()));
            int playerId = command.getPlayerId();
    
            sendToPlayer(playerId, resp);
    
            return null;
        }
    }

    @Override
    public Void visitListReplaysCommand(ListReplaysCommand command) {
        Response resp = new LobbyListResponse(DatabaseUtil.getSavedMatches());
        System.out.println(DatabaseUtil.getSavedMatches());
        sendToPlayer(command.getPlayerId(), resp);
        return null;
        
    }

    @Override
    public Void visitDisconnect(DisconnectCommand command) {
        removePlayer(command.getPlayerId());

        return null;
    }
}
