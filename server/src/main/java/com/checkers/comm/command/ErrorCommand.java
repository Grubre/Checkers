package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class ErrorCommand extends Command {

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitError(this);
    }
    
}
