package com.checkers.connection;

import com.checkers_core.comm.CommandListener;
import com.checkers_core.comm.CommandSerializer;
import com.checkers_core.comm.command.Command;

/*
 * Serwerowy nasłuchiwacz na komendy. Będzie odbierał komendy klienta, przetwarzał je na tekst,
 * a następnie wysyłał przez wysyłające połączenie
 */
public class ServerCommandListener implements CommandListener {

    private CommandSerializer serializer;
    private SendingConnection connection;


    /**
     * Kontruktor
     * @param serializer Metoda serializacji
     * @param connection Połączenie do wysyłania przetworzonych komend
     */
    public ServerCommandListener(CommandSerializer serializer, SendingConnection connection) {
        this.serializer = serializer;
        this.connection = connection;
    }
    
    /** 
     * @param command
     */
    @Override
    public void onCommand(Command command) {
        String text = serializer.serialize(command);
        connection.sendMessage(text);
    }
    
}
