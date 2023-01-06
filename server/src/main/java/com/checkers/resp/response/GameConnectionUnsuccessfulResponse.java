package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public class GameConnectionUnsuccessfulResponse extends Response {

    @Override
    public void accept(ResponseVisitor visitor) {
        visitor.visitGameConnectionUnsuccessful(this);    
    }
       
}
