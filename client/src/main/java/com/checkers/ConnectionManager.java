package com.checkers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public final class ConnectionManager {
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
            Socket socket = new Socket("localhost", 58901);    
            PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);
            Scanner socketInput = new Scanner(socket.getInputStream());
            Scanner input = new Scanner(System.in);

            Thread serverOutput = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(socketInput.hasNextLine()) {
                        System.out.println(socketInput.nextLine());
                    }
                }
            });

            serverOutput.start();
        } catch (Exception e )
        {
            return false;
        }
        return true;
    }
}
