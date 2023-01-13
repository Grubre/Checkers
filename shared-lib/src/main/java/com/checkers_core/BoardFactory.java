package com.checkers_core;

public class BoardFactory {
    public Board createBoard(VariantStartDescription desc) {
        AbstractPawnFactory pawn;
        AbstractRuleFactory rule;
        
        switch (desc.getName()) {
            case "BASIC" -> {
                pawn = new BasicPawnFactory();
                rule = new BasicVariantRuleFactory();
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }

        Board board = new BasicBoard(desc.getWidth(), desc.getHeight(), pawn, rule);

        return board;
    }
}
