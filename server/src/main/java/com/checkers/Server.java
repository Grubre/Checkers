package com.checkers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.checkers.comm.PlayerCommandSender;
import com.checkers.connection.ReceivingConnection;
import com.checkers.connection.SendingConnection;
import com.checkers.lobby.Hub;
import com.checkers.resp.PlayerResponseListener;
import com.checkers_core.comm.parser.CommandCPParser;
import com.checkers_core.resp.ResponseCPSerializer;

public class Server {

    int port;
    int internalIdCounter = 1;

    Server(int port) {
        this.port = port;
    }

    
    /** 
     * @return int
     */
    private int getNewPlayerId() {
        int id = internalIdCounter;
        internalIdCounter++;
        return id;
    }

    
    /** 
     * @throws IOException
     */
    public void start() throws IOException {

        Hub hub = new Hub();

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Started checkers server.");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            while (true) {
                try {
                    Socket newConnection = server.accept();

                    int id = getNewPlayerId();

                    PlayerCommandSender sender = new PlayerCommandSender(id, new CommandCPParser(), hub);
                    ReceivingConnection recConn = new ReceivingConnection(newConnection, sender);

                    SendingConnection sendConn = new SendingConnection(newConnection);
                    PlayerResponseListener listener = new PlayerResponseListener(sendConn, new ResponseCPSerializer());
                    
                    hub.addPlayer(id, listener);

                    pool.execute(recConn);
                    System.out.println("Connected user id: " + id);
                }
                catch (IOException e) {
                    System.out.println("Error while connecting user");
                }
            }
        }
    }
}
