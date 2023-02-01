package com.checkers_core.algorithms;

import com.checkers_core.boards.Board;
import com.checkers_core.moves.Move;
import com.checkers_core.moves.MoveGraph;

public class MiniMax implements Algorithm{
    private final Integer depth;

    public MiniMax(Integer depth) {
        this.depth = depth;
    }

    @Override
    public Move getBestMove(Board board, Board.Color currentPlayer)
    {
        return new Move();
    }

    private Integer minimax(Board parentBoard, Move parentMove, Integer currentDepth, Board.Color currentPlayer) {
        if(currentDepth == 0) {
            return 0;
        }

        // We copy parent's position list
        Move move = new Move(parentMove);
        Board.BoardPos myPos = move.visitedFields.get(move.visitedFields.size() -1);

        Integer value = 0;

        MoveGraph moves = parentBoard.getPossibleMovesForColor(currentPlayer);

        return value;
    }
}
