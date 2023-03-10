package com.checkers_core.resp;

import java.util.ArrayList;
import java.util.List;

import com.checkers_core.resp.response.Response;

public class ResponseSender {
    
    protected List<ResponseListener> listeners = new ArrayList<>();

    public void sendResponse(Response response) {
        for (ResponseListener listener : listeners) {
            listener.onResponse(response);
        }
    }

    public void addListener(ResponseListener listener) {
        if(!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(ResponseListener listener) {
        listeners.remove(listener);
    }
}
