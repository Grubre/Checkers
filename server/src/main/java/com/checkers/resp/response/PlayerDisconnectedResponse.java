package com.checkers.resp.response;

public class PlayerDisconnectedResponse extends Response {
    final int playerId;

    public PlayerDisconnectedResponse(int playerId) {
        this.playerId = playerId;
    }
}
