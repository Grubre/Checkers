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
import com.checkers_core.pawns.AbstractPawn;
import com.checkers_core.pawns.BasicPawnFactory;
import com.checkers_core.rules.BasicVariantRuleFactory;

public class MiniMaxTest {
    @Test
    public void pawnDeepCopyTest() throws CloneNotSupportedException {
        BasicPawnFactory basicPawnFactory = new BasicPawnFactory();
        AbstractPawn pawn = basicPawnFactory.createRegular(Color.BLACK);
        AbstractPawn pawnCopy = (AbstractPawn)pawn.clone();

        assertEquals(pawn.getColor(), pawnCopy.getColor());
    }

    @Test
    public void boardDeepCopyTest() throws CloneNotSupportedException {
        BasicBoard board = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
        board.setupBoard();
        BasicBoard copy = (BasicBoard)board.clone();
        copy.movePiece(new Board.BoardPos(1, 2), new Board.BoardPos(2, 3));

        assertNull(copy.getPiece(1,2));
        assertNotNull(copy.getPiece(2,3));
        assertNotNull(board.getPiece(1,2));
    }

    @Test
    public void getMaximalMovesStartingPos() {
        BasicBoard board = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
        MoveGraph moveGraph = board.getPossibleMovesForColor(Color.WHITE);
        List<Move> moves = moveGraph.getMaximalMoves();
        // System.out.println("moves size: " + moves.size());
    }

    @Test
    public void miniMaxTest() {
        BasicBoard board = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
        board.setupBoard();
        Algorithm miniMax = new MiniMax(10);
        Move bestMove = miniMax.getBestMove(board, Color.WHITE);

        assertNotNull(bestMove);
    }

    @Test
    public void miniMaxCaptureTest() {
        BasicBoard board = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
        Algorithm miniMax = new MiniMax(10);
        Move bestMove = miniMax.getBestMove(board, Color.WHITE);

        assertNotNull(bestMove);
    }
}
