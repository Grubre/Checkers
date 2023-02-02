package com.checkers_core.rules;

import java.util.Optional;

import com.checkers_core.boards.Board;
import com.checkers_core.boards.Board.BoardPos;
import com.checkers_core.boards.Board.Color;
import com.checkers_core.moves.MoveGraph;
import com.checkers_core.moves.MoveNode;
import com.checkers_core.pawns.AbstractPawn;
import com.checkers_core.pawns.AbstractPawnFactory;

public class BasicVariantRuleFactory implements AbstractRuleFactory {
    @Override
    public MoveGraph getPossibleMoves(Board board, Color playerColor) {
        MoveGraph moveGraph = new MoveGraph(board.xDim, board.yDim);

        int maxBranchLength = 0;

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
                maxBranchLength = Math.max(maxBranchLength, moveNode.getLongestBranchLength());
            }
        }

        moveGraph.markNonMaxBranches(canCapture, maxBranchLength);

        return moveGraph;
    }
    @Override
    public void setupBoard(Board board, AbstractPawnFactory pawnFactory) {
        for (int j = 0; j < board.yDim; j++) {
            for (int i = 0; i < board.xDim; i++) {
                if (j < board.yDim / 2 - 1 && (i + j) % 2 == 1) {
                    board.setPiece(i, j, pawnFactory.createRegular(Color.WHITE)); 
                } else if (j >= board.yDim / 2 + 1 && (i + j) % 2 == 1) {
                    board.setPiece(i, j, pawnFactory.createRegular(Color.BLACK));
                } else {
                    board.setPiece(i, j, null);
                }
            }
        }
    }

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

    public int eval(Board board, Board.Color maximizingPlayer) {
        int val = 0;
        for(int i = 0; i < board.yDim; i++) {
            for(int j = 0; j < board.xDim; j++) {
                AbstractPawn pawn = board.getPiece(j, i);
                int coeff = 0;
                if(pawn != null) {
                    if(pawn.getColor() == maximizingPlayer) {
                        coeff+=3;
                    } else {
                        coeff--;
                    }
                    if(pawn.isAscended()) {
                        coeff *= 2;
                    }
                }
                val += coeff;
            }
        }
        return val;
    }
    
}
