package com.checkers_core.pawns;

import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.Color;

public interface AbstractPawnFactory {
    AbstractPawn createRegular(Board.Color color);
    AbstractPawn createAscended(Board.Color color);
}
