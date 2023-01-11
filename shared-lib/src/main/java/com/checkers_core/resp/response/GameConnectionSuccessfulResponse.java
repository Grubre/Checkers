package com.checkers_core.resp.response;

import com.checkers_core.resp.ResponseVisitor;

public class GameConnectionSuccessfulResponse extends Response {
    final int gameId;

    public GameConnectionSuccessfulResponse(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitGameConnectionSuccessful(this);    
    }
}
