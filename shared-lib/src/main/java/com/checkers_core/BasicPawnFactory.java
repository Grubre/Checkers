package com.checkers_core;

public class BasicPawnFactory implements AbstractPawnFactory {

    @Override
    public AbstractPawn create_regular(Board.Color color) {
        // TODO Auto-generated method stub
        return new BasicPawn(color);
    }

    @Override
    public AbstractPawn create_ascended(Board.Color color) {
        // TODO Auto-generated method stub
        return new BasicAscendedPawn(color);
    }

    
}
