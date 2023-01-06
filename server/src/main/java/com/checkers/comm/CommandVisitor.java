package com.checkers.comm;

import com.checkers.comm.command.Command;
import com.checkers.comm.command.DisconnectCommand;
import com.checkers.comm.command.JoinGameCommand;
import com.checkers.comm.command.MovePieceCommand;
import com.checkers.comm.command.NewGameCommand;
import com.checkers.comm.command.ResignCommand;

public interface CommandVisitor {
    
    void onUnimplemented(Command command);
    
    default void visitDisconnect(DisconnectCommand command) {
        onUnimplemented(command);
    }

    default void visitJoinGame(JoinGameCommand command) {
        onUnimplemented(command);
    }

    default void visitMovePiece(MovePieceCommand command) {
        onUnimplemented(command);
    }

    default void visitNewGame(NewGameCommand command) {
        onUnimplemented(command);
    }

    default void visitResign(ResignCommand command) {
        onUnimplemented(command);
    }
}
