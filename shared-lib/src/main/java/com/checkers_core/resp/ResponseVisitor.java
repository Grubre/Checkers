package com.checkers_core.resp;

import com.checkers_core.resp.response.EndOfGameResponse;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.IncorrectMoveResponse;
import com.checkers_core.resp.response.LobbyListResponse;
import com.checkers_core.resp.response.PieceMovedResponse;
import com.checkers_core.resp.response.PlayerDisconnectedResponse;
import com.checkers_core.resp.response.WrongCommandResponse;

public interface ResponseVisitor<T> {
    T visitEndOfGame(EndOfGameResponse response);
    T visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response);
    T visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response);
    T visitIncorrectMove(IncorrectMoveResponse response);
    T visitPieceMoved(PieceMovedResponse response);
    T visitPlayerDisconnected(PlayerDisconnectedResponse response);
    T visitWrongCommand(WrongCommandResponse response);
    T visitLobbyList(LobbyListResponse response);
}
