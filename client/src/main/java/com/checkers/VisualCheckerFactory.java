package com.checkers;

import com.checkers_core.AbstractPawn;
import com.checkers_core.AbstractPawnFactory;
import com.checkers_core.Board.Color;

public class VisualCheckerFactory implements AbstractPawnFactory {

    @Override
    public AbstractPawn create_regular(Color color) {
        // TODO Auto-generated method stub
        return new Checker(color);
    }

    @Override
    public AbstractPawn create_ascended(Color color) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
