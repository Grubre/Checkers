package com.checkers.game;

import com.checkers.stage_manager.StageManager;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.Board.Color;

public class LocalGameController extends GameController {
    public LocalGameController(StageManager manager, VariantStartDescription desc) {
        super(manager, desc);
        this.view = new GameView(boardModel, Color.WHITE, this);
    }
     
    @Override
    public void endTurn() {
        super.endTurn();
        //view.newTurn();
    }
}
