package com.checkers_core.comm.parser;

import com.checkers_core.comm.command.Command;

public interface CommandParser {
    Command parse(String text) throws ParsingException;
}
