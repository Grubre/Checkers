package com.checkers_core;

import java.util.ArrayList;

import com.checkers_core.Board.Color;

public class BasicAscendedPawn extends AbstractPawn {
    public BasicAscendedPawn(Color color) {
        super(color);
    }

    @Override
    public MoveNode possibleMoves(Board board, Board.BoardPos boardPos) {
        return null;
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
