package com.checkers.connection;

import java.io.IOException;
import java.net.Socket;
import com.checkers_core.comm.CommandCPSerializer;
import com.checkers_core.resp.parser.ResponseCPParser;

public class ServerConnection {
    ReceivingConnection rec;
    SendingConnection send;
    ServerCommandListener listener;
    ServerResponseSender sender;
    
    public ServerConnection(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        send = new SendingConnection(socket);
        listener = new ServerCommandListener(new CommandCPSerializer(), send);
        
        sender = new ServerResponseSender(new ResponseCPParser());
        rec = new ReceivingConnection(socket, sender);

        Thread thread = new Thread(rec);
        thread.setDaemon(true);
        thread.start();
    }

    public ServerResponseSender getSender() {
        return sender;
    }

    public ServerCommandListener getListener() {
        return listener;
    }

}
