package com.checkers_core.resp;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.resp.response.EndOfGameResponse;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.IncorrectMoveResponse;
import com.checkers_core.resp.response.LobbyListResponse;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.PlayerDisconnectedResponse;
import com.checkers_core.resp.response.WrongCommandResponse;

public class ResponseCPSerializerTest {
    ResponseSerializer serializer = new ResponseCPSerializer();

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
        GameConnectionSuccessfulResponse resp = new GameConnectionSuccessfulResponse(testGameId, new VariantStartDescription(4, 4, "BASIC", "White"), testPlayerId);

        String result = serializer.serialize(resp);

        assertEquals(result, "GCS 42 17 4 4 BASIC White");
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
        PieceMovedResponse resp = new PieceMovedResponse(testPlayerId, testPieceId, testPieceId, testTileIds);

        String result = serializer.serialize(resp);

        assertEquals(result.trim(), "MOVED 17 10 10 11 6 20");
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

    @Test
    public void lobbyListTest(){
        LobbyListResponse resp = new LobbyListResponse(List.of(3, 4, 1));

        String result = serializer.serialize(resp);

        assertEquals(result, "LOBBIES 3 4 1 ");
    }
}
