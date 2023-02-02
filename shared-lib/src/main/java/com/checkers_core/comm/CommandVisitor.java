package com.checkers_core.comm;

import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.ErrorCommand;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.comm.command.NextMoveCommand;
import com.checkers_core.comm.command.ResignCommand;

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

    default T visitListLobby(ListLobbyCommand command) {
        return onUnimplemented(command);
    }

    default T visitNextMove(NextMoveCommand command) {
        return onUnimplemented(command);
    }
}
