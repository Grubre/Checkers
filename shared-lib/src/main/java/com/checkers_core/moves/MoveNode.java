package com.checkers_core.moves;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.BoardPos;

public class MoveNode implements Iterable<MoveNode>{
    int x;
    int y;
    public List<BoardPos> removedPawns;
    public List<MoveNode> possibleMoves = new ArrayList<>();

    private boolean markedMax = false;
    private boolean hasCaptured = false;

    private int maxPathLen = 0;

    public MoveNode(int x, int y, List<BoardPos> parentRemovedPos) {
        this.x = x;
        this.y = y;
        this.removedPawns = parentRemovedPos;
    }

    public void print(String parentString)
    {
        String myString = new String("("+x + ", " + y + ", len: " + maxPathLen + "), ");
        if(possibleMoves.isEmpty()) {
            System.out.println(parentString + myString);
        }
        for(MoveNode move : possibleMoves)
        {
            move.print(parentString + myString);
        }
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

    public void toMaxMoveList(List<Move> list, List<BoardPos> visitedFields) {
        visitedFields.add(new BoardPos(x, y));

        Move move = new Move(new ArrayList<>(visitedFields), removedPawns);

        if(possibleMoves == null || possibleMoves.isEmpty()) {
            list.add(move);
        }

        for (MoveNode newMove : possibleMoves) {
            if(newMove.isMarkedForMove()) {
                newMove.toMaxMoveList(list, visitedFields);
            }
        }

        visitedFields.remove(visitedFields.size() - 1);

    }

    public BoardPos getPos()
    {
        return new BoardPos(x, y);
    }

    public void setHasCaptured(boolean hasCaptured)
    {
        this.hasCaptured = hasCaptured;
    }

    public boolean hasCaptured()
    {
        return hasCaptured;
    }

    public int getLongestBranchLength()
    {
        int max = 0;
        for(MoveNode path : possibleMoves)
        {
            max = Math.max(max, path.getLongestBranchLength());
        }
        maxPathLen = max + 1;
        return max + 1;
    }

    public void markNonMaxBranches(int externalMaxSize) {
        getLongestBranchLength();
        countAndMark(externalMaxSize);
    }

    private void countAndMark(int externalMaxSize)
    {
        if(maxPathLen >= externalMaxSize) {
            markedMax = true;
        }
        for(MoveNode child : possibleMoves)
        {
            if(child.maxPathLen + 1 >= externalMaxSize)
            {
                child.markedMax = true;
                child.countAndMark(externalMaxSize - 1);
            }
        }
    }

    public boolean isMarkedForMove()
    {
        return markedMax;
    }

    @Override
    public Iterator<MoveNode> iterator() {
        return possibleMoves.iterator();
    }
}