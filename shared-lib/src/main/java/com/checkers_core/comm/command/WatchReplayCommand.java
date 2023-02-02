package com.checkers_core.comm.command;

import com.checkers_core.comm.CommandVisitor;

public class WatchReplayCommand extends Command{

    final int match_id;

    public WatchReplayCommand(int matchId) {
        this.match_id = matchId;
    }

    public int getMatchId() {
        return match_id;
    }

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitWatchReplayCommand(this);
    }
    
}
