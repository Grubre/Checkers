package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public class EndOfGameResponse extends Response {
    final int winnerId;

    public EndOfGameResponse(int winnerId) {
        this.winnerId = winnerId;
    }

    public int getWinnerId() {
        return winnerId;
    }

    @Override
    public void accept(ResponseVisitor visitor) {
        visitor.visitEndOfGame(this);
    }

}
