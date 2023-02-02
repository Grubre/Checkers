package com.checkers_core.algorithms;

import java.util.List;

import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.Color;
import com.checkers_core.moves.Move;
import com.checkers_core.moves.MoveGraph;
import com.checkers_core.pawns.AbstractPawn;

public class MiniMax implements Algorithm{
    private final Integer depth;

    public MiniMax(Integer depth) {
        this.depth = depth;
    }

    @Override
    public Move getBestMove(Board board, Board.Color currentPlayer)
    {
        MinimaxRet minimaxRet;
        try {
            minimaxRet = minimax(board, depth, currentPlayer, currentPlayer);
            return minimaxRet.move;
        } catch (CloneNotSupportedException e) {
            
            e.printStackTrace();
            return null;
        }
    }

    private Integer evalBoard(Board board, Board.Color player) {
        int val = 0;
        for(int i = 0; i < board.yDim; i++) {
            for(int j = 0; j < board.xDim; j++) {
                AbstractPawn pawn = board.getPiece(i, j);
                if(pawn != null) {
                    if(pawn.getColor() == player) {
                        val++;
                    }
                    else {
                        val--;
                    }
                }
            }
        }
        return val;
    }

    private class MinimaxRet {
        Move move;
        int value;

        public MinimaxRet(int value, Move move) {
            this.move = move;
            this.value = value;
        }
    }

    private MinimaxRet minimax(Board board, Integer currentDepth, Board.Color currentPlayer, Board.Color player) throws CloneNotSupportedException {
        // if depth == 0 || is leaf return 0;
        if(currentDepth == 0) {
            return new MinimaxRet(evalBoard(board, currentPlayer), null);
        }

        MoveGraph moveGraph = board.getPossibleMovesForColor(currentPlayer);
        List<Move> moves = moveGraph.getMaximalMoves();

        MinimaxRet minimaxRet = new MinimaxRet(0, null);
        // if maximizing player then
        if(currentPlayer == player) {
            minimaxRet.value = Integer.MIN_VALUE;
            for(Move move : moves) {
                Board boardCopy = (Board)board.clone();
                boardCopy.movePieceAndUpdate(move);
                MinimaxRet eval = minimax(boardCopy, currentDepth - 1, Color.getOpposite(currentPlayer), player);
                if(minimaxRet.value < eval.value) {
                    minimaxRet.move = move;
                    minimaxRet.value = eval.value;
                }
            }
        }
        // else
        else {
            minimaxRet.value = Integer.MAX_VALUE;
            for(Move move : moves) {
                Board boardCopy = (Board)board.clone();
                boardCopy.movePieceAndUpdate(move);
                MinimaxRet eval = minimax(boardCopy, currentDepth - 1, Color.getOpposite(currentPlayer), player);
                if(minimaxRet.value > eval.value) {
                    minimaxRet.move = move;
                    minimaxRet.value = eval.value;
                }
            }
        }

        return minimaxRet;
    }
}
