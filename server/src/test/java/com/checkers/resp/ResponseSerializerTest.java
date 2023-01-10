package com.checkers.resp;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.checkers.resp.response.EndOfGameResponse;
import com.checkers.resp.response.GameConnectionSuccessfulResponse;
import com.checkers.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers.resp.response.IncorrectMoveResponse;
import com.checkers.resp.response.PieceMovedResponse;
import com.checkers.resp.response.PlayerDisconnectedResponse;
import com.checkers.resp.response.WrongCommandResponse;

public class ResponseSerializerTest {
    ResponseSerializer serializer = new ResponseSerializer();

    int testGameId = 42;
    int testPlayerId = 17;
    int testPieceId = 10;
    List<Integer> testTileIds = Arrays.asList(11, 6, 20);

    @Test
    public void endOfGameTest(){
        EndOfGameResponse resp = new EndOfGameResponse(testPlayerId);

        String result = serializer.serialize(resp);

        assertEquals(result, "EOG 17");
    }
    @Test
    public void gameConnectionSuccessfulTest(){
        GameConnectionSuccessfulResponse resp = new GameConnectionSuccessfulResponse(testGameId);

        String result = serializer.serialize(resp);

        assertEquals(result, "GCS 42");
    }
    @Test
    public void gameConnectionUnsuccessfulTest(){
        GameConnectionUnsuccessfulResponse resp = new GameConnectionUnsuccessfulResponse();

        String result = serializer.serialize(resp);

        assertEquals(result, "GCU");
    }
    @Test
    public void incorrectMoveTest(){
        IncorrectMoveResponse resp = new IncorrectMoveResponse();

        String result = serializer.serialize(resp);

        assertEquals(result, "INCORRMOVE");
    }
    @Test
    public void pieceMovedTest(){
        PieceMovedResponse resp = new PieceMovedResponse(testPlayerId, testPieceId, testTileIds);

        String result = serializer.serialize(resp);

        assertEquals(result.trim(), "MOVED 17 10 11 6 20");
    }
    @Test
    public void playerDisconnectedTest(){
        PlayerDisconnectedResponse resp = new PlayerDisconnectedResponse(testPlayerId);

        String result = serializer.serialize(resp);

        assertEquals(result, "DISCONNECT 17");
    }
    @Test
    public void wrongCommandTest(){
        WrongCommandResponse resp = new WrongCommandResponse();

        String result = serializer.serialize(resp);

        assertEquals(result, "WRONGCOMM");
    }
}
