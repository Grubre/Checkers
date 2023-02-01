package com.checkers_core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.checkers_core.boards.BasicBoard;
import com.checkers_core.boards.Board.Color;
import com.checkers_core.pawns.BasicPawnFactory;
import com.checkers_core.rules.BasicVariantRuleFactory;

public class BasicVariantTest {
    @Test
    public void constructorTest()
    {
        int xDim = 8;
        int yDim = 8;
        BasicBoard basicVariant = new BasicBoard(xDim, yDim, new BasicPawnFactory(), new BasicVariantRuleFactory());
        assertEquals(basicVariant.xDim, xDim);
        assertEquals(basicVariant.yDim, yDim);
    }
    @Test
    public void startingBoardCntTest()
    {
        BasicBoard startingBoard = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
        startingBoard.setupBoard();
        int pawnCnt = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(startingBoard.getPiece(i, j) != null) {
                    pawnCnt++;
                }
            }
        }
        assertEquals(pawnCnt, 24);
    }
    @Test
    public void startingBoardNoPawnsOnWhiteFieldsTest()
    {
        BasicBoard startingBoard = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
        startingBoard.setupBoard();
        int whiteFieldPawnCnt = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if((i + j) % 2 == 0 && startingBoard.getPiece(i, j) != null) {
                    whiteFieldPawnCnt++;
                }
            }
        }
        assertEquals(whiteFieldPawnCnt, 0);
    }
    @Test
    public void startingBoardWhitePlacementTest()
    {
        BasicBoard startingBoard = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
        startingBoard.setupBoard();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(j < 3 && (i + j) % 2 == 1 && startingBoard.getPiece(i, j).getColor() != Color.WHITE) {
                    assertTrue(false);
                }
            }
        }
        assertTrue(true);
    }
    @Test
    public void startingBoardBlackPlacementTest()
    {
        BasicBoard startingBoard = new BasicBoard(8, 8, new BasicPawnFactory(), new BasicVariantRuleFactory());
        startingBoard.setupBoard();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(j > 4 && (i + j) % 2 == 1 && startingBoard.getPiece(i, j).getColor() != Color.BLACK) {
                    assertTrue(false);
                }
            }
        }
        assertTrue(true);
    }
}