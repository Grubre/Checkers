package com.checkers_core.comm.command;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.comm.CommandVisitor;

public class NewGameCommand extends Command {

    final VariantStartDescription desc;
    final boolean with_bot;

    public boolean isWithBot() {
        return with_bot;
    }

    public VariantStartDescription getDesc() {
        return desc;
    }

    public NewGameCommand(VariantStartDescription desc, boolean withBot) {
        this.desc = desc;
        this.with_bot = withBot;
    }

    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitNewGame(this);
    }
    
}
