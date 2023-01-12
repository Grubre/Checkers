package com.checkers_core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import com.checkers_core.Board.Color;

public class BasicPawn extends AbstractPawn {
    private int direction;
    public BasicPawn(Color color) {
        super(color);
        if(color == Color.WHITE)
            direction = 1;
        else
            direction = -1;
    }

    private void findMoveRecursive(Board board, ArrayList<Move> arrayList, Move parentMove, Board.BoardPos newPos)
    {
        Move move = new Move(parentMove);
        move.visitedFields.add(newPos);
        int x = newPos.x;
        int y = newPos.y;

        boolean foundMove = false;

        AbstractPawn leftPiece = board.getPiece(x - 1, y + direction);
        AbstractPawn rightPiece = board.getPiece(x + 1, y + direction);
        if(leftPiece != null && leftPiece.getColor() != color)
        {
            if(board.getPiece(x - 2, y + 2 * direction) == null)
            {
                findMoveRecursive(board, arrayList, move, new Board.BoardPos(x - 2, y + 2 * direction));
                foundMove = true;
            }
        }
        if(rightPiece != null && rightPiece.getColor() != color)
        {
            if(board.getPiece(x + 2, y + 2 * direction) == null)
            {
                findMoveRecursive(board, arrayList, move, new Board.BoardPos(x + 2, y + 2 * direction));
                foundMove = true;
            }
        }

        if(!foundMove && board.isInBounds(x, y))
        {
            System.out.println("Adding new move at: " + x + ", " + y);
            arrayList.add(move);
        }
    }

    @Override
    public ArrayList<Move> possibleMoves(Board board, Board.BoardPos boardPos) {
        ArrayList<Move> moves = new ArrayList<Move>();
        int x = boardPos.x;
        int y = boardPos.y;

        System.out.println("Possible Moves:");

        Move move = new Move();
        if(board.isInBounds(x - 1, y + direction) && board.getPiece(x - 1, y + direction) == null)
        {
            move.visitedFields.add(new Board.BoardPos(x - 1, y + direction));
            moves.add(move);
        }
        move = new Move();
        if(board.isInBounds(x + 1, y + direction) && board.getPiece(x + 1, y + direction) == null)
        {
            move.visitedFields.add(new Board.BoardPos(x + 1, y + direction));
            moves.add(move);
        }

        move = new Move();
        findMoveRecursive(board, moves, move, new Board.BoardPos(x, y));

        Optional<Move> maxSizeMove = moves.stream().max(Comparator.comparingInt(cmove -> cmove.visitedFields.size()));

        if(maxSizeMove.isPresent())
        {
            //int maxLen = maxSizeMove.get().visitedFields.size();
            //System.out.println("MaxSize = " + maxLen);
            // moves = new ArrayList<>(moves
                                    // .stream()
                                    // .filter(compMove -> compMove.visitedFields.size() >= maxLen)
                                    // .collect(Collectors.toList()));
        }

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
