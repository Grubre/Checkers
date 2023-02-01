package com.checkers_core.pawns;

import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.Color;

public class BasicPawnFactory implements AbstractPawnFactory {

    @Override
    public AbstractPawn createRegular(Board.Color color) {
        return new BasicPawn(color);
    }

    @Override
    public AbstractPawn createAscended(Board.Color color) {
        return new BasicAscendedPawn(color);
    }

    
}
