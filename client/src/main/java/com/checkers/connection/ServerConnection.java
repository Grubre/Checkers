package com.checkers.connection;

import java.io.IOException;
import java.net.Socket;
import com.checkers_core.comm.CommandCPSerializer;
import com.checkers_core.resp.parser.ResponseCPParser;

/*
 * Pełne połączenie z serwerem. Pośredniczy w wysyłaniu i odbieraniu wiadomości.
 */
public class ServerConnection {
    private ReceivingConnection rec;
    private SendingConnection send;
    private ServerCommandListener listener;
    private ServerResponseSender sender;
    
    /**
     * Konstruktor
     * Łączy się z serwerem
     * @param host Ip serwera
     * @param port Port 
     * @throws IOException
     */
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

    
    /** 
     * @return ServerResponseSender
     */
    public ServerResponseSender getSender() {
        return sender;
    }

    
    /** 
     * @return ServerCommandListener
     */
    public ServerCommandListener getListener() {
        return listener;
    }

}
