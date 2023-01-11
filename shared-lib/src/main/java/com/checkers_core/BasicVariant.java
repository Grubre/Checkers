package com.checkers_core;

import java.util.HashMap;
import java.util.Optional;

public class BasicVariant extends Board {
    AbstractPawnFactory pawnFactory;
    public BasicVariant(int xDim, int yDim, AbstractPawnFactory pawnFactory)
    {
        super(xDim, yDim, pawnFactory);
        this.pawnFactory = pawnFactory;
    }

    @Override
    public void setupBoard() {
        for (int j = 0; j < yDim; j++) {
            for (int i = 0; i < xDim; i++) {
                if(j < 3 && (i + j) % 2 == 1)
                    board[i][j] = pawnFactory.create_regular(Color.WHITE);
                else if(j >= yDim - 3 && (i + j) % 2 == 1)
                    board[i][j] = pawnFactory.create_regular(Color.BLACK);
                else
                    board[i][j] = null;
            }
        }
    }

    @Override
    public Optional<Color> gameOver() {
        HashMap<Color,Integer> colorCnt = new HashMap<Board.Color,Integer>();
        for (int i = 0; i < xDim; i++) {
            for (int j = 0; j < yDim; j++) {
                AbstractPawn pawn = board[i][j];
                if(pawn == null)
                    continue;
                Color key = pawn.getColor();
                colorCnt.putIfAbsent(key, 0);
                colorCnt.put(key, colorCnt.get(key) + 1);
            }
        }
        if(colorCnt.get(Color.BLACK) == 0)
            return Optional.of(Color.WHITE);
        else if(colorCnt.get(Color.WHITE) == 0)
            return Optional.of(Color.BLACK);
        return Optional.empty();
    }
    
}
