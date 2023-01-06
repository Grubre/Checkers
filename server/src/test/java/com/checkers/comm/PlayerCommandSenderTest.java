package com.checkers.comm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.checkers.comm.command.Command;
import com.checkers.comm.parser.CommandParser;

@RunWith(MockitoJUnitRunner.class)
public class PlayerCommandSenderTest {
    int testId = 100;

    String testText = "TEST PARSE";

    Command testCommand = new Command() {
        @Override
        public void accept(CommandVisitor visitor) {
        }
    };

    @Mock
    CommandParser parser;

    @Mock
    CommandListener listener;

    @Captor
    ArgumentCaptor<Command> commandCaptor;

    @Test
    public void correctInjectingPlayerDataInCommands() {
        PlayerCommandSender p = new PlayerCommandSender(testId, parser, listener);

        Mockito.when(parser.parse(testText)).thenReturn(testCommand);

        p.onMessage(testText);

        Mockito.verify(listener).onCommand(commandCaptor.capture());

        Command returnedCommand = commandCaptor.getValue();

        assertEquals(returnedCommand.getPlayerId(), testId);
        assertEquals(returnedCommand.getSource(), p);

    }
}
