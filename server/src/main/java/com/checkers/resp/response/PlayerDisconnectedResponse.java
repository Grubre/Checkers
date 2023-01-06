package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public class PlayerDisconnectedResponse extends Response {
    final int playerId;

    
    public PlayerDisconnectedResponse(int playerId) {
        this.playerId = playerId;
    }
    public int getPlayerId() {
        return playerId;
    }
    @Override
    public void accept(ResponseVisitor visitor) {
        visitor.visitPlayerDisconnected(this);    
    }

    
}
