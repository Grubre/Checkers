package com.checkers_core.comm.parser;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.comm.command.ResignCommand;

public class CommandCPParserTest {
    CommandParser parser = new CommandCPParser();

    int testGameId = 42;
    int testPieceId = 10;
    List<Integer> testTileIds = Arrays.asList(11, 6, 20);

    @Test
    public void joinGameCommandTest() throws ParsingException {
        JoinGameCommand comm = (JoinGameCommand)parser.parse("JOINGAME " + testGameId);
        
        assertEquals(comm.getGameId(), testGameId);
    }

    @Test
    public void newGameCommandTest() throws ParsingException {
        Command comm = parser.parse("NEWGAME 5 5 BASIC White false");
        
        assert comm instanceof NewGameCommand;
    }

    @Test
    public void movePieceCommandTest() throws ParsingException {
        String testStringTileIds = "";
        for(int tileId : testTileIds) {
            testStringTileIds += tileId + " ";
        }

        MovePieceCommand comm = (MovePieceCommand)parser.parse("MOVE " + testPieceId + " " + testPieceId + " " + testStringTileIds);
        
        assertEquals(comm.getPieceX(), testPieceId);
        assertEquals(comm.getPieceY(), testPieceId);
        assertEquals(comm.getTileIds().size(), testTileIds.size());
        for (int i = 0; i < testTileIds.size(); i++) {
            assertEquals(testTileIds.get(i), comm.getTileIds().get(i));
        }
    }

    @Test
    public void resignCommandTest() throws ParsingException {
        Command comm = parser.parse("RESIGN " + testGameId);
        
        assert comm instanceof ResignCommand;
    }

    @Test
    public void listLobbyCommandTest() throws ParsingException {
        Command comm = parser.parse("LISTLOBBIES");
        
        assert comm instanceof ListLobbyCommand;
    }
}
