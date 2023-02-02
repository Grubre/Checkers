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
    public Move getBestMove(Board board, Board.Color maximizingPlayer)
    {
        MinimaxRet minimaxRet;
        try {
            System.out.println("Maximizing: " + maximizingPlayer.toString());
            minimaxRet = minimax(board, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, maximizingPlayer, maximizingPlayer);
            System.out.println("Chose value = " + minimaxRet.value);
            return minimaxRet.move;
        } catch (CloneNotSupportedException e) {
            
            e.printStackTrace();
            return null;
        }
    }

    private class MinimaxRet {
        Move move;
        int value;

        public MinimaxRet(int value, Move move) {
            this.move = move;
            this.value = value;
        }
    }

    private MinimaxRet minimax(Board board, Integer currentDepth, int alpha, int beta, Board.Color currentPlayer, Board.Color maximizingPlayer) throws CloneNotSupportedException {
        MoveGraph moveGraph = board.getPossibleMovesForColor(currentPlayer);
        List<Move> moves = moveGraph.getMaximalMoves();
        
        // if depth == 0 || is leaf return 0;
        if(currentDepth == 0 || moves == null || moves.isEmpty()) {
            return new MinimaxRet(board.eval(maximizingPlayer), null);
        }

        MinimaxRet minimaxRet = new MinimaxRet(0, null);
        // if maximizing player then
        if(currentPlayer == maximizingPlayer) {
            minimaxRet.value = Integer.MIN_VALUE;
            for(Move move : moves) {
                Board boardCopy = (Board)board.clone();
                boardCopy.movePieceAndUpdate(move);
                MinimaxRet eval = minimax(boardCopy, currentDepth - 1, alpha, beta, Color.getOpposite(currentPlayer), maximizingPlayer);
                if(minimaxRet.value < eval.value) {
                    minimaxRet.move = move;
                    minimaxRet.value = eval.value;
                }
                if(minimaxRet.value > beta) {
                    break;
                }
                alpha = Math.max(alpha, minimaxRet.value);
            }
        }
        // else
        else {
            minimaxRet.value = Integer.MAX_VALUE;
            for(Move move : moves) {
                Board boardCopy = (Board)board.clone();
                boardCopy.movePieceAndUpdate(move);
                MinimaxRet eval = minimax(boardCopy, currentDepth - 1, alpha, beta, Color.getOpposite(currentPlayer), maximizingPlayer);
                if(minimaxRet.value > eval.value) {
                    minimaxRet.move = move;
                    minimaxRet.value = eval.value;
                }
                if(minimaxRet.value > alpha) {
                    break;
                }
                beta = Math.min(beta, minimaxRet.value);
            }
        }

        return minimaxRet;
    }
}
