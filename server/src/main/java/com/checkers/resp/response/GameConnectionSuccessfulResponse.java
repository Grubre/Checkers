package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public class GameConnectionSuccessfulResponse extends Response {
    final int gameId;

    public GameConnectionSuccessfulResponse(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public void accept(ResponseVisitor visitor) {
        visitor.visitGameConnectionSuccessful(this);    
    }
}
