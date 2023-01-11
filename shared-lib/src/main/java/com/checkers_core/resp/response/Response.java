package com.checkers_core.resp.response;

import com.checkers_core.resp.ResponseVisitor;

public abstract class Response {
    public abstract <T> T accept(ResponseVisitor<T> visitor);
}
