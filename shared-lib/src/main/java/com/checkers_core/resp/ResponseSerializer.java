package com.checkers_core.resp;

import com.checkers_core.resp.response.Response;

public interface ResponseSerializer {
    String serialize(Response response);
}
