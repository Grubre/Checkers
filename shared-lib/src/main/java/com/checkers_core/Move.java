package com.checkers_core;

import java.util.ArrayList;
import java.util.List;

import com.checkers_core.Board.BoardPos;

public class Move {
    public List<Board.BoardPos> visitedFields;
    public List<Board.BoardPos> removedPawns;
    public Move()
    {
        visitedFields = new ArrayList<>();
        removedPawns = new ArrayList<>();
    }
    public Move(Move move)
    {
        visitedFields = new ArrayList<>(move.visitedFields);
        removedPawns = new ArrayList<>(move.removedPawns);
    }
    public Move(List<BoardPos> visitedFields, List<BoardPos> removedPawns) {
        this.visitedFields = visitedFields;
        this.removedPawns = removedPawns;
    }
}