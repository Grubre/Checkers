package com.checkers_core.comm.command;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.comm.CommandVisitor;

public class NewGameCommand extends Command {

    final VariantStartDescription desc;

    
    /** 
     * @return VariantStartDescription
     */
    public VariantStartDescription getDesc() {
        return desc;
    }

    public NewGameCommand(VariantStartDescription desc) {
        this.desc = desc;
    }

    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(CommandVisitor<T> visitor) {
        return visitor.visitNewGame(this);
    }
    
}
