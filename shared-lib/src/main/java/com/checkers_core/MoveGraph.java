package com.checkers_core;

public class MoveGraph {
    private MoveNode[][] movePerPawn;

    public MoveGraph(int xDim, int yDim)
    {
        movePerPawn = new MoveNode[xDim][yDim];
    }

    
    /** 
     * @param pawnPos
     * @param pawnMoveNode
     */
    public void setPawnMoveNode(Board.BoardPos pawnPos, MoveNode pawnMoveNode)
    {
        movePerPawn[pawnPos.x][pawnPos.y] = pawnMoveNode;
    }

    
    /** 
     * @param tilePos
     * @return MoveNode
     */
    public MoveNode getMoveNodeAt(Board.BoardPos tilePos)
    {
        return movePerPawn[tilePos.x][tilePos.y];
    }
}
