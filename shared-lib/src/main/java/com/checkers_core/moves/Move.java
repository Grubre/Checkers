package com.checkers_core.moves;

import java.util.ArrayList;
import java.util.List;

import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.BoardPos;

public class Move{
    public List<Board.BoardPos> visitedFields;
    
    public Move()
    {
        visitedFields = new ArrayList<>();
    }
    public Move(Move move)
    {
        visitedFields = new ArrayList<>(move.visitedFields);
    }

    public Move(List<BoardPos> visitedFields, List<BoardPos> removedPawns) {
        this.visitedFields = visitedFields;
    }

    public void appendPosition(Board.BoardPos pos) {
        this.visitedFields.add(pos);
    }
}