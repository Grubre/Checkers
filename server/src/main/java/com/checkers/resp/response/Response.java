package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public abstract class Response {
    public abstract <T> T accept(ResponseVisitor<T> visitor);
}
