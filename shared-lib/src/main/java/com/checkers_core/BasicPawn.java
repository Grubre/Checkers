package com.checkers_core;

import java.util.ArrayList;

import com.checkers_core.Board.Color;

public class BasicPawn extends AbstractPawn {
    private int offset;
    public BasicPawn(Color color) {
        super(color);
        if(color == Color.WHITE)
            offset = 1;
        else
            offset = -1;
    }

    private void findMoveRecursive(Board board, ArrayList<Move> arrayList, Move parentMove, Board.BoardPos newPos)
    {
        Move move = new Move(parentMove);
        int x = newPos.x;
        int y = newPos.y;

        if(board.isInBounds(x, y))
        {
            if(board.getPiece(x, y) == null)
            {
                System.out.println("Pos: ["+x+"]["+y+"]");
                move.visitedFields.add(newPos);
                arrayList.add(move);
            }
        }
        return;

        // findMoveRecursive(board, arrayList, parentMove, new Board.BoardPos(x - 1, y + offset));
        // findMoveRecursive(board, arrayList, parentMove, new Board.BoardPos(x + 1, y + offset));
    }

    @Override
    public ArrayList<Move> possibleMoves(Board board, Board.BoardPos boardPos) {
        ArrayList<Move> moves = new ArrayList<Move>();
        int x = boardPos.x;
        int y = boardPos.y;

        System.out.println("Possible Moves:");

        Move move = new Move();
        findMoveRecursive(board, moves, move, new Board.BoardPos(x - 1, y + offset));
        findMoveRecursive(board, moves, move, new Board.BoardPos(x + 1, y + offset));

        return moves;
    }

    // @Override
    // public ArrayList<Move> possibleMoves(Board board, Board.BoardPos boardPos) {
    //     ArrayList<Move> moves = new ArrayList<Move>();
    //     int x = boardPos.x;
    //     int y = boardPos.y;
    //     Move move = new Move();
    //     if(x > 0 && y > 0 && board.getPiece(x - 1 ,y - 1) == null)
    //     {
    //         move.visitedFields.add(new Board.BoardPos(x - 1, y - 1));
    //     }
    //     if(x < board.xDim - 1 && y > 0 && board.getPiece(x + 1 ,y - 1) == null)
    //     {
    //         move.visitedFields.add(new Board.BoardPos(x + 1, y - 1));
    //     }
    //     moves.add(move);
    //     return moves;
    // }


    @Override
    public Boolean canAscend(Board board, Board.BoardPos boardPos) {
        if(color == Color.BLACK)
        {
            if(boardPos.y == 0)
                return true;
        }
        else if(color == Color.WHITE)
        {
            if(boardPos.y == board.xDim - 1)
                return true;
        }
        return false;
    }
    
    public Boolean isAscended()
    {
        return false;
    }
}
