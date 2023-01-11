package com.checkers_core;

import java.util.ArrayList;

public abstract class AbstractPawn {
    public static class Move {
        public Move()
        {
            visitedFields = new ArrayList<>();
            removedPawns = new ArrayList<>();
        }
        public ArrayList<Board.BoardPos> visitedFields;
        public ArrayList<AbstractPawn> removedPawns;
    }

    protected Board.Color color;

    AbstractPawn(Board.Color color)
    {
        this.color = color;
    }

    public Board.Color get_color()
    {
        return color;
    }

    public boolean is_enemy(AbstractPawn otherPawn)
    {
        return otherPawn.get_color() != this.color;
    }

    public abstract ArrayList<Move> possible_moves(Board board, Board.BoardPos boardPos);
    public abstract Boolean is_ascended();
    public abstract Boolean can_ascend(Board board, Board.BoardPos boardPos);
}
