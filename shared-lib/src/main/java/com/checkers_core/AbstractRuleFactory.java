package com.checkers_core;

public interface AbstractRuleFactory {
    public MoveGraph getPossibleMoves(Board board, Board.Color playerColor);
}
