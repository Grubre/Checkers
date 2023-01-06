package com.checkers.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendingConnection {
    
    Socket socket;
    PrintWriter output;

    public SendingConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMessage(String message) {
        output.println(message);
    }
}
