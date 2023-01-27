package com.checkers_core;

public abstract class AbstractPawn {
    protected Board.Color color;

    AbstractPawn(Board.Color color)
    {
        this.color = color;
    }

    
    /** 
     * @return Color
     */
    public Board.Color getColor()
    {
        return color;
    }

    
    /** 
     * @param otherPawn
     * @return boolean
     */
    public boolean isEnemy(AbstractPawn otherPawn)
    {
        return otherPawn.getColor() != this.color;
    }

    public abstract MoveNode possibleMoves(Board board, Board.BoardPos boardPos);
    public abstract Boolean isAscended();
    public abstract Boolean canAscend(Board board, Board.BoardPos boardPos);
}
