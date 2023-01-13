package com.checkers.connection;

import com.checkers_core.resp.ResponseSender;
import com.checkers_core.resp.parser.ParsingException;
import com.checkers_core.resp.parser.ResponseParser;
import com.checkers_core.resp.response.ErrorResponse;
import com.checkers_core.resp.response.Response;

import javafx.application.Platform;

public class ServerResponseSender extends ResponseSender {
    ResponseParser parser;    

    public ServerResponseSender(ResponseParser parser) {
        this.parser = parser;
    }

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
