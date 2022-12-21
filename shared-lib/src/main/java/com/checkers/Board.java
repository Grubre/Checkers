package com.checkers;

import java.util.Optional;

public abstract class Board {
    public enum Color{
        BLACK,
        WHITE
    };

    private int x_dim;
    private int y_dim;

    private AbstractPawn[][] board;

    Board(int x_dim, int y_dim)
    {
        this.x_dim = x_dim;
        this.y_dim = y_dim;

        board = new AbstractPawn[x_dim][y_dim];
    }

    public abstract Optional<Color> game_over();
}
