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

    public void setHasCaptured(boolean hasCaptured)
    {
        this.hasCaptured = hasCaptured;
    }

    public boolean hasCaptured()
    {
        return hasCaptured;
    }

    public int getLongestPathLength()
    {
        int max = 0;
        for(MoveNode path : possibleMoves)
        {
            max = Math.max(max, path.getLongestPathLength());
        }
        maxPathLen = max + 1;
        return max + 1;
    }

    public void pruneNonMaxPaths(int externalMaxSize) {
        getLongestPathLength();
        countAndPrune(externalMaxSize);
    }

    private void countAndPrune(int externalMaxSize)
    {
        for(MoveNode child : possibleMoves)
        {
            if(child.maxPathLen + 1 >= externalMaxSize)
            {
                child.markedMax = true;
                child.countAndPrune(externalMaxSize - 1);
            }
        }
    }

    public boolean isMarkedMax()
    {
        return markedMax;
    }

    @Override
    public Iterator<MoveNode> iterator() {
        return possibleMoves.iterator();
    }
}