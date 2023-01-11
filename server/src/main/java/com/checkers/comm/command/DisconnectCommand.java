package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class DisconnectCommand extends Command {

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitDisconnect(this);
    }
    
}
