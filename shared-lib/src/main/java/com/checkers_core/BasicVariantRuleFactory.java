package com.checkers_core;

import com.checkers_core.Board.Color;

public class BasicVariantRuleFactory implements AbstractRuleFactory {

    @Override
    public MoveGraph getPossibleMoves(Board board, Color playerColor) {
        MoveGraph moveGraph = new MoveGraph(board.xDim, board.yDim);

        int maxSize = 0;

        boolean canCapture = false;

        for(int j = 0; j < board.yDim; j++)
        {
            for(int i = 0; i < board.xDim; i++)
            {
                Board.BoardPos boardPos = new Board.BoardPos(i, j);
                AbstractPawn piece = board.getPiece(i, j);
                if(piece == null || piece.getColor() != playerColor) {
                    continue;
                }
                MoveNode moveNode = piece.possibleMoves(board, boardPos);
                if(moveNode.hasCaptured()) {
                    canCapture = true;
                }
                moveGraph.setPawnMoveNode(boardPos, moveNode);
                maxSize = Math.max(maxSize, moveNode.getLongestPathLength());
            }
        }

        for(int j = 0; j < board.yDim; j++)
        {
            for(int i = 0; i < board.xDim; i++)
            {
                MoveNode moveNode = moveGraph.getMoveNodeAt(new Board.BoardPos(i, j));
                if(moveNode != null) {
                    if(canCapture && moveNode.hasCaptured() || !canCapture) {
                        moveNode.pruneNonMaxPaths(maxSize);
                    }
                }
            }
        }

        return moveGraph;
    }
    
}
