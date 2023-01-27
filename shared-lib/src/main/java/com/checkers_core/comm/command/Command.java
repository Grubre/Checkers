package com.checkers_core.comm.command;

import com.checkers_core.comm.CommandSender;
import com.checkers_core.comm.CommandVisitor;

public abstract class Command {
    int playerId;
    CommandSender source;

    public abstract <T> T accept(CommandVisitor<T> visitor);

    
    /** 
     * @return CommandSender
     */
    public CommandSender getSource() {
        return source;
    }

    
    /** 
     * @param source
     */
    public void setSource(CommandSender source) {
        this.source = source;
    }

    
    /** 
     * @return int
     */
    public int getPlayerId() {
        return playerId;
    }

    
    /** 
     * @param playerId
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
