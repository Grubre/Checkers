package com.checkers.game;

import com.checkers.connection.ServerConnection;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.Board;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.Board.BoardPos;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.ResignCommand;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.ResponseVisitor;
import com.checkers_core.resp.response.EndOfGameResponse;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.Response;

public class MultiGameController extends GameController implements ResponseVisitor<Void>, ResponseListener {

    ServerConnection connection;

    public MultiGameController(StageManager manager, VariantStartDescription desc, ServerConnection connection) {
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
    public void endTurn() {
        connection.getListener().onCommand(new MovePieceCommand(queuedPieceX, queuedPieceY, moveQueue));
        System.out.println("Sent " + queuedPieceX + " " + queuedPieceY + " " + moveQueue);
        super.endTurn();
    }

    @Override
    public Void visitPieceMoved(PieceMovedResponse response) {
        BoardPos piecePos = new BoardPos(response.getPieceX(), response.getPieceY());
        
        for (int tileId : response.getTileIds()) {
            BoardPos targetPos = new BoardPos(tileId % boardModel.xDim, tileId / boardModel.xDim);
            boardModel.movePiece(piecePos, targetPos);
            piecePos = targetPos;
        }

        view.newTurn();

        return null;
    }

    @Override
    public Void visitEndOfGame(EndOfGameResponse response) {
        connection.getListener().onCommand(new ResignCommand());
        
        manager.switchToChooseLobbyMenu(connection);

        return null;
    }

    @Override
    public Void onUnimplemented(Response response) {
        System.out.println("unimplemented");
        return null;
    }

    @Override
    public void onResponse(Response response) {
        response.accept(this);
    }
    
}
