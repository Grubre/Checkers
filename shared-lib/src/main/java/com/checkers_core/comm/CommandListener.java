package com.checkers_core.comm;

import com.checkers_core.comm.command.Command;

public interface CommandListener {
    void onCommand(Command command);
}
