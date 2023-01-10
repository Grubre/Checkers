package com.checkers_core;

import java.util.ArrayList;

import com.checkers_core.Board.BoardPos;
import com.checkers_core.Board.Color;

public class AscendedPawn extends AbstractPawn {
    public AscendedPawn(Color color) {
        super(color);
    }

    @Override
    public ArrayList<Move> possible_moves(Board board, Board.BoardPos boardPos) {
        ArrayList<Move> moves = new ArrayList<Move>();
        int x = boardPos.x;
        int y = boardPos.y;
        Move move = new Move();
        for(int i = x; i >= 0; i--)
        {
            for(int j = y; j >= 0; j--)
            {
                move.visitedFields.add(board.new BoardPos(i, j));
            }
        }
        moves.add(move);
        return moves;
    }

    @Override
    public Boolean can_ascend(Board board, Board.BoardPos boardPos) {
        return false;
    }
    
    public Boolean is_ascended()
    {
        return true;
    }
}
