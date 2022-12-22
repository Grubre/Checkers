package com.checkers_core;

import java.util.ArrayList;

import com.checkers_core.Board.Color;

public class RegularPawn extends AbstractPawn {
    
    public RegularPawn(Color color) {
        super(color);
    }

    @Override
    public ArrayList<ArrayList<Board.BoardPos>> possible_moves(Board board, Board.BoardPos boardPos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean can_ascend(Board board, Board.BoardPos boardPos) {
        if(color == Color.BLACK)
        {
            if(boardPos.y == 0)
                return true;
        }
        else if(color == Color.WHITE)
        {
            if(boardPos.y == board.x_dim - 1)
                return true;
        }
        return false;
    }
    
}
