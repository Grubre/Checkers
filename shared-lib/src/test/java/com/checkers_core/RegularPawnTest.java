package com.checkers_core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.checkers_core.boards.BasicBoard;
import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.Color;
import com.checkers_core.pawns.BasicPawn;
import com.checkers_core.pawns.BasicPawnFactory;
import com.checkers_core.rules.BasicVariantRuleFactory;

public class RegularPawnTest {
    @Test
    public void constructorTest()
    {
        Color color = Color.BLACK;
        BasicPawn regularPawn = new BasicPawn(color);

        assertTrue(color == regularPawn.getColor());
    }

    @Test
    public void whiteAscensionRowTest()
    {
        int xDim = 8;
        int yDim = 8;
        BasicPawn regularPawn = new BasicPawn(Color.WHITE);
        BasicBoard basicVariant = new BasicBoard(xDim, yDim, new BasicPawnFactory(), new BasicVariantRuleFactory());

        for (int j = 0; j < yDim; j++) {
            for (int i = 0; i < xDim; i++) {
                if(j == yDim - 1)
                    assertTrue(regularPawn.canAscend(basicVariant, new Board.BoardPos(i,j)));
                else
                    assertTrue(!regularPawn.canAscend(basicVariant, new Board.BoardPos(i,j)));
            }
        }
    }

    @Test
    public void blackAscensionRowTest()
    {
        int xDim = 8;
        int yDim = 8;
        BasicPawn regularPawn = new BasicPawn(Color.BLACK);
        BasicBoard basicVariant = new BasicBoard(xDim, yDim, new BasicPawnFactory(), new BasicVariantRuleFactory());

        for (int j = 0; j < yDim; j++) {
            for (int i = 0; i < xDim; i++) {
                if(j == 0)
                    assertTrue(regularPawn.canAscend(basicVariant, new Board.BoardPos(i,j)));
                else
                    assertTrue(!regularPawn.canAscend(basicVariant, new Board.BoardPos(i,j)));
            }
        }
    }

    @Test
    public void whiteAscensionCntTest()
    {
        int xDim = 8;
        int yDim = 8;
        BasicPawn regularPawn = new BasicPawn(Color.WHITE);
        BasicBoard basicVariant = new BasicBoard(xDim, yDim, new BasicPawnFactory(), new BasicVariantRuleFactory());

        int whiteCnt = 0;

        for (int j = 0; j < yDim; j++) {
            for (int i = 0; i < xDim; i++) {
                if(regularPawn.canAscend(basicVariant, new Board.BoardPos(i,j)))
                    whiteCnt++;
            }
        }

        assertTrue(whiteCnt == xDim);
    }

    @Test
    public void blackAscensionCntTest()
    {
        int xDim = 8;
        int yDim = 8;
        BasicPawn regularPawn = new BasicPawn(Color.BLACK);
        BasicBoard basicVariant = new BasicBoard(xDim, yDim, new BasicPawnFactory(), new BasicVariantRuleFactory());

        int blackCnt = 0;

        for (int j = 0; j < yDim; j++) {
            for (int i = 0; i < xDim; i++) {
                if(regularPawn.canAscend(basicVariant, new Board.BoardPos(i,j)))
                    blackCnt++;
            }
        }

        assertTrue(blackCnt == xDim);
    }
}
