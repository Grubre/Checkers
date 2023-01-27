package com.checkers_core.resp.response;

import com.checkers_core.resp.ResponseVisitor;

public class PlayerDisconnectedResponse extends Response {
    final int playerId;

    
    public PlayerDisconnectedResponse(int playerId) {
        this.playerId = playerId;
    }
    
    /** 
     * @return int
     */
    public int getPlayerId() {
        return playerId;
    }
    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitPlayerDisconnected(this);    
    }

    
}
