package com.checkers.game;

import com.checkers.view.StageView;
import com.checkers_core.boards.Board;

public class GameView extends StageView {
    GameController controller;
    
    Grid grid;

    public GameView(Board board, Board.Color color, GameController controller) {
        this.controller = controller;
        this.grid = new Grid(board, color, controller);
        root.getChildren().addAll(grid);
    }

    public void newTurn() {
        grid.endTurn();
    }
}

