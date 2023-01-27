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

    
    /** 
     * @return int
     */
    public int getPlayerId() {
        return playerId;
    }

    
    /** 
     * @return int
     */
    public int getPieceX() {
        return pieceX;
    }

    
    /** 
     * @return int
     */
    public int getPieceY() {
        return pieceY;
    }

    
    /** 
     * @return List<Integer>
     */
    public List<Integer> getTileIds() {
        return tileIds;
    }

    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitPieceMoved(this);
    }

}
