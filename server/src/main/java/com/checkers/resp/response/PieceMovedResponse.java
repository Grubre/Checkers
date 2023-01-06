package com.checkers.resp.response;

import java.util.Collection;
import java.util.List;

public class PieceMovedResponse extends Response {
    final int playerId;
    final int pieceId;
    final List<Integer> tileIds;

    public PieceMovedResponse(int playerId, int pieceId, List<Integer> tileIds) {
        this.playerId = playerId;
        this.pieceId = pieceId;
        this.tileIds = tileIds;
    }
}
