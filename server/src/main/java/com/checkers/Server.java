package com.checkers;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.checkers.lobby.MainLobby;

public class Server {

    MainLobby mainLobby = new MainLobby();

    void start() throws IOException {
        try (ServerSocket listener = new ServerSocket(58901)) {
            System.out.println("Started checkers server.");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            while (true) {
                pool.execute(new Connection(listener.accept(), mainLobby));
                System.out.println("Connected user");
            }
        }

    }
}
