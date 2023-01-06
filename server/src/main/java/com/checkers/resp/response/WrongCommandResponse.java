package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public class WrongCommandResponse extends Response {

    @Override
    public void accept(ResponseVisitor visitor) {
        visitor.visitWrongCommand(this);    
    }
    
}
