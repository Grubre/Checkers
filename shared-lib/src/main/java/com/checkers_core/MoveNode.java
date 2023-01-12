package com.checkers_core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.checkers_core.Board.BoardPos;

public class MoveNode implements Iterable<MoveNode>{
    int x;
    int y;
    List<BoardPos> removedPawns;
    List<MoveNode> possibleMoves = new ArrayList<>();

    public MoveNode(int x, int y, List<BoardPos> parentRemovedPos) {
        this.x = x;
        this.y = y;
        this.removedPawns = parentRemovedPos;
    }

    public void print(String parentString)
    {
        String myString = new String("("+x + ", " + y + "), ");
        if(possibleMoves.size() == 0)
            System.out.println(parentString + myString);
        for(MoveNode move : possibleMoves)
        {
            move.print(parentString + myString);
        }
    }

    public List<MoveNode> getPossibleMoves()
    {
        return possibleMoves;
    }

    public void toMoveList(List<Move> list, List<BoardPos> visitedFields) {
        visitedFields.add(new BoardPos(x, y));

        Move move = new Move(new ArrayList<>(visitedFields), removedPawns);

        list.add(move);

        for (MoveNode newMove : possibleMoves) {
            newMove.toMoveList(list, visitedFields);
        }

        visitedFields.remove(visitedFields.size() - 1);

    }

    public BoardPos getPos()
    {
        return new BoardPos(x, y);
    }

    @Override
    public Iterator<MoveNode> iterator() {
        return possibleMoves.iterator();
    }
}