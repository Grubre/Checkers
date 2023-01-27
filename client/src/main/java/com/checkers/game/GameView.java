package com.checkers.game;

import com.checkers.view.StageView;
import com.checkers_core.Board;

/*
 * Widok ekranu grania.
 */
public class GameView extends StageView {
    private GameController controller; 
    private Grid grid;

    public GameView(Board board, Board.Color color, GameController controller) {
        this.controller = controller;
        this.grid = new Grid(board, color, controller);
        root.getChildren().addAll(grid);
    }

    public void newTurn() {
        grid.endTurn();
    }
}

