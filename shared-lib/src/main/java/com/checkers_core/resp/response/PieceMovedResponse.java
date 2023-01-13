package com.checkers_core.resp.response;

import java.util.List;

import com.checkers_core.resp.ResponseVisitor;

public class PieceMovedResponse extends Response {
    final int playerId;
    final int pieceX;
    final int pieceY;
    final List<Integer> tileIds;

    public PieceMovedResponse(int playerId, int pieceX, int pieceY, List<Integer> tileIds) {
        this.playerId = playerId;
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.tileIds = tileIds;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPieceX() {
        return pieceX;
    }

    public int getPieceY() {
        return pieceY;
    }

    public List<Integer> getTileIds() {
        return tileIds;
    }

    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitPieceMoved(this);
    }

}
