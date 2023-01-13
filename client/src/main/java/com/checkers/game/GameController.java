package com.checkers.game;

import java.util.ArrayList;
import java.util.List;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.Board;
import com.checkers_core.BoardFactory;
import com.checkers_core.VariantStartDescription;

public class GameController implements StageController {

    StageManager manager;
    GameView view;
    Board boardModel;
    List<Integer> moveQueue = new ArrayList<>();
    int queuedPieceX;
    int queuedPieceY;

    public GameController(StageManager manager, VariantStartDescription desc) {
        this.manager = manager;
        this.boardModel = new BoardFactory().createBoard(desc);
        this.view = new GameView(boardModel, Board.Color.fromString(desc.getColor()), this);
    }

    @Override
    public void activate() {
        view.setCurrent();
    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub
    }

    public void queuePiece(int x, int y) {
        queuedPieceX = x;
        queuedPieceY = y;
    }
    
    public void addMove(int tileId) {
        System.out.println("Added " + tileId);
        moveQueue.add(tileId);
    }

    public void endTurn() {
        moveQueue.clear();
    }

    
}
