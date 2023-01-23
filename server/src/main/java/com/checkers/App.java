package com.checkers;

import java.io.IOException;

import com.checkers.database.DatabaseConnection;

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
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.connect();
            server.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
