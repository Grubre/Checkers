package com.checkers_core;

import java.util.Optional;

public abstract class Board {
    public enum Color{
        BLACK,
        WHITE
    };

    public class BoardPos{
        public BoardPos(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        int x;
        int y;
    }

    public final int x_dim;
    public final int y_dim;

    protected AbstractPawn[][] board;

    Board(int x_dim, int y_dim)
    {
        this.x_dim = x_dim;
        this.y_dim = y_dim;

        board = new AbstractPawn[x_dim][y_dim];
    }

    public AbstractPawn get_piece(int i, int j)
    {
        return board[i][j];
    }

    public AbstractPawn[][] get_board()
    {
        return board;
    }

    public abstract void setup_board();
    public abstract Optional<Color> game_over();
}
