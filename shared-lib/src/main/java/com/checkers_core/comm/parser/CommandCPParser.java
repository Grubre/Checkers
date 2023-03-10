package com.checkers_core.comm.parser;

import java.util.ArrayList;
import java.util.List;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.comm.command.Command;
import com.checkers_core.comm.command.DisconnectCommand;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.comm.command.ListReplaysCommand;
import com.checkers_core.comm.command.MovePieceCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.comm.command.NextMoveCommand;
import com.checkers_core.comm.command.ResignCommand;
import com.checkers_core.comm.command.WatchReplayCommand;

public class CommandCPParser implements CommandParser {
    @Override
    public Command parse(String text) throws ParsingException {
        String[] words = text.split(" ");

        try {
            if("NEWGAME".equals(words[0])) {
                int width = Integer.parseInt(words[1]);
                int height = Integer.parseInt(words[2]);
                String variant = words[3];
                String color = words[4];
                boolean withBot = Boolean.parseBoolean(words[5]);

                return new NewGameCommand(new VariantStartDescription(width, height, variant, color), withBot);
            } 
            else if("JOINGAME".equals(words[0])) {
                int gameId = Integer.parseInt(words[1]);

                return new JoinGameCommand(gameId);
            }
            else if("REPLAY".equals(words[0])) {
                int gameId = Integer.parseInt(words[1]);

                return new WatchReplayCommand(gameId);
            }
            else if("MOVE".equals(words[0])) {
                int pieceX = Integer.parseInt(words[1]);
                int pieceY = Integer.parseInt(words[2]);
                List<Integer> tileIds = new ArrayList<>();

                for(int i = 3; i < words.length; i++) {
                    tileIds.add(Integer.parseInt(words[i]));
                }

                return new MovePieceCommand(pieceX, pieceY, tileIds);
            }
            else if("RESIGN".equals(words[0])) {
                return new ResignCommand();
            }
            else if("LISTLOBBIES".equals(words[0])) {
                return new ListLobbyCommand();
            }
            else if("LISTREPLAYS".equals(words[0])) {
                return new ListReplaysCommand();
            }
            else if("DISCONNECT".equals(words[0])) {
                return new DisconnectCommand();
            }
            else if("NEXTMOVE".equals(words[0])) {
                return new NextMoveCommand();
            }
            else {
                throw new ParsingException();
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new ParsingException(e);
        }

    }
}
