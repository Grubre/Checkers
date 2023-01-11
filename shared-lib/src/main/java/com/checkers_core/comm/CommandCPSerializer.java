package com.checkers_core.comm;

import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.ErrorCommand;
import com.checkers_core.comm.command.JoinGameCommand;
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
        // TODO Auto-generated method stub
        return CommandVisitor.super.visitDisconnect(command);
    }



    @Override
    public String visitError(ErrorCommand command) {
        // TODO Auto-generated method stub
        return CommandVisitor.super.visitError(command);
    }



    @Override
    public String visitJoinGame(JoinGameCommand command) {
        // TODO Auto-generated method stub
        return CommandVisitor.super.visitJoinGame(command);
    }



    @Override
    public String visitMovePiece(MovePieceCommand command) {
        // TODO Auto-generated method stub
        return CommandVisitor.super.visitMovePiece(command);
    }



    @Override
    public String visitNewGame(NewGameCommand command) {
        // TODO Auto-generated method stub
        return CommandVisitor.super.visitNewGame(command);
    }



    @Override
    public String visitResign(ResignCommand command) {
        // TODO Auto-generated method stub
        return CommandVisitor.super.visitResign(command);
    }

    @Override
    public String onUnimplemented(Command command) {
        return "NULL";
    }

    
}
