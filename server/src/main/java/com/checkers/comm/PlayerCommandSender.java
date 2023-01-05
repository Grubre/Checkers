package com.checkers.comm;

import com.checkers.comm.command.Command;

public class PlayerCommandSender extends CommandSender {
    int playerId;
    CommandParser parser;

    public void onMessage(String message) {
        Command command = parser.parse(message);
        
        command.setPlayerId(playerId);
        command.setSource(this);

        sendCommand(command);
    }
}
