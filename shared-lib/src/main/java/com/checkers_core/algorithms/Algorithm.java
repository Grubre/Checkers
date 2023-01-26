package com.checkers_core.algorithms;

import com.checkers_core.boards.Board;
import com.checkers_core.moves.Move;

public interface Algorithm {
    Move getBestMove(Board board, Board.Color currentPlayer);
}
