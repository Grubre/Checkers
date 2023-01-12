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

    private int maxPathLen = 0;

    public MoveNode(int x, int y, List<BoardPos> parentRemovedPos) {
        this.x = x;
        this.y = y;
        this.removedPawns = parentRemovedPos;
    }

    public void print(String parentString)
    {
        String myString = new String("("+x + ", " + y + ", len: " + maxPathLen + "), ");
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

    public int getLongestPathLength()
    {
        int max = 0;
        System.out.println("Max: "+max);
        for(MoveNode path : possibleMoves)
        {
            max = Math.max(max, path.getLongestPathLength());
        }
        maxPathLen = max;
        return max + 1;
    }

    public void pruneNonMaxPaths() {
        getLongestPathLength();
        countAndPrune();
    }

    private void countAndPrune()
    {
        for(MoveNode child : possibleMoves)
        {
            if(child.maxPathLen + 1 >= maxPathLen)
            {
                child.markedMax = true;
                child.countAndPrune();
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