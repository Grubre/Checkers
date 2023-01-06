package com.checkers.resp.response;

import java.util.Collection;

public class PieceMovedResponse extends Response {
    final int playerId;
    final int pieceId;
    final Collection<Integer> tileIds;

    public PieceMovedResponse(int playerId, int pieceId, Collection<Integer> tileIds) {
        this.playerId = playerId;
        this.pieceId = pieceId;
        this.tileIds = tileIds;
    }
}
