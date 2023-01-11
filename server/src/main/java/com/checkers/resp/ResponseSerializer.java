package com.checkers.resp;

import com.checkers.resp.response.EndOfGameResponse;
import com.checkers.resp.response.GameConnectionSuccessfulResponse;
import com.checkers.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers.resp.response.IncorrectMoveResponse;
import com.checkers.resp.response.PieceMovedResponse;
import com.checkers.resp.response.PlayerDisconnectedResponse;
import com.checkers.resp.response.Response;
import com.checkers.resp.response.WrongCommandResponse;

public class ResponseSerializer implements ResponseVisitor<String> {
    public String serialize(Response response) {
        return response.accept(this);
    }

    @Override
    public String visitEndOfGame(EndOfGameResponse response) {
        return "EOG " + response.getWinnerId();
        
    }

    @Override
    public String visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        return "GCS " + response.getGameId();
        
    }

    @Override
    public String visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response) {
        return "GCU";
        
    }

    @Override
    public String visitIncorrectMove(IncorrectMoveResponse response) {
        return "INCORRMOVE";
    }

    @Override
    public String visitPieceMoved(PieceMovedResponse response) {
        String stringTileIds = "";
        for(int tileId : response.getTileIds()) {
            stringTileIds += tileId + " ";
        }

        return "MOVED " + response.getPlayerId() + " " + response.getPieceId() + " " + stringTileIds;
    }

    @Override
    public String visitPlayerDisconnected(PlayerDisconnectedResponse response) {
        return "DISCONNECT " + response.getPlayerId();
        
    }

    @Override
    public String visitWrongCommand(WrongCommandResponse response) {
        return "WRONGCOMM";
        
    }
}
