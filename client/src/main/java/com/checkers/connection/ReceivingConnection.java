package com.checkers.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceivingConnection implements Runnable {
    
    Scanner input;
    Socket socket;
    ServerResponseSender sender;
    
    public ReceivingConnection(Socket socket, ServerResponseSender sender) throws IOException {
        this.socket = socket;
        this.sender = sender;
        this.input = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            sender.onMessage(command);
        }
        //sender.disconnect();
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
