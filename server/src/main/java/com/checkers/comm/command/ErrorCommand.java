package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class ErrorCommand extends Command {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visitError(this);
    }
    
}
