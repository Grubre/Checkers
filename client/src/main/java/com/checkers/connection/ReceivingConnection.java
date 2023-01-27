package com.checkers.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


/*
 * Połączenie z serwerem, które jest w stanie odbierać od niego wiadomości tekstowe.
 * Należy je uruchomić na osobnym wątku.
 */
public class ReceivingConnection implements Runnable {
    
    private Scanner input;
    private Socket socket;
    private ServerResponseSender sender;

    /**
     * Konstruktor
     * @param socket Socket, do którego się podepnie
     * @param sender Gdzie mają zostać wysłane wiadomości do kolejnego przetwarzania
     * @throws IOException
     */
    public ReceivingConnection(Socket socket, ServerResponseSender sender) throws IOException {
        this.socket = socket;
        this.sender = sender;
        this.input = new Scanner(socket.getInputStream());
    }

    /*
     * Uruchomienie nasłuchiwania na wiadomości
     */
    @Override
    public void run() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            sender.onMessage(command);
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
