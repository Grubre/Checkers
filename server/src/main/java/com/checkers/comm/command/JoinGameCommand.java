package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class JoinGameCommand extends Command {
    final int gameId;

    public JoinGameCommand(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visitJoinGame(this);       
    }

    public int getGameId() {
        return gameId;
    }
}
