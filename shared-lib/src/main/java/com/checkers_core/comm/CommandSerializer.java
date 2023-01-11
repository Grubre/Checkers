package com.checkers_core.comm;

import com.checkers_core.comm.command.Command;

public interface CommandSerializer {
    String serialize(Command command);
}
