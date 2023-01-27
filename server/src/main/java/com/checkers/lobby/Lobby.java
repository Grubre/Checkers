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

    
    /** 
     * @param playerId
     * @param listener
     */
    public void addPlayer(int playerId, ResponseListener listener) {
        connectedPlayers.put(playerId, listener);
    }

    
    /** 
     * @param playerId
     * @return ResponseListener
     */
    public ResponseListener getPlayer(int playerId) {
        return connectedPlayers.get(playerId);
    }

    
    /** 
     * @return int
     */
    public int getNumberOfPlayers() {
        return connectedPlayers.size();
    }

    
    /** 
     * @param playerId
     * @return ResponseListener
     */
    public ResponseListener removePlayer(int playerId) {
        return connectedPlayers.remove(playerId);
    }

    
    /** 
     * @param playerId
     * @param commPlayer
     * @param otherLobby
     */
    public void transferPlayerTo(int playerId, CommandSender commPlayer, Lobby otherLobby) {
        ResponseListener respPlayer = removePlayer(playerId);
        otherLobby.addPlayer(playerId, respPlayer);
        commPlayer.setListener(otherLobby);
    }

    
    /** 
     * @param playerId
     * @param response
     */
    public void sendToPlayer(int playerId, Response response) {
        connectedPlayers.get(playerId).onResponse(response);
    }

    
    /** 
     * @param response
     */
    public void broadcastToPlayers(Response response) {
        for (ResponseListener player : connectedPlayers.values()) {
            player.onResponse(response);
        }
    }

    
    /** 
     * @param command
     */
    @Override
    public void onCommand(Command command) {
        command.accept(this);
    }

    
    /** 
     * @param command
     * @return Void
     */
    @Override
    public Void onUnimplemented(Command command) {
        sendToPlayer(command.getPlayerId(), new WrongCommandResponse());
        return null;
    }
}
