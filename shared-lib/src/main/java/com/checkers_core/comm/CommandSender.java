package com.checkers_core.comm;

import com.checkers_core.comm.command.Command;

public abstract class CommandSender {
    
    protected CommandListener listener;

    public void sendCommand(Command command) {
        listener.onCommand(command);
    }

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }
}
