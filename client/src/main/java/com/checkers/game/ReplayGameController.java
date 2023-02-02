package com.checkers.game;

import com.checkers.connection.ServerConnection;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.boards.Board.BoardPos;
import com.checkers_core.comm.command.NextMoveCommand;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.ResponseVisitor;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.Response;

public class ReplayGameController extends GameController implements ResponseVisitor<Void>, ResponseListener {

    ServerConnection connection;

    public ReplayGameController(StageManager manager, VariantStartDescription desc, ServerConnection connection) {
        super(manager, desc);
        
        this.connection = connection;
    }

    @Override
    public void activate() {
        connection.getSender().addListener(this);
        super.activate();
    }
    
    @Override
    public void deactivate() {
        connection.getSender().removeListener(this);
        super.deactivate();
    }

    @Override
    public Void visitPieceMoved(PieceMovedResponse response) {
        BoardPos piecePos = new BoardPos(response.getPieceX(), response.getPieceY());

        System.out.println(response);
        
        for (int tileId : response.getTileIds()) {
            BoardPos targetPos = new BoardPos(tileId % boardModel.xDim, tileId / boardModel.xDim);
            boardModel.movePiece(piecePos, targetPos);
            piecePos = targetPos;
        }

        view.newTurn();

        return null;
    }

    @Override
    public void click() {
        connection.getListener().onCommand(new NextMoveCommand());
    }

    @Override
    public void onResponse(Response response) {
        response.accept(this);
    }

    @Override
    public Void onUnimplemented(Response response) {
        return null;
    }
    
}
