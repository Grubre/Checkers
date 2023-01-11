package com.checkers.resp;

import com.checkers.connection.SendingConnection;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.ResponseSerializer;
import com.checkers_core.resp.response.Response;

public class PlayerResponseListener implements ResponseListener {
    SendingConnection connection;
    ResponseSerializer serializer;

    public PlayerResponseListener(SendingConnection connection, ResponseSerializer serializer) {
        this.connection = connection;
        this.serializer = serializer;
    }

    @Override
    public void onResponse(Response response) {
        String message = serializer.serialize(response);
        connection.sendMessage(message);   
    }

}
