package com.checkers.connection;

import com.checkers_core.comm.CommandListener;
import com.checkers_core.comm.CommandSerializer;
import com.checkers_core.comm.command.Command;

public class ServerCommandListener implements CommandListener {

    CommandSerializer serializer;
    SendingConnection connection;

    public ServerCommandListener(CommandSerializer serializer, SendingConnection connection) {
        this.serializer = serializer;
        this.connection = connection;
    }

    @Override
    public void onCommand(Command command) {
        String text = serializer.serialize(command);
        connection.sendMessage(text);
    }
    
}
