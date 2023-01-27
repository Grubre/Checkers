package com.checkers_core.resp.response;

import com.checkers_core.resp.ResponseVisitor;

public class ErrorResponse extends Response {

    
    /** 
     * @param visitor
     * @return T
     */
    @Override
    public <T> T accept(ResponseVisitor<T> visitor) {
        return visitor.visitErrorResponse(this);
    }
    
}
