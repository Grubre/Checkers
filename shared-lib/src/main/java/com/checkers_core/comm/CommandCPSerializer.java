package com.checkers_core.comm;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.ErrorCommand;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.comm.command.ResignCommand;

public class CommandCPSerializer implements CommandSerializer, CommandVisitor<String> {

    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String serialize(Command command) {
        return command.accept(this);
    }
    
    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String visitDisconnect(DisconnectCommand command) {
        return "DISCONNECT";
    }

    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String visitError(ErrorCommand command) {
        return "ERROR";
    }

    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String visitJoinGame(JoinGameCommand command) {
        return "JOINGAME " + command.getGameId(); 
    }

    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String visitMovePiece(MovePieceCommand command) {
        String stringTileIds = "";
        for(int tileId : command.getTileIds()) {
            stringTileIds += tileId + " ";
        }

        return "MOVE " + command.getPieceX() + " " + command.getPieceY() + " " + stringTileIds;
    }

    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String visitNewGame(NewGameCommand command) {
        VariantStartDescription desc = command.getDesc();
        return "NEWGAME " + desc.getWidth() + " " + desc.getHeight() + " " + desc.getName() + " " + desc.getColor();
    }

    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String visitResign(ResignCommand command) {
        return "RESIGN";
    }

    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String visitListLobby(ListLobbyCommand command) {
        return "LISTLOBBIES";
    }

    
    /** 
     * @param command
     * @return String
     */
    @Override
    public String onUnimplemented(Command command) {
        return "NULL";
    }

    
}
