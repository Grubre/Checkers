package com.checkers_core.resp.parser;

import java.util.ArrayList;
import java.util.List;

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

public class ResponseCPParser implements ResponseParser {

    @Override
    public Response parse(String text) throws ParsingException {
        String[] words = text.split(" ");

        try {
            if("GCU".equals(words[0])) {
                return new GameConnectionUnsuccessfulResponse();
            } 
            else if("GCS".equals(words[0])) {
                int gameId = Integer.parseInt(words[1]);
                int playerId = Integer.parseInt(words[2]);
                int width = Integer.parseInt(words[3]);
                int height = Integer.parseInt(words[4]);
                String variant = words[5];
                String color = words[6];

                return new GameConnectionSuccessfulResponse(gameId, new VariantStartDescription(width, height, variant, color), playerId);
            }
            else if("MOVED".equals(words[0])) {
                int playerId = Integer.parseInt(words[1]);
                int pieceX = Integer.parseInt(words[2]);
                int pieceY = Integer.parseInt(words[3]);
                List<Integer> tileIds = new ArrayList<>();

                for(int i = 4; i < words.length; i++) {
                    tileIds.add(Integer.parseInt(words[i]));
                }

                return new PieceMovedResponse(playerId, pieceX, pieceY, tileIds);
            }
            else if("EOG".equals(words[0])) {
                int winnerId = Integer.parseInt(words[1]);
                
                return new EndOfGameResponse(winnerId);
            }
            else if("DISCONNECT".equals(words[0])) {
                int winnerId = Integer.parseInt(words[1]);

                return new PlayerDisconnectedResponse(winnerId);
            }
            else if("INCORRMOVE".equals(words[0])) {
                return new IncorrectMoveResponse();
            }
            else if("WRONGCOMM".equals(words[0])) {
                return new WrongCommandResponse();
            }
            else if("LOBBIES".equals(words[0])) {
                List<Integer> lobbiesIds = new ArrayList<>();

                for(int i = 1; i < words.length; i++) {
                    lobbiesIds.add(Integer.parseInt(words[i]));
                }

                return new LobbyListResponse(lobbiesIds);
            }
            else {
                throw new ParsingException();
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new ParsingException(e);
        }
    }
    
}
