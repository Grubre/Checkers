package com.checkers_core.resp.response;

import com.checkers_core.resp.ResponseVisitor;

public class EndOfGameResponse extends Response {
    final int winnerId;

    public EndOfGameResponse(int winnerId) {
        this.winnerId = winnerId;
    }

    
    /** 
     * @return int
     */
    public int getWinnerId() {
        return winnerId;
    }

    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitEndOfGame(this);
    }

}
