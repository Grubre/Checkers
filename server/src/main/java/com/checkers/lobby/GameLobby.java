package com.checkers.lobby;

import com.checkers.comm.command.DisconnectCommand;
import com.checkers.comm.command.MovePieceCommand;
import com.checkers.comm.command.ResignCommand;
import com.checkers.resp.response.PieceMovedResponse;
import com.checkers.resp.response.PlayerDisconnectedResponse;

public class GameLobby extends Lobby {
    Hub mainHub;
    
    public GameLobby(Hub mainHub) {
        this.mainHub = mainHub;
    }

    @Override
    public void visitDisconnect(DisconnectCommand command) {
        int playerId = command.getPlayerId();
        
        removePlayer(playerId);

        //TODO Insert game logic
        
        broadcastToPlayers(new PlayerDisconnectedResponse(playerId));

        closeIfEmpty();
    }

    @Override
    public void visitResign(ResignCommand command) {
        int playerId = command.getPlayerId();

        transferPlayerTo(playerId, command.getSource(), mainHub);

        //TODO Insert game logic
        
        broadcastToPlayers(new PlayerDisconnectedResponse(playerId));

        closeIfEmpty();
    }

    @Override
    public void visitMovePiece(MovePieceCommand command) {
        //TODO Insert game logic
        broadcastToPlayers(new PieceMovedResponse(command.getPlayerId(), 0, command.getTileIds()));
    }

    private void closeIfEmpty() {
        if (getNumberOfPlayers() == 0) {
            mainHub.closeLobby(this);
        }
    }
}
