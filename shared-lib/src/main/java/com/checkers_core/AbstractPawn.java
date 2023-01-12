package com.checkers_core;

import java.util.ArrayList;

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
        public ArrayList<Board.BoardPos> visitedFields;
        public ArrayList<Board.BoardPos> removedPawns;
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

    public abstract ArrayList<Move> possibleMoves(Board board, Board.BoardPos boardPos);
    public abstract Boolean isAscended();
    public abstract Boolean canAscend(Board board, Board.BoardPos boardPos);
}
