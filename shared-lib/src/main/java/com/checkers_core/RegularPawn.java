package com.checkers_core;

import java.util.ArrayList;

import com.checkers_core.Board.Color;

public class RegularPawn extends AbstractPawn {    
    public RegularPawn(Color color) {
        super(color);
    }

    @Override
    public ArrayList<Move> possible_moves(Board board, Board.BoardPos boardPos) {
        ArrayList<Move> moves = new ArrayList<Move>();
        int x = boardPos.x;
        int y = boardPos.y;
        Move move = new Move();
        if(x > 0 && y > 0 && board.get_piece(x - 1 ,y - 1) == null)
        {
            move.visitedFields.add(new Board.BoardPos(x - 1, y - 1));
        }
        if(x < board.x_dim - 1 && y > 0 && board.get_piece(x + 1 ,y - 1) == null)
        {
            move.visitedFields.add(new Board.BoardPos(x + 1, y - 1));
        }
        moves.add(move);
        return moves;
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
    
    public Boolean is_ascended()
    {
        return false;
    }
}
