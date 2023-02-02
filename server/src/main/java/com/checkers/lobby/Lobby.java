package com.checkers.lobby;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

import com.checkers_core.comm.command.Command;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.response.Response;
import com.checkers_core.resp.response.WrongCommandResponse;
import com.checkers.connection.PlayerConnection;
import com.checkers_core.comm.CommandListener;
import com.checkers_core.comm.CommandVisitor;

public class Lobby implements CommandListener, CommandVisitor<Void> {
    private Map<Integer, PlayerConnection> connectedPlayers = new TreeMap<>();

    public void addPlayer(int playerId, PlayerConnection player) {
        connectedPlayers.put(playerId, player);
        player.setListener(this);
        onPlayerJoin(playerId);
    }

    public PlayerConnection getPlayer(int playerId) {
        return connectedPlayers.get(playerId);
    }

    public PlayerConnection removePlayer(int playerId) {
        PlayerConnection player = connectedPlayers.remove(playerId);
        player.setListener(null);
        return player;
    }

    public int getNumberOfPlayers() {
        return connectedPlayers.size();
    }
    
    public void transferPlayerTo(int playerId, Lobby otherLobby) {
        PlayerConnection player = removePlayer(playerId);
        otherLobby.addPlayer(playerId, player);
    }

    public void sendToPlayer(int playerId, Response response) {
        connectedPlayers.get(playerId).onResponse(response);
    }

    public void broadcastToPlayers(Predicate<Integer> test, Response response) {
        connectedPlayers.forEach((id, player) -> {
            if (test.test(id)) {
                player.onResponse(response);
            }
        });
    }

    public void broadcastToAllPlayers(Response response) {
        for (ResponseListener player : connectedPlayers.values()) {
            player.onResponse(response);
        }
    }

    public void onPlayerJoin(int playerId) {}

    @Override
    public void onCommand(Command command) {
        command.accept(this);
    }

    @Override
    public Void onUnimplemented(Command command) {
        sendToPlayer(command.getPlayerId(), new WrongCommandResponse());
        return null;
    }
}
