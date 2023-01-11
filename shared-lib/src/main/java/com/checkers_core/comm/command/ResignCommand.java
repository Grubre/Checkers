package com.checkers_core.comm.command;

import com.checkers_core.comm.CommandVisitor;

public class ResignCommand extends Command {

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitResign(this);       
    }
    
}
