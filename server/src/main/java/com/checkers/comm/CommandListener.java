package com.checkers.comm;

import com.checkers.comm.command.Command;

public interface CommandListener {
    void onCommand(Command command);
}
