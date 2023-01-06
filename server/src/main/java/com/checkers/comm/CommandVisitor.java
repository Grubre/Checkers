package com.checkers.comm;

import com.checkers.comm.command.Command;
import com.checkers.comm.command.DisconnectCommand;
import com.checkers.comm.command.JoinGameCommand;
import com.checkers.comm.command.MovePieceCommand;
import com.checkers.comm.command.NewGameCommand;
import com.checkers.comm.command.ResignCommand;

public abstract class CommandVisitor {
    
    protected abstract void onUnimplemented(Command command);
    
    public void visitDisconnect(DisconnectCommand command) {
        onUnimplemented(command);
    }

    public void visitJoinGame(JoinGameCommand command) {
        onUnimplemented(command);
    }

    public void visitMovePiece(MovePieceCommand command) {
        onUnimplemented(command);
    }

    public void visitNewGame(NewGameCommand command) {
        onUnimplemented(command);
    }

    public void visitResign(ResignCommand command) {
        onUnimplemented(command);
    }
}
