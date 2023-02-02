package com.checkers_core.boards;

import com.checkers_core.pawns.AbstractPawn;
import com.checkers_core.pawns.AbstractPawnFactory;
import com.checkers_core.rules.AbstractRuleFactory;

public class BasicBoard extends Board {
    public BasicBoard(int xDim, int yDim, AbstractPawnFactory pawnFactory, AbstractRuleFactory ruleFactory) {
        super(xDim, yDim, pawnFactory, ruleFactory);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BasicBoard board = new BasicBoard(this.xDim, this.yDim, this.pawnFactory, this.ruleFactory);

        for (int j = 0; j < board.yDim; j++) {
            for (int i = 0; i < board.xDim; i++) {
                if(getPiece(i,j) != null) {
                    board.setPiece(i, j, (AbstractPawn)getPiece(i, j).clone());
                }
            }
        }

        return board;
    }
}
