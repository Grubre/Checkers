package com.checkers_core.comm.command;

import com.checkers_core.comm.CommandSender;
import com.checkers_core.comm.CommandVisitor;

public abstract class Command {
    int playerId;
    CommandSender source;

    public abstract <T> T accept(CommandVisitor<T> visitor);

    public CommandSender getSource() {
        return source;
    }

    public void setSource(CommandSender source) {
        this.source = source;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
