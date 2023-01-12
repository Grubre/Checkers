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

    private MoveNode findMoveRecursive(Board board, MoveNode parentMove, BoardPos newPos, BoardPos removedPiece) {
        int x = newPos.x;
        int y = newPos.y;

        if (board.isInBounds(x, y) && (board.getPiece(x, y) == null || board.getPiece(x, y) == this)) {
            MoveNode move = new MoveNode(x, y, new ArrayList<>(parentMove.removedPawns));
            move.removedPawns.add(removedPiece);

            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    AbstractPawn triedPiece = board.getPiece(x + i, y + j);
                    if (triedPiece != null && triedPiece.getColor() != color && !move.removedPawns.contains(new Board.BoardPos(x + i, y + j))) {
                        MoveNode newMove = findMoveRecursive(board, move, new BoardPos(x + 2 * i, y + 2 * j), new BoardPos(x + i, y + j));
                        if (newMove != null) {
                            move.possibleMoves.add(newMove);
                        }
                    }
                }
            }

            return move;
        }

        return null;
    }

    private MoveNode findMoveRecursive(Board board, Board.BoardPos newPos) {
        int x = newPos.x;
        int y = newPos.y;

        if (board.isInBounds(x, y) && (board.getPiece(x, y) == null || board.getPiece(x, y) == this)) {
            MoveNode move = new MoveNode(x, y, new ArrayList<>());

            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    if (board.getPiece(x + i, y + j) != null && board.getPiece(x + i, y + j).getColor() != color) {
                        MoveNode newMove = findMoveRecursive(board, move, new BoardPos(x + 2 * i, y + 2 * j), new BoardPos(x + i, y + j));
                        if (newMove != null) {
                            move.possibleMoves.add(newMove);
                        }
                    }
                }
            }

            return move;
        }

        return null;
    }

    @Override
    public MoveNode possibleMoves(Board board, Board.BoardPos boardPos) {
        int x = boardPos.x;
        int y = boardPos.y;
        MoveNode moveGraph = findMoveRecursive(board, new Board.BoardPos(x, y));

        if(moveGraph.possibleMoves.size() > 0)
        {
            moveGraph.pruneNonMaxPaths();
            return moveGraph;
        }

        if (board.isInBounds(x - 1, y + direction) && board.getPiece(x - 1, y + direction) == null) {
            moveGraph.possibleMoves.add(new MoveNode(x - 1, y + direction, new ArrayList<>()));
        }
        if (board.isInBounds(x + 1, y + direction) && board.getPiece(x + 1, y + direction) == null) {
            moveGraph.possibleMoves.add(new MoveNode(x + 1, y + direction, new ArrayList<>()));
        }

        moveGraph.pruneNonMaxPaths();
        
        return moveGraph;
    }

    // @Override
    // public ArrayList<Move> possibleMoves(Board board, Board.BoardPos boardPos) {
    // ArrayList<Move> moves = new ArrayList<Move>();
    // int x = boardPos.x;
    // int y = boardPos.y;
    // Move move = new Move();
    // if(x > 0 && y > 0 && board.getPiece(x - 1 ,y - 1) == null)
    // {
    // move.visitedFields.add(new Board.BoardPos(x - 1, y - 1));
    // }
    // if(x < board.xDim - 1 && y > 0 && board.getPiece(x + 1 ,y - 1) == null)
    // {
    // move.visitedFields.add(new Board.BoardPos(x + 1, y - 1));
    // }
    // moves.add(move);
    // return moves;
    // }

    @Override
    public Boolean canAscend(Board board, Board.BoardPos boardPos) {
        if (color == Color.BLACK) {
            if (boardPos.y == 0)
                return true;
        } else if (color == Color.WHITE) {
            if (boardPos.y == board.xDim - 1)
                return true;
        }
        return false;
    }

    public Boolean isAscended() {
        return false;
    }
}
