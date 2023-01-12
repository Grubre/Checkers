package com.checkers_core;

import java.util.ArrayList;
import java.util.List;

import com.checkers_core.Board.BoardPos;

public abstract class AbstractPawn {
    public static class Move {
        public Move()
        {
            visitedFields = new ArrayList<>();
            removedPawns = new ArrayList<>();
        }
        public Move(Move move)
        {
            visitedFields = new ArrayList<>(move.visitedFields);
            removedPawns = new ArrayList<>(move.removedPawns);
        }
        public Move(List<BoardPos> visitedFields, List<BoardPos> removedPawns) {
            this.visitedFields = visitedFields;
            this.removedPawns = removedPawns;
        }
        public List<Board.BoardPos> visitedFields;
        public List<Board.BoardPos> removedPawns;
    }

    protected Board.Color color;

    AbstractPawn(Board.Color color)
    {
        this.color = color;
    }

    public Board.Color getColor()
    {
        return color;
    }

    public boolean isEnemy(AbstractPawn otherPawn)
    {
        return otherPawn.getColor() != this.color;
    }

    public abstract List<Move> possibleMoves(Board board, Board.BoardPos boardPos);
    public abstract Boolean isAscended();
    public abstract Boolean canAscend(Board board, Board.BoardPos boardPos);
}
