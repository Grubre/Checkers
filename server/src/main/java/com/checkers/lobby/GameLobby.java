package com.checkers.lobby;

import java.util.Locale;

import com.checkers_core.Board;
import com.checkers_core.BoardFactory;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.Board.BoardPos;
import com.checkers_core.comm.CommandCPSerializer;
import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.ResignCommand;
import com.checkers_core.resp.response.EndOfGameResponse;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.PlayerDisconnectedResponse;

public class GameLobby extends Lobby {
    Hub mainHub;
    
    VariantStartDescription desc;
    Board board;
    
    public GameLobby(Hub mainHub, VariantStartDescription desc) {
        this.mainHub = mainHub;
        this.desc = desc;
        this.board = new BoardFactory().createBoard(desc);
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

        for (int tileId : command.getTileIds()) {
            BoardPos targetPos = new BoardPos(tileId % board.xDim, tileId / board.xDim);
            board.movePiece(piecePos, targetPos);
            piecePos = targetPos;
        }
        
        System.out.println(new CommandCPSerializer().serialize(command));
        
        broadcastToPlayers((id) -> id != command.getPlayerId(), new PieceMovedResponse(command.getPlayerId(), command.getPieceX(), command.getPieceY(), command.getTileIds()));
        
        if (board.gameOver().isPresent()) {
            broadcastToAllPlayers(new EndOfGameResponse(board.gameOver().get().getId()));
        }
        
        return null;
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
