package com.checkers_core;

import java.util.ArrayList;

import com.checkers_core.Board.Color;

public class BasicAscendedPawn extends AbstractPawn {
    private int direction;

    public BasicAscendedPawn(Color color) {
        super(color);
        if (color == Color.WHITE)
            direction = 1;
        else
            direction = -1;
    }

    @Override
    public MoveNode possibleMoves(Board board, Board.BoardPos boardPos) {
        int x = boardPos.x;
        int y = boardPos.y;
        
        BasicMoveFinder basicMoveFinder = new BasicMoveFinder(board, boardPos, direction, 25);

        basicMoveFinder.findCaptures();

        if(basicMoveFinder.foundCaptures())
        {
            return basicMoveFinder.getMoveNode();
        }

        basicMoveFinder.findMoves();
        
        return basicMoveFinder.getMoveNode();
    }

    @Override
    public Boolean canAscend(Board board, Board.BoardPos boardPos) {
        return false;
    }
    
    public Boolean isAscended()
    {
        return true;
    }
}
