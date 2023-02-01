package com.checkers_core;

import java.util.ArrayList;

import com.checkers_core.boards.Board.BoardPos;
import com.checkers_core.boards.Board;
import com.checkers_core.moves.MoveNode;
import com.checkers_core.pawns.AbstractPawn;

public class BasicMoveFinder {
    private final int depth;
    private final Board board;
    private final Board.BoardPos pawnPos;
    private MoveNode moveNode;
    private final int direction;
    private final Board.Color color;
    private final AbstractPawn piece;

    private boolean foundCaptures = false;

    public BasicMoveFinder(Board board, Board.BoardPos boardPos, int direction, int depth)
    {
        this.depth = depth;
        this.board = board;
        this.pawnPos = boardPos;
        this.piece = board.getPiece(pawnPos);
        this.color = piece.getColor();
        this.direction = direction;
        this.moveNode = new MoveNode(pawnPos.x, pawnPos.y, new ArrayList<>());
    }
    
    public void findMoves()
    {
        int xDirs[] = {-1, 1};
        if(board.getPiece(pawnPos).isAscended()) {
            int yDirs[] = {-1, 1};
            findMovesHelper(xDirs, yDirs);
        }
        else {
            int yDirs[] = {direction};
            findMovesHelper(xDirs, yDirs);
        }
    }

    private void findMovesHelper(int xDirs[], int yDirs[])
    {
        for(int xDir : xDirs)
        {
            for(int yDir : yDirs)
            {
                for(int i = 1; i <= depth; i++)
                {
                    if (board.isInBounds(pawnPos.x + i * xDir, pawnPos.y + i * yDir) &&
                        board.getPiece(pawnPos.x + i * xDir, pawnPos.y + i * yDir) == null) {
                        moveNode.possibleMoves.add(new MoveNode(pawnPos.x + i * xDir, pawnPos.y + i * yDir, new ArrayList<>()));
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }

    public void findCaptures()
    {
        int x = pawnPos.x;
        int y = pawnPos.y;
        int xDirs[] = {-1 , 1};
        int yDirs[] = {-1 , 1};
        for (int xDir : xDirs) {
            for (int yDir : yDirs) {
                // Find the first enemy pawn in your way
                for(int d = 1; d <= depth; d++)
                {
                    if (board.getPiece(x + d * xDir, y + d * yDir) != null)
                    {
                        // If it is an enemy pawn you can move there
                        if(board.getPiece(x + d * xDir, y + d * yDir).getColor() != color)
                        {
                            for(int j = 1; j <= depth; j++)
                            {
                                AbstractPawn potentialMove = board.getPiece(x + (d + j) * xDir, y + (d + j) * yDir);
                                if (potentialMove != null)
                                {
                                    break;
                                }
                                MoveNode newMove =
                                findCapturesRecursive(moveNode,
                                new BoardPos(x + (d + j) * xDir, y + (d + j) * yDir),
                                new BoardPos(x + d * xDir, y + d * yDir));

                                if (newMove != null) {
                                    moveNode.possibleMoves.add(newMove);
                                    foundCaptures = true;
                                    moveNode.setHasCaptured(true);
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private MoveNode findCapturesRecursive(MoveNode parentMove, Board.BoardPos newPos, BoardPos removedPiece) {
        int x = newPos.x;
        int y = newPos.y;
        int xDirs[] = {-1 , 1};
        int yDirs[] = {-1 , 1};

        if (board.isInBounds(x, y) && (board.getPiece(x, y) == null || board.getPiece(x, y) == piece)) {
            MoveNode move = new MoveNode(x, y, new ArrayList<>(parentMove.removedPawns));
            move.removedPawns.add(removedPiece);

            for (int xDir : xDirs) {
                for (int yDir : yDirs) {
                    // Find the first enemy pawn in your way
                    for(int d = 1; d <= depth; d++)
                    {
                        if (board.getPiece(x + d * xDir, y + d * yDir) != null)
                        {
                            AbstractPawn triedPiece = board.getPiece(x + d * xDir, y + d * yDir);
                            if (triedPiece != null && triedPiece.getColor() != color &&
                                !move.removedPawns.contains(new Board.BoardPos(x + d * xDir, y + d * yDir)))
                            {
                                for(int j = 1; j <= depth; j++)
                                {
                                    AbstractPawn potentialMove = board.getPiece(x + (d + j) * xDir, y + (d + j) * yDir);
                                    if (potentialMove != null)
                                    {
                                        break;
                                    }
                                    MoveNode newMove =
                                    findCapturesRecursive(moveNode,
                                    new BoardPos(x + (d + j) * xDir, y + (d + j) * yDir),
                                    new BoardPos(x + d * xDir, y + d * yDir));

                                    if (newMove != null) {
                                        move.possibleMoves.add(newMove);
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
            return move;
        }
        return null;
    }

    public boolean foundCaptures()
    {
        return foundCaptures;
    }

    public MoveNode getMoveNode()
    {
        return moveNode;
    }
}
