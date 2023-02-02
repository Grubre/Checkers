package com.checkers.lobby;

import java.util.Locale;

import com.checkers.database.DatabaseUtil;
import com.checkers.database.MatchEntry;
import com.checkers.database.MoveEntry;
import com.checkers.database.VariantEntry;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.boards.Board;
import com.checkers_core.boards.BoardFactory;
import com.checkers_core.boards.Board.BoardPos;
import com.checkers_core.comm.CommandCPSerializer;
import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.ResignCommand;
import com.checkers_core.resp.response.EndOfGameResponse;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.PlayerDisconnectedResponse;

public class GameLobby extends Lobby {
    Hub mainHub;
    int gameId;
    
    VariantStartDescription desc;
    Board board;

    MatchEntry match;
    int turn_number = 0;
    
    public GameLobby(Hub mainHub, VariantStartDescription desc, int gameId) {
        this.mainHub = mainHub;
        this.desc = desc;
        this.board = new BoardFactory().createBoard(desc);
        this.gameId = gameId;

        this.match = new MatchEntry();
        match.setBoard_height(desc.getHeight());
        match.setBoard_width(desc.getWidth());
        VariantEntry variant = DatabaseUtil.getVariantByName(desc.getName());
        match.setVariant(variant);
        DatabaseUtil.persist(match);
    }
    
    @Override
    public Void visitDisconnect(DisconnectCommand command) {
        int playerId = command.getPlayerId();
        
        removePlayer(playerId);
        
        //TODO Insert game logic
        
        broadcastToAllPlayers(new PlayerDisconnectedResponse(playerId));

        return null;
    }
    
    @Override
    public Void visitResign(ResignCommand command) {
        int playerId = command.getPlayerId();
        
        transferPlayerTo(playerId, mainHub);
        
        //TODO Insert game logic
        
        broadcastToAllPlayers(new PlayerDisconnectedResponse(playerId));
        
        closeIfEmpty();
        
        return null;
    }
    
    @Override
    public Void visitMovePiece(MovePieceCommand command) {
        BoardPos piecePos = new BoardPos(command.getPieceX(), command.getPieceY());

        int moveNumber = 0;
        for (int tileId : command.getTileIds()) {
            BoardPos targetPos = new BoardPos(tileId % board.xDim, tileId / board.xDim);
            board.movePiece(piecePos, targetPos);
            piecePos = targetPos;

            MoveEntry move = new MoveEntry();
            move.setTurn_number(turn_number);
            move.setMove_number(moveNumber);
            move.setMatch(match);
            move.setStarting_x(piecePos.x);
            move.setStarting_y(piecePos.y);
            move.setTarget_x(targetPos.x);
            move.setTarget_y(targetPos.y);
            DatabaseUtil.persist(move);

            moveNumber += 1;
        }
        turn_number += 1;
        
        // System.out.println(new CommandCPSerializer().serialize(command));
        
        broadcastToPlayers((id) -> id != command.getPlayerId(), new PieceMovedResponse(command.getPlayerId(), command.getPieceX(), command.getPieceY(), command.getTileIds()));
        
        if (board.gameOver().isPresent()) {
            broadcastToAllPlayers(new EndOfGameResponse(board.gameOver().get().getId()));
        }
        
        return null;
    }

    @Override
    public void onPlayerJoin(int playerId) {
        if (getNumberOfPlayers() == 1) {
            sendToPlayer(playerId, new GameConnectionSuccessfulResponse(gameId, desc, playerId));
        }
        else if (getNumberOfPlayers() == 2) {
            sendToPlayer(playerId, new GameConnectionSuccessfulResponse(gameId, getAnotherColoredDesc(), playerId));
        }
        else {
            sendToPlayer(playerId, new GameConnectionUnsuccessfulResponse());
            transferPlayerTo(playerId, mainHub);
        }
    }

    private void closeIfEmpty() {
        if (getNumberOfPlayers() == 0) {
            mainHub.closeLobby(this);
        }
    }

    public VariantStartDescription getDesc() {
        return desc;
    }

    @Override
    public void onCommand(Command command) {
        super.onCommand(command);
        this.closeIfEmpty();
    }    

    public VariantStartDescription getAnotherColoredDesc() {
        String oppos = switch (desc.getColor().toLowerCase(Locale.US)) {
            case "white" -> "black";
            case "black" -> "white";
            default -> null;
        };
        return new VariantStartDescription(desc.getWidth(), desc.getHeight(), desc.getName(), oppos);
    }
}
