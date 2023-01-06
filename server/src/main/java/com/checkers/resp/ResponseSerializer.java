package com.checkers.resp;

import com.checkers.resp.response.EndOfGameResponse;
import com.checkers.resp.response.GameConnectionSuccessfulResponse;
import com.checkers.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers.resp.response.IncorrectMoveResponse;
import com.checkers.resp.response.PieceMovedResponse;
import com.checkers.resp.response.PlayerDisconnectedResponse;
import com.checkers.resp.response.Response;
import com.checkers.resp.response.WrongCommandResponse;

public class ResponseSerializer implements ResponseVisitor {
    String result;
    
    public String serialize(Response response) {
        response.accept(this);
        return result;
    }

    @Override
    public void visitEndOfGame(EndOfGameResponse response) {
        result = "EOG " + response.getWinnerId();
        
    }

    @Override
    public void visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        result = "GCS " + response.getGameId();
        
    }

    @Override
    public void visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response) {
        result = "GCU";
        
    }

    @Override
    public void visitIncorrectMove(IncorrectMoveResponse response) {
        result = "INCORRMOVE";
    }

    @Override
    public void visitPieceMoved(PieceMovedResponse response) {
        String stringTileIds = "";
        for(int tileId : response.getTileIds()) {
            stringTileIds += tileId + " ";
        }

        result = "MOVED " + response.getPlayerId() + " " + response.getPieceId() + " " + stringTileIds;
    }

    @Override
    public void visitPlayerDisconnected(PlayerDisconnectedResponse response) {
        result = "DISCONNECT " + response.getPlayerId();
        
    }

    @Override
    public void visitWrongCommand(WrongCommandResponse response) {
        result = "WRONGCOMM";
        
    }
}
