package com.checkers_core.resp.response;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.resp.ResponseVisitor;

public class GameConnectionSuccessfulResponse extends Response {
    final int gameId;
    final VariantStartDescription desc;

    
    public GameConnectionSuccessfulResponse(int gameId, VariantStartDescription desc) {
        this.gameId = gameId;
        this.desc = desc;
    }
    public VariantStartDescription getDesc() {
        return desc;
    }
    
    public int getGameId() {
        return gameId;
    }

    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitGameConnectionSuccessful(this);    
    }
}
