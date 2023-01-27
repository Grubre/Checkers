package com.checkers.game;

import java.util.ArrayList;
import java.util.List;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.Board;
import com.checkers_core.BoardFactory;
import com.checkers_core.VariantStartDescription;

/*
 * Kontroler do ekranu grania.
 * Po nim dziedziczą konkretne MultiGameController i SingleGameController
 */
public class GameController implements StageController {

    protected StageManager manager;
    protected GameView view;
    protected Board boardModel;
    protected List<Integer> moveQueue = new ArrayList<>();
    protected int queuedPieceX;
    protected int queuedPieceY;

    /**
     * Konstruktor
     * Ustawienie planszy 
     * @param manager
     * @param desc
     */
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

    
    /** 
     * Zakolejkuj pionek do wysyłania.
     * @param x
     * @param y
     */
    public void queuePiece(int x, int y) {
        queuedPieceX = x;
        queuedPieceY = y;
    }
    
    
    /** 
     * Dodaj ruch do kolejki.
     * @param tileId
     */
    public void addMove(int tileId) {
        System.out.println("Added " + tileId);
        moveQueue.add(tileId);
    }

    /*
     * Zakończ turę. Czyści kolejkę ruchów.
     */
    public void endTurn() {
        moveQueue.clear();
    }
}
