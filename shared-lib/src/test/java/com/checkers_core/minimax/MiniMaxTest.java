package com.checkers_core.minimax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.checkers_core.boards.Board.Color;
import com.checkers_core.pawns.AbstractPawn;
import com.checkers_core.pawns.BasicPawnFactory;

public class MiniMaxTest {
    @Test
    public void pawnDeepCopyTest() throws CloneNotSupportedException {
        BasicPawnFactory basicPawnFactory = new BasicPawnFactory();
        AbstractPawn pawn = basicPawnFactory.createRegular(Color.BLACK);
        AbstractPawn pawnCopy = (AbstractPawn)pawn.clone();

        assertEquals(pawn.getColor(), pawnCopy.getColor());
    }
}
