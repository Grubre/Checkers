package com.checkers.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Połączenie z serwerem odpowiadające za wysyłanie do niego wiadomości tekstowych
 */
public class SendingConnection {
    
    private Socket socket;
    private PrintWriter output;

    /**
     * Konstruktor
     * @param socket Socket, do którego się podepnie
     * @throws IOException
     */
    public SendingConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }
    
    /** 
     * @param message
     */
    public void sendMessage(String message) {
        output.println(message);
    }
}
