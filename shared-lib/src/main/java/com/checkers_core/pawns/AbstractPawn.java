package com.checkers_core.pawns;

import com.checkers_core.boards.Board;
import com.checkers_core.moves.MoveNode;

public abstract class AbstractPawn implements Cloneable {
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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract MoveNode possibleMoves(Board board, Board.BoardPos boardPos);
    public abstract Boolean isAscended();
    public abstract Boolean canAscend(Board board, Board.BoardPos boardPos);
}
