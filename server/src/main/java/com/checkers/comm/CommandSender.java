package com.checkers.comm;

import com.checkers.comm.command.Command;

public abstract class CommandSender {
    
    CommandListener listener;

    public void sendCommand(Command command) {
        listener.onCommand(command);
    }

    public void setListener(CommandListener listener) {
        this.listener = listener;
    }
}
