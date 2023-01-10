package com.checkers_core;

import java.util.ArrayList;

public abstract class AbstractPawn {
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

    public abstract ArrayList<ArrayList<Board.BoardPos>> possible_moves(Board board, Board.BoardPos boardPos);
    public abstract Boolean is_ascended();
    public abstract Boolean can_ascend(Board board, Board.BoardPos boardPos);
}
