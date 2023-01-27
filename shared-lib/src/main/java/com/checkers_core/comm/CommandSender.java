package com.checkers_core.comm;

import com.checkers_core.comm.command.Command;

public abstract class CommandSender {
    
    protected CommandListener listener;

    
    /** 
     * @param command
     */
    public void sendCommand(Command command) {
        listener.onCommand(command);
    }

    
    /** 
     * @param listener
     */
    public void setListener(CommandListener listener) {
        this.listener = listener;
    }
}
