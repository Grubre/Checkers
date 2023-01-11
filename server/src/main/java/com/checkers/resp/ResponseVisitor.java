package com.checkers.resp;

import com.checkers.resp.response.EndOfGameResponse;
import com.checkers.resp.response.GameConnectionSuccessfulResponse;
import com.checkers.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers.resp.response.IncorrectMoveResponse;
import com.checkers.resp.response.PieceMovedResponse;
import com.checkers.resp.response.PlayerDisconnectedResponse;
import com.checkers.resp.response.WrongCommandResponse;

public interface ResponseVisitor<T> {
    T visitEndOfGame(EndOfGameResponse response);
    T visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response);
    T visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response);
    T visitIncorrectMove(IncorrectMoveResponse response);
    T visitPieceMoved(PieceMovedResponse response);
    T visitPlayerDisconnected(PlayerDisconnectedResponse response);
    T visitWrongCommand(WrongCommandResponse response);
}
