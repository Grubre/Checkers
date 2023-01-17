package com.checkers_core;

public class BasicBoard extends Board {
    public BasicBoard(int xDim, int yDim, AbstractPawnFactory pawnFactory, AbstractRuleFactory ruleFactory) {
        super(xDim, yDim, pawnFactory, ruleFactory);
    }
}
