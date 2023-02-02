package com.checkers_core.moves;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.BoardPos;

public class MoveGraph {
    private final int xDim;
    private final int yDim;
    private MoveNode[][] movePerPawn;

    public MoveGraph(int xDim, int yDim)
    {
        this.xDim = xDim;
        this.yDim = yDim;
        movePerPawn = new MoveNode[xDim][yDim];
    }

    public void setPawnMoveNode(Board.BoardPos pawnPos, MoveNode pawnMoveNode)
    {
        movePerPawn[pawnPos.x][pawnPos.y] = pawnMoveNode;
    }

    public MoveNode getMoveNodeAt(Board.BoardPos tilePos)
    {
        return movePerPawn[tilePos.x][tilePos.y];
    }

    public void markNonMaxBranches(boolean canCapture, int maxBranchLength) {
        for(int j = 0; j < yDim; j++)
        {
            for(int i = 0; i < xDim; i++)
            {
                MoveNode moveNode = getMoveNodeAt(new Board.BoardPos(i, j));
                if(moveNode != null) {
                    if(canCapture && moveNode.hasCaptured() || !canCapture) {
                        moveNode.markNonMaxBranches(maxBranchLength);
                    }
                }
            }
        }
    }

    public List<Move> getMaximalMoves() {
        List<Move> moves = new ArrayList<Move>();
        for(int j = 0; j < yDim; j++) {
            for(int i = 0; i < xDim; i++) {
                if(movePerPawn[i][j] != null && movePerPawn[i][j].isMarkedForMove()) {
                    movePerPawn[i][j].toMaxMoveList(moves, new ArrayList<BoardPos>());
                }
            }
        }
        return moves;
    }
}
