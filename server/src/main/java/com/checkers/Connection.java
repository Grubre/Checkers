package com.checkers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.checkers.lobby.AbstractLobby;

public class Connection implements Runnable {

    Socket socket;

    PrintWriter output;
    Scanner input;

    AbstractLobby currentLobby;

    Connection(Socket socket, AbstractLobby currentLobby) {
        this.socket = socket;
        this.currentLobby = currentLobby;

    }

    @Override
    public void run() {   
        System.out.println("Running user thread");
        try {
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new Scanner(socket.getInputStream());

            while(input.hasNextLine()) {
                String command = input.nextLine();
                String result = currentLobby.processMessage(command);
                output.println(result);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
    
}
