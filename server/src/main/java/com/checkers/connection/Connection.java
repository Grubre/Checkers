package com.checkers.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection implements Runnable{

    Socket socket;

    PrintWriter output;
    Scanner input;

    ConnectionController controller;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {   
        System.out.println("Running user thread");
        try {
            while(input.hasNextLine()) {
                String command = input.nextLine();
                controller.processMessage(command);
            }
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Ended user thread");
    }

    public void setController(ConnectionController controller) {
        this.controller = controller;
    }

    public void send(String message) {
        output.println(message);        
    }
}
