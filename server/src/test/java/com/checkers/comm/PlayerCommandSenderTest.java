// package com.checkers.comm;

// import static org.junit.Assert.assertEquals;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.Captor;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.runners.MockitoJUnitRunner;

// import com.checkers_core.comm.command.Command;
// import com.checkers.connection.PlayerCommandSender;
// import com.checkers_core.comm.CommandListener;
// import com.checkers_core.comm.CommandVisitor;
// import com.checkers_core.comm.parser.CommandParser;
// import com.checkers_core.comm.parser.ParsingException;

// @RunWith(MockitoJUnitRunner.class)
// public class PlayerCommandSenderTest {
//     int testId = 100;

//     String testText = "TEST PARSE";

//     Command testCommand = new Command() {
//         @Override
//         public <T> T accept(CommandVisitor<T> visitor) {
//             return null;
//         }
//     };

//     @Mock
//     CommandParser parser;

//     @Mock
//     CommandListener listener;

//     @Captor
//     ArgumentCaptor<Command> commandCaptor;

//     @Test
//     public void correctInjectingPlayerDataInCommands() throws ParsingException {
//         PlayerCommandSender p = new PlayerCommandSender(testId, parser, listener);

//         Mockito.when(parser.parse(testText)).thenReturn(testCommand);

//         p.onMessage(testText);

//         Mockito.verify(listener).onCommand(commandCaptor.capture());

//         Command returnedCommand = commandCaptor.getValue();

//         assertEquals(returnedCommand.getPlayerId(), testId);
//         assertEquals(returnedCommand.getSource(), p);

//     }
// }
