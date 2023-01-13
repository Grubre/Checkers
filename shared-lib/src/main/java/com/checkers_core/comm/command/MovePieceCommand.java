package com.checkers_core.comm.command;

import java.util.List;

import com.checkers_core.comm.CommandVisitor;

public class MovePieceCommand extends Command {
    final int pieceX;
    final int pieceY;
    final List<Integer> tileIds;

    public List<Integer> getTileIds() {
        return tileIds;
    }

    public int getPieceX() {
        return pieceX;
    }

    public int getPieceY() {
        return pieceY;
    }

    public MovePieceCommand(int pieceX, int pieceY, List<Integer> tileId) {
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.tileIds = tileId;
    }

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitMovePiece(this);
    }
}
