package com.checkers_core.resp.response;

import com.checkers_core.resp.ResponseVisitor;

public class GameConnectionUnsuccessfulResponse extends Response {

    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitGameConnectionUnsuccessful(this);    
    }
       
}
