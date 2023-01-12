package com.checkers.scenes;

import com.checkers.Grid;
import com.checkers_core.Board;

public class GameController extends MenuScene {
    protected Board board;
    protected Board.Color playerColor;

    public GameController(Board board, Board.Color color)
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
