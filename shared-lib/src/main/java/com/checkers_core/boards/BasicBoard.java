package com.checkers_core.boards;

import com.checkers_core.pawns.AbstractPawnFactory;
import com.checkers_core.rules.AbstractRuleFactory;

public class BasicBoard extends Board {
    public BasicBoard(int xDim, int yDim, AbstractPawnFactory pawnFactory, AbstractRuleFactory ruleFactory) {
        super(xDim, yDim, pawnFactory, ruleFactory);
    }
}
