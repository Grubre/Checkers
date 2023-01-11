package com.checkers_core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.checkers_core.Board.Color;

public class BasicVariantTest {
    @Test
    public void constructorTest()
    {
        int xDim = 8;
        int yDim = 8;
        BasicVariant basicVariant = new BasicVariant(xDim, yDim, new BasicPawnFactory());
        assertTrue(basicVariant.xDim == xDim);
        assertTrue(basicVariant.yDim == yDim);
    }
    @Test
    public void startingBoardCntTest()
    {
        BasicVariant startingBoard = new BasicVariant(8, 8, new BasicPawnFactory());
        startingBoard.setupBoard();
        int pawnCnt = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(startingBoard.getBoard()[i][j] != null)
                    pawnCnt++;
            }
        }
        assertTrue(pawnCnt == 24);
    }
    @Test
    public void startingBoardNoPawnsOnWhiteFieldsTest()
    {
        BasicVariant startingBoard = new BasicVariant(8, 8, new BasicPawnFactory());
        startingBoard.setupBoard();
        int whiteFieldPawnCnt = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if((i + j) % 2 == 0 && startingBoard.getBoard()[i][j] != null)
                whiteFieldPawnCnt++;
            }
        }
        assertTrue(whiteFieldPawnCnt == 0);
    }
    @Test
    public void startingBoardWhitePlacementTest()
    {
        BasicVariant startingBoard = new BasicVariant(8, 8, new BasicPawnFactory());
        startingBoard.setupBoard();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(j < 3 && (i + j) % 2 == 1 && startingBoard.getBoard()[i][j].getColor() != Color.WHITE)
                    assert(false);
            }
        }
        assertTrue(true);
    }
    @Test
    public void startingBoardBlackPlacementTest()
    {
        BasicVariant startingBoard = new BasicVariant(8, 8, new BasicPawnFactory());
        startingBoard.setupBoard();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(j > 4 && (i + j) % 2 == 1 && startingBoard.getBoard()[i][j].getColor() != Color.BLACK)
                    assert(false);
            }
        }
        assertTrue(true);
    }
}