package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class DisconnectCommand extends Command {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visitDisconnect(this);
    }
    
}
