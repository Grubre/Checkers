package com.checkers_core.comm.command;

import java.util.List;

import com.checkers_core.comm.CommandVisitor;

public class MovePieceCommand extends Command {
    final int pieceX;
    final int pieceY;
    final List<Integer> tileIds;

    
    /** 
     * @return List<Integer>
     */
    public List<Integer> getTileIds() {
        return tileIds;
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

    public MovePieceCommand(int pieceX, int pieceY, List<Integer> tileId) {
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.tileIds = tileId;
    }

    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitMovePiece(this);
    }
}
