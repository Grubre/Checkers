package com.checkers.resp.response;

import com.checkers.resp.ResponseVisitor;

public class IncorrectMoveResponse extends Response {

    @Override
    public void accept(ResponseVisitor visitor) {
        visitor.visitIncorrectMove(this);    
    }
    
}
