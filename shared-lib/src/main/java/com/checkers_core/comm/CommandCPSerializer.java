package com.checkers_core.comm;

import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.ErrorCommand;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.comm.command.ResignCommand;

public class CommandCPSerializer implements CommandSerializer, CommandVisitor<String> {

    @Override
    public String serialize(Command command) {
        return command.accept(this);
    }
    
    @Override
    public String visitDisconnect(DisconnectCommand command) {
        return "DISCONNECT";
    }

    @Override
    public String visitError(ErrorCommand command) {
        return "ERROR";
    }

    @Override
    public String visitJoinGame(JoinGameCommand command) {
        return "JOINGAME " + command.getGameId(); 
    }

    @Override
    public String visitMovePiece(MovePieceCommand command) {
        String stringTileIds = "";
        for(int tileId : command.getTileIds()) {
            stringTileIds += tileId + " ";
        }

        return "MOVE " + command.getPieceId() + " " + stringTileIds;
    }

    @Override
    public String visitNewGame(NewGameCommand command) {
        return "NEWGAME";
    }

    @Override
    public String visitResign(ResignCommand command) {
        return "RESIGN";
    }

    @Override
    public String visitListLobby(ListLobbyCommand command) {
        return "LISTLOBBIES";
    }

    @Override
    public String onUnimplemented(Command command) {
        return "NULL";
    }

    
}
