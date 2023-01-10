package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public abstract class Response {
    public abstract void accept(ResponseVisitor visitor);
}
