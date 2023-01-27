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

    
    /** 
     * @param parentString
     */
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

    
    /** 
     * @return List<MoveNode>
     */
    public List<MoveNode> getPossibleMoves()
    {
        return possibleMoves;
    }

    
    /** 
     * @param list
     * @param visitedFields
     */
    public void toMoveList(List<Move> list, List<BoardPos> visitedFields) {
        visitedFields.add(new BoardPos(x, y));

        Move move = new Move(new ArrayList<>(visitedFields), removedPawns);

        list.add(move);

        for (MoveNode newMove : possibleMoves) {
            newMove.toMoveList(list, visitedFields);
        }

        visitedFields.remove(visitedFields.size() - 1);

    }

    
    /** 
     * @return BoardPos
     */
    public BoardPos getPos()
    {
        return new BoardPos(x, y);
    }

    
    /** 
     * @param hasCaptured
     */
    public void setHasCaptured(boolean hasCaptured)
    {
        this.hasCaptured = hasCaptured;
    }

    
    /** 
     * @return boolean
     */
    public boolean hasCaptured()
    {
        return hasCaptured;
    }

    
    /** 
     * @return int
     */
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

    
    /** 
     * @param externalMaxSize
     */
    public void pruneNonMaxPaths(int externalMaxSize) {
        getLongestPathLength();
        countAndPrune(externalMaxSize);
    }

    
    /** 
     * @param externalMaxSize
     */
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

    
    /** 
     * @return boolean
     */
    public boolean isMarkedForMove()
    {
        return markedMax;
    }

    
    /** 
     * @return Iterator<MoveNode>
     */
    @Override
    public Iterator<MoveNode> iterator() {
        return possibleMoves.iterator();
    }
}