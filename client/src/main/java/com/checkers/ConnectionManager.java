package com.checkers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javafx.application.Platform;

public final class ConnectionManager {
    private Socket socket;
    private PrintWriter socketOutput;
    private Scanner socketInput;
    private Scanner input;

    private ConnectionManager()
    {

    }

    private static ConnectionManager instance;
    synchronized public static ConnectionManager getInstance()
    {
        if(instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public boolean connect()
    {
        try {
            socket = new Socket("localhost", 58901);    
            socketOutput = new PrintWriter(socket.getOutputStream(), true);
            socketInput = new Scanner(socket.getInputStream());
            input = new Scanner(System.in);

            Thread serverOutput = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(socketInput.hasNextLine()) {
                        System.out.println(socketInput.nextLine());
                    }
                    Platform.runLater(() -> {
                        MainMenu.getInstance().setCurrent();
                    });
                }
            });

            serverOutput.start();
        } catch (Exception e ) {
            return false;
        }
        return true;
    }
}
