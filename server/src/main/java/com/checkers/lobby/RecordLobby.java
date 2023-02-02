package com.checkers.lobby;

import java.util.ArrayList;
import java.util.List;

import com.checkers.connection.PlayerConnection;
import com.checkers.database.DatabaseUtil;
import com.checkers.database.MatchEntry;
import com.checkers.database.MoveEntry;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.comm.command.NextMoveCommand;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.PieceMovedResponse;

public class RecordLobby extends Lobby {
    
    List<MoveEntry> moves;
    MatchEntry match;
    int turn_number;
    int gameId;

    public RecordLobby(int matchId, int gameId) {
        moves = DatabaseUtil.getMovesByMatchId(matchId);
        match = DatabaseUtil.getMatchById(matchId);
        this.gameId = gameId;
    }

    @Override
    public void onPlayerJoin(int playerId) {
        VariantStartDescription desc = new VariantStartDescription(match.getBoard_width(), match.getBoard_height(), match.getVariant().getType(), "NONE");

        sendToPlayer(playerId, new GameConnectionSuccessfulResponse(gameId, desc, playerId));
    }

    public Void visitNextMove(NextMoveCommand command) {

        List<MoveEntry> filteredMoves = moves.stream().filter((entry) -> entry.getTurn_number() == turn_number).toList();

        if(filteredMoves.isEmpty()) {
            return null;
        }
        
        int pieceX = filteredMoves.get(0).getStarting_x();
        int pieceY = filteredMoves.get(0).getStarting_y();
        
        List<Integer> tileIds = new ArrayList<>();
        
        for (MoveEntry entry : filteredMoves) {
            int tileX = entry.getTarget_x();
            int tileY = entry.getTarget_y();
            
            //tile.getX() + tile.getY() * board.xDim
            tileIds.add(tileX + tileY * match.getBoard_width());
        }

        broadcastToAllPlayers(new PieceMovedResponse(-1, pieceX, pieceY, tileIds));

        return null;
    }
}
