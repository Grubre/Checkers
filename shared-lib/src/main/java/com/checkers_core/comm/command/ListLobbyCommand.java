package com.checkers_core.comm.command;

import com.checkers_core.comm.CommandVisitor;

public class ListLobbyCommand extends Command {

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitListLobby(this);
    }
    
}
