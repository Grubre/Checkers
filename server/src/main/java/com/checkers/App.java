package com.checkers;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server server = new Server(58901);
        try {
            server.start();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
