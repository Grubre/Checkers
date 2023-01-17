package com.checkers_core;

import java.util.Optional;

public interface AbstractRuleFactory {
    MoveGraph getPossibleMoves(Board board, Board.Color playerColor);
    void setupBoard(Board board, AbstractPawnFactory pawnFactory);
    Optional<Board.Color> gameOver(Board board);
}
