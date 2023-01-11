package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class ResignCommand extends Command {

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitResign(this);       
    }
    
}
