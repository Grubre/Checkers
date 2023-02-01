package com.checkers_core.rules;

import java.util.Optional;

import com.checkers_core.boards.Board;
import com.checkers_core.moves.MoveGraph;
import com.checkers_core.pawns.AbstractPawnFactory;

public interface AbstractRuleFactory {
    MoveGraph getPossibleMoves(Board board, Board.Color playerColor);
    void setupBoard(Board board, AbstractPawnFactory pawnFactory);
    Optional<Board.Color> gameOver(Board board);
}
