package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class MovePieceCommand extends Command {
    final int pieceId;
    final int[] tileIds;

    public int getPieceId() {
        return pieceId;
    }

    public int[] getTileIds() {
        return tileIds;
    }

    public MovePieceCommand(int pieceId, int[] tileId) {
        this.pieceId = pieceId;
        this.tileIds = tileId;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visitMovePiece(this);    
    }
}
