package com.checkers_core;

import java.util.Optional;

import com.checkers_core.Board.Color;

public class BasicVariantRuleFactory implements AbstractRuleFactory {
    
    /** 
     * @param board
     * @param playerColor
     * @return MoveGraph
     */
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
    
    /** 
     * @param board
     * @param pawnFactory
     */
    @Override
    public void setupBoard(Board board, AbstractPawnFactory pawnFactory) {
        for (int j = 0; j < board.yDim; j++) {
            for (int i = 0; i < board.xDim; i++) {
                if (j < board.yDim / 2 - 1 && (i + j) % 2 == 1) {
                    board.setPiece(i, j, pawnFactory.create_regular(Color.WHITE)); 
                } else if (j >= board.yDim / 2 + 1 && (i + j) % 2 == 1) {
                    board.setPiece(i, j, pawnFactory.create_regular(Color.BLACK));
                } else {
                    board.setPiece(i, j, null);
                }
            }
        }
    }

    
    /** 
     * @param board
     * @return Optional<Color>
     */
    @Override
    public Optional<Color> gameOver(Board board) {
        int black = 0;
        int white = 0;

        for (int i = 0; i < board.xDim; i++) {
            for (int j = 0; j < board.yDim; j++) {
                AbstractPawn pawn = board.getPiece(i, j);
                if (pawn == null) {
                    continue;
                }
                Color key = pawn.getColor();
                switch (key) {
                    case WHITE -> {
                        white++;
                    }
                    case BLACK -> {
                        black++;
                    }
                    default -> {}
                }
            }
        }

        System.out.println("white " + white + " black " + black);

        if (black == 0) {
            return Optional.of(Color.WHITE);
        } else if (white == 0) {
            return Optional.of(Color.BLACK);
        }
        return Optional.empty();
    }
    
}
