package com.checkers_core.minimax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.checkers_core.algorithms.Algorithm;
import com.checkers_core.algorithms.MiniMax;
import com.checkers_core.boards.BasicBoard;
import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.Color;
import com.checkers_core.moves.Move;
import com.checkers_core.moves.MoveGraph;
import com.checkers_core.moves.MoveNode;
import com.checkers_core.pawns.AbstractPawn;
import com.checkers_core.pawns.BasicPawn;
import com.checkers_core.pawns.BasicPawnFactory;
import com.checkers_core.rules.BasicVariantRuleFactory;

public class MiniMaxTest {
    // @Test
    // public void pawnDeepCopyTest() throws CloneNotSupportedException {
    //     BasicPawnFactory basicPawnFactory = new BasicPawnFactory();
    //     AbstractPawn pawn = basicPawnFactory.createRegular(Color.BLACK);
    //     AbstractPawn pawnCopy = (AbstractPawn)pawn.clone();

    //     assertEquals(pawn.getColor(), pawnCopy.getColor());
    // }

    // @Test
    // public void boardDeepCopyTest() throws CloneNotSupportedException {
    //     BasicBoard board = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
    //     board.setupBoard();
    //     BasicBoard copy = (BasicBoard)board.clone();
    //     copy.movePiece(new Board.BoardPos(1, 2), new Board.BoardPos(2, 3));
    //     copy.movePiece(new Board.BoardPos(4, 5), new Board.BoardPos(3, 4));
    //     copy.movePiece(new Board.BoardPos(2, 3), new Board.BoardPos(4, 5));

    //     int blackCntCopy = 0, blackCntOrig = 0;
    //     for(int i = 0; i < copy.yDim; i++) {
    //         for(int j = 0; j < copy.xDim; j++) {
    //             if(copy.getPiece(i, j) != null && copy.getPiece(i, j).getColor() == Color.BLACK) {
    //                 blackCntCopy++;
    //             }
    //             if(board.getPiece(i, j) != null && board.getPiece(i, j).getColor() == Color.BLACK) {
    //                 blackCntOrig++;
    //             }
    //         }
    //     }
    //     assertEquals(blackCntCopy, 11);
    //     assertEquals(blackCntOrig, 12);
    // }

    @Test
    public void multipleCapturesTest() {
        BasicPawnFactory pawnFactory = new BasicPawnFactory();
        BasicBoard board = new BasicBoard(8, 8, pawnFactory, new BasicVariantRuleFactory());
        for(int i = 0; i < board.yDim; i++) {
            for(int j = 0; j < board.xDim; j++) {
                board.setPiece(i, j, null);
            }
        }
        board.setPiece(1, 2, pawnFactory.createRegular(Color.WHITE));
        board.setPiece(3, 2, pawnFactory.createRegular(Color.WHITE));
        board.setPiece(0, 3, pawnFactory.createRegular(Color.BLACK));

        for(int i = 0; i < board.yDim; i++) {
            for(int j = 0; j < board.xDim; j++) {
                if(board.getPiece(j, i) != null) {
                    if(board.getPiece(j, i).getColor() == Color.BLACK) {
                        System.out.print("B ");
                    } else {
                        System.out.print("W ");
                    }
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("");
        }
        MoveGraph moveGraph = board.getPossibleMovesForColor(Color.BLACK);
        // MoveNode moveNode = moveGraph.getMoveNodeAt(new Board.BoardPos(0, 3));
        // System.out.println(moveNode.possibleMoves.get(0).possibleMoves.size());
        // List<Move> moves = moveGraph.getMaximalMoves();
        // for(Board.BoardPos pos : moves.get(0).visitedFields) {
        //     System.out.println("pos: " + pos.x + ", " + pos.y);
        // }
    }

    // @Test
    // public void getMaximalMovesStartingPos() {
    //     BasicBoard board = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
    //     MoveGraph moveGraph = board.getPossibleMovesForColor(Color.WHITE);
    //     List<Move> moves = moveGraph.getMaximalMoves();
    //     // System.out.println("moves size: " + moves.size());
    // }

    // @Test
    // public void miniMaxTest() {
    //     BasicBoard board = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
    //     board.setupBoard();
    //     Algorithm miniMax = new MiniMax(10);
    //     Move bestMove = miniMax.getBestMove(board, Color.WHITE);

    //     assertNotNull(bestMove);
    // }

    // @Test
    // public void miniMaxCaptureTest() {
    //     BasicBoard board = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
    //     Algorithm miniMax = new MiniMax(10);
    //     Move bestMove = miniMax.getBestMove(board, Color.WHITE);

    //     assertNotNull(bestMove);
    // }
}
