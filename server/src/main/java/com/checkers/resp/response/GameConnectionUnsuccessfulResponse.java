package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public class GameConnectionUnsuccessfulResponse extends Response {

    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitGameConnectionUnsuccessful(this);    
    }
       
}
