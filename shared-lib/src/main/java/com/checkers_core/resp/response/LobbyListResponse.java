package com.checkers_core.resp.response;

import java.util.List;

import com.checkers_core.resp.ResponseVisitor;

public class LobbyListResponse extends Response {
    final List<Integer> lobbyIds;

    
    /** 
     * @return List<Integer>
     */
    public List<Integer> getLobbyIds() {
        return lobbyIds;
    }

    public LobbyListResponse(List<Integer> lobbyIds) {
        this.lobbyIds = lobbyIds;
    }

    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitLobbyList(this);
    }
}
