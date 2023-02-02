package com.checkers.connection;

import java.util.List;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.algorithms.Algorithm;
import com.checkers_core.algorithms.MiniMax;
import com.checkers_core.boards.Board;
import com.checkers_core.boards.BoardFactory;
import com.checkers_core.boards.Board.BoardPos;
import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.moves.Move;
import com.checkers_core.resp.ResponseVisitor;
import com.checkers_core.resp.response.EndOfGameResponse;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.Response;

public class BotConnection extends PlayerConnection implements ResponseVisitor<Void> {
    Board board;
    Algorithm algorithm;
    Board.Color color;
    

    public BotConnection(VariantStartDescription desc) {
        this.board =  new BoardFactory().createBoard(desc);

        this.algorithm = new MiniMax(10);

        this.color = Board.Color.fromString(desc.getColor());
    }

    public void onResponse(Response response) {
        response.accept(this);
    }

    public Void onUnimplemented(Response response) {
        return null;
    }

    public Void visitPieceMoved(PieceMovedResponse response) {
        BoardPos piecePos = new BoardPos(response.getPieceX(), response.getPieceY());
        
        for (int tileId : response.getTileIds()) {
            BoardPos targetPos = new BoardPos(tileId % board.xDim, tileId / board.xDim);
            board.movePiece(piecePos, targetPos);
            piecePos = targetPos;
        }

        respondWithBestMove();

        return null;
    }

    public Void visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        if (color == Board.Color.WHITE) {
            respondWithBestMove();
        }
        return null;
    }

    public Void visitEndOfGame(EndOfGameResponse response) {
        Command comm = new DisconnectCommand();

        comm.setPlayerId(-1);

        sendCommand(comm);

        return null;
    }

    public void respondWithBestMove() {
        Move move = algorithm.getBestMove(board, color);

        if (move == null) {
            return;
        }

        board.movePieceAndUpdate(move);

        List<Board.BoardPos> poss = move.visitedFields;

        int pieceX = poss.get(0).x;
        int pieceY = poss.get(0).y;

        List<Integer> tileIds = poss.stream().skip(1).map((pos) -> pos.x + pos.y * board.xDim).toList();

        System.out.println("Daje ruch: " + pieceX + " " + pieceY + " " + tileIds);

        Command comm = new MovePieceCommand(pieceX, pieceY, tileIds);

        comm.setPlayerId(-1);
        
        sendCommand(comm);
    }
}
