package com.checkers_core.resp.response;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.resp.ResponseVisitor;

public class GameConnectionSuccessfulResponse extends Response {
    final int gameId;
    final int playerId;
    final VariantStartDescription desc;
    
    public GameConnectionSuccessfulResponse(int gameId, VariantStartDescription desc, int playerId) {
        this.gameId = gameId;
        this.desc = desc;
        this.playerId = playerId;
    }
    public VariantStartDescription getDesc() {
        return desc;
    }

    public int getPlayerId() {
        return playerId;
    }
    
    public int getGameId() {
        return gameId;
    }

    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitGameConnectionSuccessful(this);    
    }
}
