package com.checkers.resp.response;

public class PieceMovedResponse extends Response {
    final int playerId;
    final int pieceId;
    final int tileIds[];
    
    public PieceMovedResponse(int playerId, int pieceId, int[] tileIds) {
        this.playerId = playerId;
        this.pieceId = pieceId;
        this.tileIds = tileIds;
    }
}
