package com.checkers.comm.command;

import com.checkers.comm.CommandVisitor;

public class NewGameCommand extends Command {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visitNewGame(this);
    }
    
}
