package com.checkers_core.resp.response;

import java.util.List;

import com.checkers_core.resp.ResponseVisitor;

public class LobbyListResponse extends Response {
    final List<Integer> lobbyIds;

    public List<Integer> getLobbyIds() {
        return lobbyIds;
    }

    public LobbyListResponse(List<Integer> lobbyIds) {
        this.lobbyIds = lobbyIds;
    }

    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitLobbyList(this);
    }
}
