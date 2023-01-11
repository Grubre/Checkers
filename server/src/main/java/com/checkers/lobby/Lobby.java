package com.checkers.lobby;

import java.util.Map;
import java.util.TreeMap;

import com.checkers_core.comm.command.Command;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.response.Response;
import com.checkers_core.resp.response.WrongCommandResponse;
import com.checkers_core.comm.CommandListener;
import com.checkers_core.comm.CommandSender;
import com.checkers_core.comm.CommandVisitor;

public class Lobby implements CommandListener, CommandVisitor<Void> {
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

    public void transferPlayerTo(int playerId, CommandSender commPlayer, Lobby otherLobby) {
        ResponseListener respPlayer = removePlayer(playerId);
        otherLobby.addPlayer(playerId, respPlayer);
        commPlayer.setListener(otherLobby);
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
    public Void onUnimplemented(Command command) {
        sendToPlayer(command.getPlayerId(), new WrongCommandResponse());
        return null;
    }
}
