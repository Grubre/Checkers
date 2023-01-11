package com.checkers_core.comm.command;

import java.util.List;

import com.checkers_core.comm.CommandVisitor;

public class MovePieceCommand extends Command {
    final int pieceId;
    final List<Integer> tileIds;

    public int getPieceId() {
        return pieceId;
    }

    public List<Integer> getTileIds() {
        return tileIds;
    }

    public MovePieceCommand(int pieceId, List<Integer> tileId) {
        this.pieceId = pieceId;
        this.tileIds = tileId;
    }

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitMovePiece(this);    
    }
}
