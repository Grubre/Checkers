package com.checkers;

import com.checkers_core.Board;

public class Game extends MenuScene {
    protected Board board;

    public Game(Board board)
    {
        super();
        this.board = board;
    }

    public void set_board(Board board)
    {
        this.board = board;
    }
}
