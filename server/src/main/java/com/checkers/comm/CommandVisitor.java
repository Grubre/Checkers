package com.checkers.comm;

import com.checkers.comm.command.Command;
import com.checkers.comm.command.DisconnectCommand;
import com.checkers.comm.command.ErrorCommand;
import com.checkers.comm.command.JoinGameCommand;
import com.checkers.comm.command.MovePieceCommand;
import com.checkers.comm.command.NewGameCommand;
import com.checkers.comm.command.ResignCommand;

public interface CommandVisitor<T> {
    
    T onUnimplemented(Command command);
    
    default T visitDisconnect(DisconnectCommand command) {
        return onUnimplemented(command);
    }

    default T visitJoinGame(JoinGameCommand command) {
        return onUnimplemented(command);
    }

    default T visitMovePiece(MovePieceCommand command) {
        return onUnimplemented(command);
    }

    default T visitNewGame(NewGameCommand command) {
        return onUnimplemented(command);
    }

    default T visitResign(ResignCommand command) {
        return onUnimplemented(command);
    }

    default T visitError(ErrorCommand command) {
        return onUnimplemented(command);
    }
}
