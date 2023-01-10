package com.checkers.comm.command;

import java.util.Collection;
import java.util.List;

import com.checkers.comm.CommandVisitor;

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
    public void accept(CommandVisitor visitor) {
        visitor.visitMovePiece(this);    
    }
}
