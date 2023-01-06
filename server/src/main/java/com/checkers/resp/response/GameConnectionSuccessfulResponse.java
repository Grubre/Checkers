package com.checkers.resp.response;

public class GameConnectionSuccessfulResponse extends Response {
    final int gameId;

    public GameConnectionSuccessfulResponse(int gameId) {
        this.gameId = gameId;
    }
}
