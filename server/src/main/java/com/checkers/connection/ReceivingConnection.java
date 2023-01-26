package com.checkers.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceivingConnection implements Runnable {
    
    Scanner input;
    Socket socket;
    ClientConnection connectedPlayer;
    
    public ReceivingConnection(Socket socket, ClientConnection player) throws IOException {
        this.socket = socket;
        this.connectedPlayer = player;
        this.input = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            connectedPlayer.onMessage(command);
        }
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
