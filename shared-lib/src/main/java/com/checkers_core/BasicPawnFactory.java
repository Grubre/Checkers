package com.checkers_core;

public class BasicPawnFactory implements AbstractPawnFactory {

    @Override
    public AbstractPawn create_regular(Board.Color color) {
        return new BasicPawn(color);
    }

    @Override
    public AbstractPawn create_ascended(Board.Color color) {
        return new BasicAscendedPawn(color);
    }

    
}
