package com.checkers_core;

public class BasicPawnFactory implements AbstractPawnFactory {

    
    /** 
     * @param color
     * @return AbstractPawn
     */
    @Override
    public AbstractPawn create_regular(Board.Color color) {
        return new BasicPawn(color);
    }

    
    /** 
     * @param color
     * @return AbstractPawn
     */
    @Override
    public AbstractPawn create_ascended(Board.Color color) {
        return new BasicAscendedPawn(color);
    }

    
}
