package com.checkers.resp;

import com.checkers.resp.response.EndOfGameResponse;
import com.checkers.resp.response.GameConnectionSuccessfulResponse;
import com.checkers.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers.resp.response.IncorrectMoveResponse;
import com.checkers.resp.response.PieceMovedResponse;
import com.checkers.resp.response.PlayerDisconnectedResponse;
import com.checkers.resp.response.WrongCommandResponse;

public interface ResponseVisitor {
    void visitEndOfGame(EndOfGameResponse response);
    void visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response);
    void visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response);
    void visitIncorrectMove(IncorrectMoveResponse response);
    void visitPieceMoved(PieceMovedResponse response);
    void visitPlayerDisconnected(PlayerDisconnectedResponse response);
    void visitWrongCommand(WrongCommandResponse response);
}
