package com.checkers;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    
    /** 
     * @param args
     */
    public static void main( String[] args )
    {
        Server server = new Server(58901);
        try {
            server.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
