package com.checkers_core;

import java.util.ArrayList;

import com.checkers_core.Board.Color;

public class BasicAscendedPawn extends AbstractPawn {
    public BasicAscendedPawn(Color color) {
        super(color);
    }

    @Override
    public ArrayList<Move> possibleMoves(Board board, Board.BoardPos boardPos) {
        ArrayList<Move> moves = new ArrayList<Move>();
        int x = boardPos.x;
        int y = boardPos.y;
        Move move = new Move();
        for(int i = x; i >= 0; i--)
        {
            for(int j = y; j >= 0; j--)
            {
                move.visitedFields.add(new Board.BoardPos(i, j));
            }
        }
        moves.add(move);
        return moves;
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