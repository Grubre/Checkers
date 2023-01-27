package com.checkers.connection;

import com.checkers_core.resp.ResponseSender;
import com.checkers_core.resp.parser.ParsingException;
import com.checkers_core.resp.parser.ResponseParser;
import com.checkers_core.resp.response.ErrorResponse;
import com.checkers_core.resp.response.Response;

import javafx.application.Platform;

/*
 * Serwerowy wysyłacz odpowiedzi. Przyjmuje tekstowe odpowiedzi z serwera i konwertuje je na odpowiednie typy, na które możemy odpowiadać.
 */
public class ServerResponseSender extends ResponseSender {
    private ResponseParser parser;    

    /**
     * Konstruktor
     * @param parser Sposób konwertowania tekstowych odpowiedzi
     */
    public ServerResponseSender(ResponseParser parser) {
        this.parser = parser;
    }

    
    /** 
     * Przekonwertuj wiadomość tekstową i przekaż ją dalej
     * @param message
     */
    public void onMessage(String message) {
        Platform.runLater(() -> {
            Response response;
            try {
                response = parser.parse(message);
            } catch (ParsingException e) {
                response = new ErrorResponse();
            }
            sendResponse(response);
        });
    }
}
