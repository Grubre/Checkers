package com.checkers.connection;

import java.io.IOException;
import java.net.Socket;

import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.ErrorCommand;
import com.checkers_core.comm.parser.CommandCPParser;
import com.checkers_core.comm.parser.CommandParser;
import com.checkers_core.comm.parser.ParsingException;
import com.checkers_core.resp.ResponseCPSerializer;
import com.checkers_core.resp.ResponseSerializer;
import com.checkers_core.resp.response.PlayerDisconnectedResponse;
import com.checkers_core.resp.response.Response;

public class ClientConnection extends PlayerConnection implements Runnable {
    ReceivingConnection rec;
    SendingConnection send;
    int playerId;
    
    CommandParser deserializer = new CommandCPParser();
    ResponseSerializer serializer = new ResponseCPSerializer();

    public ClientConnection(Socket socket, int id) throws IOException {
        this.playerId = id;
        
        rec = new ReceivingConnection(socket, this);
        send = new SendingConnection(socket);
    }

    public void onMessage(String message) {
        Command command;
        
        try {
            command = deserializer.parse(message);
        } catch (ParsingException e) {
            command = new ErrorCommand();    
        }
        
        sendCommand(command);
    }

    public void close() {
        Command command = new DisconnectCommand();
        Response resp = new PlayerDisconnectedResponse(playerId);

        onResponse(resp);
        sendCommand(command);
    }

    @Override
    public void sendCommand(Command command) {
        command.setPlayerId(playerId);

        if (listener != null) {
            super.sendCommand(command);
        }
    }

    @Override
    public void onResponse(Response response) {
        String message = serializer.serialize(response);
        send.sendMessage(message); 
    }

    @Override
    public void run() {
        rec.run();

        this.close();
    }

}
