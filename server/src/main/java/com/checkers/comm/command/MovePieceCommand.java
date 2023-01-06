package com.checkers.comm.command;

import java.util.Collection;

import com.checkers.comm.CommandVisitor;

public class MovePieceCommand extends Command {
    final int pieceId;
    final Collection<Integer> tileIds;

    public int getPieceId() {
        return pieceId;
    }

    public Collection<Integer> getTileIds() {
        return tileIds;
    }

    public MovePieceCommand(int pieceId, Collection<Integer> tileId) {
        this.pieceId = pieceId;
        this.tileIds = tileId;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visitMovePiece(this);    
    }
}
