package com.checkers_core.boards;

import com.checkers_core.VariantStartDescription;
import com.checkers_core.pawns.AbstractPawnFactory;
import com.checkers_core.pawns.BasicPawnFactory;
import com.checkers_core.rules.AbstractRuleFactory;
import com.checkers_core.rules.AntiCheckersVariantRuleFactory;
import com.checkers_core.rules.BasicVariantRuleFactory;

public class BoardFactory {
    public Board createBoard(VariantStartDescription desc) {
        AbstractPawnFactory pawn;
        AbstractRuleFactory rule;
        
        switch (desc.getName()) {
            case "BASIC" -> {
                pawn = new BasicPawnFactory();
                rule = new BasicVariantRuleFactory();
            }
            case "ANTI" -> {
                pawn = new BasicPawnFactory();
                rule = new AntiCheckersVariantRuleFactory();
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }

        Board board = new BasicBoard(desc.getWidth(), desc.getHeight(), pawn, rule);

        return board;
    }
}
