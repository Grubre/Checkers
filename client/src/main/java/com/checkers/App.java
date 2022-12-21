package com.checkers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class App  {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 58901);    
            PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);
            Scanner socketInput = new Scanner(socket.getInputStream());
            Scanner input = new Scanner(System.in);

            while(input.hasNextLine()) {
                String command = input.nextLine();
                socketOutput.println(command);
                String result = socketInput.nextLine();
                System.out.println(result);
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}