package com.checkers;

import java.util.ArrayList;

public abstract class AbstractPawn {
    private Board.Color color;

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

    public abstract ArrayList<Integer> possible_moves();
    public abstract Boolean can_ascend();
}
