package com.checkers_core.resp;

import com.checkers_core.resp.response.EndOfGameResponse;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.IncorrectMoveResponse;
import com.checkers_core.resp.response.LobbyListResponse;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.PlayerDisconnectedResponse;
import com.checkers_core.resp.response.Response;
import com.checkers_core.resp.response.WrongCommandResponse;

public interface ResponseVisitor<T> {
    T onUnimplemented(Response response);

    default T visitEndOfGame(EndOfGameResponse response) {
        return onUnimplemented(response);
    }
    default T visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        return onUnimplemented(response);
    }
    default T visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response) {
        return onUnimplemented(response);
    }
    default T visitIncorrectMove(IncorrectMoveResponse response) {
        return onUnimplemented(response);
    }
    default T visitPieceMoved(PieceMovedResponse response) {
        return onUnimplemented(response);
    }
    default T visitPlayerDisconnected(PlayerDisconnectedResponse response) {
        return onUnimplemented(response);
    }
    default T visitWrongCommand(WrongCommandResponse response) {
        return onUnimplemented(response);
    }
    default T visitLobbyList(LobbyListResponse response) {
        return onUnimplemented(response);
    }
}
