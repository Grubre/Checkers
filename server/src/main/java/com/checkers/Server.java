package com.checkers;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.checkers.lobby.MainLobby;

public class Server {

    MainLobby mainLobby = new MainLobby();

    int port;

    Server(int port) {
        this.port = port;
    }

    void start() throws IOException {
        try (ServerSocket listener = new ServerSocket(port)) {
            System.out.println("Started checkers server.");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            while (true) {
                try {
                    pool.execute(new Connection(listener.accept(), mainLobby));
                    System.out.println("Connected user");
                }
                catch (IOException e) {
                    System.out.println("Error while connecting user");
                }
            }
        }

    }
}
