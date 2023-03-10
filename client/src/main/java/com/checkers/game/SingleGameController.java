package com.checkers.game;

import com.checkers.stage_manager.StageManager;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.boards.Board.Color;

public class SingleGameController extends GameController {
    public SingleGameController(StageManager manager, VariantStartDescription desc) {
        super(manager, desc);
        this.view = new GameView(boardModel, Color.WHITE, this);
    }
     
    @Override
    public void endTurn() {
        super.endTurn();
        //view.newTurn();
    }
}
