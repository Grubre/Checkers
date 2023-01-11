package com.checkers_core.resp.response;

import java.util.List;

import com.checkers_core.resp.ResponseVisitor;

public class LobbyListResponse extends Response {
    List<Integer> lobby_ids;

    public LobbyListResponse(List<Integer> lobbyIds) {
        this.lobby_ids = lobbyIds;
    }

    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitLobbyList(this);
    }
}
