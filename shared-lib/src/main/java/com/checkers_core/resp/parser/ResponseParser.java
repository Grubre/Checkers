package com.checkers_core.resp.parser;

import com.checkers_core.resp.response.Response;

public interface ResponseParser {
    Response parse(String text) throws ParsingException;
}
