package com.checkers_core;

public interface AbstractPawnFactory {
    public AbstractPawn create_regular(Board.Color color);
    public AbstractPawn create_ascended(Board.Color color);
}
