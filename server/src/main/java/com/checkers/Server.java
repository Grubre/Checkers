package com.checkers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.checkers.connection.ClientConnection;
import com.checkers.lobby.Hub;

public class Server {

    int port;
    int internalIdCounter = 1;

    Server(int port) {
        this.port = port;
    }

    private int getNewPlayerId() {
        int id = internalIdCounter;
        internalIdCounter++;
        return id;
    }

    public void start() throws IOException {

        Hub hub = new Hub();

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Started checkers server.");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            while (true) {
                try {
                    Socket newConnection = server.accept();

                    int id = getNewPlayerId();

                    ClientConnection player = new ClientConnection(newConnection, id);

                    hub.addPlayer(id, player);

                    pool.execute(player);
                    System.out.println("Connected user id: " + id);
                }
                catch (IOException e) {
                    System.out.println("Error while connecting user");
                }
            }
        }
    }
}
