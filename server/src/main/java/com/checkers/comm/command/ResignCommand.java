package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class ResignCommand extends Command {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visitResign(this);       
    }
    
}
