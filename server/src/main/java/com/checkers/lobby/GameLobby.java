package com.checkers.lobby;

import com.checkers_core.Board;
import com.checkers_core.BoardFactory;
import com.checkers_core.Move;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.Board.BoardPos;
import com.checkers_core.comm.CommandCPSerializer;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.ResignCommand;
import com.checkers_core.resp.ResponseListener;
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
        
        broadcastToPlayers(new PlayerDisconnectedResponse(playerId));
        
        closeIfEmpty();

        return null;
    }
    
    @Override
    public Void visitResign(ResignCommand command) {
        int playerId = command.getPlayerId();
        
        transferPlayerTo(playerId, command.getSource(), mainHub);
        
        //TODO Insert game logic
        
        broadcastToPlayers(new PlayerDisconnectedResponse(playerId));
        
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
        
        for (int id : connectedPlayers.keySet()) {
            if (command.getPlayerId() != id) {
                sendToPlayer(id, (new PieceMovedResponse(command.getPlayerId(), command.getPieceX(), command.getPieceY(), command.getTileIds())));
            }
        }
        
        if (board.gameOver().isPresent()) {
            int colorId = switch (board.gameOver().get()) {
                case WHITE -> 0;
                case BLACK -> 1;
            };

            broadcastToPlayers(new EndOfGameResponse(colorId));
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

    public VariantStartDescription getAnotherColoredDesc() {
        String oppos = switch (desc.getColor().toLowerCase()) {
            case "white" -> "black";
            case "black" -> "white";
            default -> null;
        };
        return new VariantStartDescription(desc.getWidth(), desc.getHeight(), desc.getName(), oppos);
    }
}
