package com.checkers_core.comm.command;

import com.checkers_core.comm.CommandVisitor;

public class DisconnectCommand extends Command {

    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitDisconnect(this);
    }
    
}
