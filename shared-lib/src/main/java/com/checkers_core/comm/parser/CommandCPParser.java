package com.checkers_core.comm.parser;

import java.util.ArrayList;
import java.util.List;

import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.comm.command.ResignCommand;

public class CommandCPParser implements CommandParser {
    @Override
    public Command parse(String text) throws ParsingException {
        String[] words = text.split(" ");

        try {
            if("NEWGAME".equals(words[0])) {
                return new NewGameCommand();
            } 
            else if("JOINGAME".equals(words[0])) {
                int gameId = Integer.parseInt(words[1]);

                return new JoinGameCommand(gameId);
            }
            else if("MOVE".equals(words[0])) {
                int pieceId = Integer.parseInt(words[1]);
                List<Integer> tileIds = new ArrayList<>();

                for(int i = 2; i < words.length; i++) {
                    tileIds.add(Integer.parseInt(words[i]));
                }

                return new MovePieceCommand(pieceId, tileIds);
            }
            else if("RESIGN".equals(words[0])) {
                return new ResignCommand();
            }
            else {
                throw new ParsingException();
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new ParsingException(e);
        }

    }
}
