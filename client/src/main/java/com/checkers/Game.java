package com.checkers;

import com.checkers_core.Board;

public class Game extends MenuScene {
    protected Board board;
    protected Board.Color playerColor;

    public Game(Board board)
    {
        super();
        this.board = board;
    }

    public void set_board(Board board, Board.Color color)
    {
        this.board = board;
        this.playerColor = color;
    }

    @Override
    protected void onEnter()
    {
        
    }
}
