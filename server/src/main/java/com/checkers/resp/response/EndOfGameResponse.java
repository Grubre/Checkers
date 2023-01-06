package com.checkers.resp.response;

public class EndOfGameResponse extends Response {
    final int winnerId;

    public EndOfGameResponse(int winnerId) {
        this.winnerId = winnerId;
    }
}
