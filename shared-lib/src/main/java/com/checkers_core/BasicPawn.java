package com.checkers_core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.checkers_core.Board.BoardPos;
import com.checkers_core.Board.Color;

public class BasicPawn extends AbstractPawn {
    private int direction;

    public BasicPawn(Color color) {
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
        
        BasicMoveFinder basicMoveFinder = new BasicMoveFinder(board, boardPos, direction, 1);

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
        if (color == Color.BLACK) {
            if (boardPos.y == 0) {   
                return true;
            }
        } else if (color == Color.WHITE) {
            if (boardPos.y == board.xDim - 1) { 
                return true;
            }
        }
        return false;
    }

    public Boolean isAscended() {
        return false;
    }
}
