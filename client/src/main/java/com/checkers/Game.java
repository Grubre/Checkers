package com.checkers;

import com.checkers_core.Board;

import javafx.scene.control.Button;

public class Game extends MenuScene {
    protected Board board;
    protected Board.Color playerColor;

    public Game(Board board, Board.Color color)
    {
        super();
        this.playerColor = color;
        this.board = board;
    }

    @Override
    protected void onEnter()
    {
        Grid grid = new Grid(board, playerColor);

        root.getChildren().addAll(grid);
    }

    public void gameOver()
    {
        
    }
}
