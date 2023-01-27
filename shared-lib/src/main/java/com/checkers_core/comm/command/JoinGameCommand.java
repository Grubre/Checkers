package com.checkers_core.comm.command;

import com.checkers_core.comm.CommandVisitor;

public class JoinGameCommand extends Command {
    final int gameId;

    public JoinGameCommand(int gameId) {
        this.gameId = gameId;
    }

    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitJoinGame(this);       
    }

    
    /** 
     * @return int
     */
    public int getGameId() {
        return gameId;
    }
}
