package com.checkers.comm;

import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.ErrorCommand;
import com.checkers_core.comm.parser.CommandParser;
import com.checkers_core.comm.parser.ParsingException;
import com.checkers_core.comm.CommandListener;
import com.checkers_core.comm.CommandSender;

public class PlayerCommandSender extends CommandSender {
    int playerId;
    CommandParser parser;

    public PlayerCommandSender(int playerId, CommandParser parser, CommandListener listener) {
        this.playerId = playerId;
        this.parser = parser;
        this.listener = listener;
    }
    
    public void onMessage(String message) {
        Command command;
        
        try {
            command = parser.parse(message);
        } catch (ParsingException e) {
            command = new ErrorCommand();    
        }
        
        command.setPlayerId(playerId);
        command.setSource(this);

        sendCommand(command);
    }

    public void disconnect() {
        Command command = new DisconnectCommand();

        command.setPlayerId(playerId);
        command.setSource(this);

        sendCommand(command);
    }
}
