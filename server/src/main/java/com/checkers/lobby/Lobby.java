package com.checkers.lobby;

import java.util.Map;
import java.util.TreeMap;

import com.checkers.comm.CommandListener;
import com.checkers.comm.CommandVisitor;
import com.checkers.comm.command.Command;
import com.checkers.resp.ResponseListener;
import com.checkers.resp.response.Response;
import com.checkers.resp.response.WrongCommandResponse;

public class Lobby implements CommandListener, CommandVisitor {
    Map<Integer, ResponseListener> connectedPlayers = new TreeMap<>();

    public void addPlayer(int playerId, ResponseListener listener) {
        connectedPlayers.put(playerId, listener);
    }

    public ResponseListener getPlayer(int playerId) {
        return connectedPlayers.get(playerId);
    }

    public int getNumberOfPlayers() {
        return connectedPlayers.size();
    }

    public ResponseListener removePlayer(int playerId) {
        return connectedPlayers.remove(playerId);
    }

    public void transferPlayerTo(int playerId, Lobby otherLobby) {
        ResponseListener player = removePlayer(playerId);
        otherLobby.addPlayer(playerId, player);
    }

    public void sendToPlayer(int playerId, Response response) {
        connectedPlayers.get(playerId).onResponse(response);
    }

    public void broadcastToPlayers(Response response) {
        for (ResponseListener player : connectedPlayers.values()) {
            player.onResponse(response);
        }
    }

    @Override
    public void onCommand(Command command) {
        command.accept(this);
    }

    @Override
    public void onUnimplemented(Command command) {
        sendToPlayer(command.getPlayerId(), new WrongCommandResponse());
    }
}
