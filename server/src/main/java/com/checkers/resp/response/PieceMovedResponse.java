package com.checkers.resp.response;

import java.util.List;

import com.checkers.resp.ResponseVisitor;

public class PieceMovedResponse extends Response {
    final int playerId;
    final int pieceId;
    final List<Integer> tileIds;
    
    public PieceMovedResponse(int playerId, int pieceId, List<Integer> tileIds) {
        this.playerId = playerId;
        this.pieceId = pieceId;
        this.tileIds = tileIds;
    }
    public int getPlayerId() {
        return playerId;
    }
    public int getPieceId() {
        return pieceId;
    }
    public List<Integer> getTileIds() {
        return tileIds;
    }
    @Override
    public void accept(ResponseVisitor visitor) {
        visitor.visitPieceMoved(this);    
    }
    
}
