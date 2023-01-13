package com.checkers_core;

import java.util.HashMap;
import java.util.Optional;

public class BasicBoard extends Board {
    public BasicBoard(int xDim, int yDim, AbstractPawnFactory pawnFactory, AbstractRuleFactory ruleFactory) {
        super(xDim, yDim, pawnFactory, ruleFactory);
    }

    @Override
    public void setupBoard() {
        for (int j = 0; j < yDim; j++) {
            for (int i = 0; i < xDim; i++) {
                if (j < yDim / 2 - 1 && (i + j) % 2 == 1) {
                    board[i][j] = pawnFactory.create_regular(Color.WHITE);
                } else if (j >= yDim / 2 + 1 && (i + j) % 2 == 1) {
                    board[i][j] = pawnFactory.create_regular(Color.BLACK);
                } else {
                    board[i][j] = null;
                }
            }
        }
    }

    @Override
    public Optional<Color> gameOver() {
        int black = 0;
        int white = 0;

        for (int i = 0; i < xDim; i++) {
            for (int j = 0; j < yDim; j++) {
                AbstractPawn pawn = board[i][j];
                if (pawn == null) {
                    continue;
                }
                Color key = pawn.getColor();
                switch (key) {
                    case WHITE -> {
                        white++;
                    }
                    case BLACK -> {
                        black++;
                    }
                }
            }
        }
        if (black == 0) {
            return Optional.of(Color.WHITE);
        } else if (white == 0) {
            return Optional.of(Color.BLACK);
        }
        return Optional.empty();
    }

}
