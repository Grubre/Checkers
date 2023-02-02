package com.checkers_core.resp;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.resp.response.EndOfGameResponse;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.IncorrectMoveResponse;
import com.checkers_core.resp.response.LobbyListResponse;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.PlayerDisconnectedResponse;
import com.checkers_core.resp.response.Response;
import com.checkers_core.resp.response.WrongCommandResponse;

public class ResponseCPSerializer implements ResponseSerializer, ResponseVisitor<String> {
    @Override
    public String serialize(Response response) {
        return response.accept(this);
    }

    @Override
    public String onUnimplemented(Response response) {
        return "NULL";
    }

    @Override
    public String visitEndOfGame(EndOfGameResponse response) {
        return "EOG " + response.getWinnerId();
        
    }

    @Override
    public String visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        VariantStartDescription desc = response.getDesc();
        return "GCS " + response.getGameId() + " " + response.getPlayerId() + " " + desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor();
        
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

        return "MOVED " + response.getPlayerId() + " " + response.getPieceX() + " " + response.getPieceY() + " " + stringTileIds;
    }

    @Override
    public String visitPlayerDisconnected(PlayerDisconnectedResponse response) {
        return "DISCONNECT " + response.getPlayerId();
        
    }

    @Override
    public String visitWrongCommand(WrongCommandResponse response) {
        return "WRONGCOMM";
        
    }

    @Override
    public String visitLobbyList(LobbyListResponse response) {
        String stringLobbyIds = "";
        for(int lobbyId : response.getLobbyIds()) {
            stringLobbyIds += lobbyId + " ";
        }

        return "LOBBIES " + stringLobbyIds;
    }

    
}
