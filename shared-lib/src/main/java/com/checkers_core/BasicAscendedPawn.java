package com.checkers_core;

import com.checkers_core.Board.Color;

public class BasicAscendedPawn extends AbstractPawn {
    private int direction;

    public BasicAscendedPawn(Color color) {
        super(color);
        if (color == Color.WHITE) {
            direction = 1;
        }
        else {
            direction = -1;
        }
    }

    
    /** 
     * @param board
     * @param boardPos
     * @return MoveNode
     */
    @Override
    public MoveNode possibleMoves(Board board, Board.BoardPos boardPos) {
        BasicMoveFinder basicMoveFinder = new BasicMoveFinder(board, boardPos, direction, 1000);

        basicMoveFinder.findCaptures();

        if(basicMoveFinder.foundCaptures())
        {
            return basicMoveFinder.getMoveNode();
        }

        basicMoveFinder.findMoves();
        
        return basicMoveFinder.getMoveNode();
    }

    
    /** 
     * @param board
     * @param boardPos
     * @return Boolean
     */
    @Override
    public Boolean canAscend(Board board, Board.BoardPos boardPos) {
        return false;
    }
    
    
    /** 
     * @return Boolean
     */
    public Boolean isAscended()
    {
        return true;
    }
}
