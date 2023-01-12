package com.checkers_core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.checkers_core.Board.BoardPos;
import com.checkers_core.Board.Color;

public class BasicPawn extends AbstractPawn {
    private int direction;

    public BasicPawn(Color color) {
        super(color);
        if (color == Color.WHITE)
            direction = 1;
        else
            direction = -1;
    }

    class MoveGraph {
        int x;
        int y;
        List<Board.BoardPos> removedPawns;
        List<MoveGraph> possibleMoves = new ArrayList<>();

        public MoveGraph(int x, int y, List<BoardPos> parentRemovedPos) {
            this.x = x;
            this.y = y;
            this.removedPawns = parentRemovedPos;
        }

        public void toMoveList(List<Move> list, List<BoardPos> visitedFields) {
            visitedFields.add(new BoardPos(x, y));

            Move move = new Move(new ArrayList<>(visitedFields), null);

            list.add(move);

            for (MoveGraph newMove : possibleMoves) {
                newMove.toMoveList(list, visitedFields);
            }

            visitedFields.remove(visitedFields.size() - 1);

        }
    }

    private MoveGraph findMoveRecursive(Board board, MoveGraph parentMove, BoardPos newPos, BoardPos removedPiece) {
        int x = newPos.x;
        int y = newPos.y;

        if (board.isInBounds(x, y) && (board.getPiece(x, y) == null || board.getPiece(x, y) == this)) {
            MoveGraph move = new MoveGraph(x, y, new ArrayList<>(parentMove.removedPawns));
            move.removedPawns.add(removedPiece);

            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    AbstractPawn triedPiece = board.getPiece(x + i, y + j);
                    if (triedPiece != null && triedPiece.getColor() != color && !move.removedPawns.contains(new Board.BoardPos(x + i, y + j))) {
                        MoveGraph newMove = findMoveRecursive(board, move, new BoardPos(x + 2 * i, y + 2 * j), new BoardPos(x + i, y + j));
                        if (newMove != null) {
                            move.possibleMoves.add(newMove);
                        }
                    }
                }
            }

            return move;
        }

        return null;
    }

    private MoveGraph findMoveRecursive(Board board, Board.BoardPos newPos) {
        int x = newPos.x;
        int y = newPos.y;

        if (board.isInBounds(x, y) && (board.getPiece(x, y) == null || board.getPiece(x, y) == this)) {
            MoveGraph move = new MoveGraph(x, y, new ArrayList<>());

            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    if (board.getPiece(x + i, y + j) != null && board.getPiece(x + i, y + j).getColor() != color) {
                        MoveGraph newMove = findMoveRecursive(board, move, new BoardPos(x + 2 * i, y + 2 * j), new BoardPos(x + i, y + j));
                        if (newMove != null) {
                            move.possibleMoves.add(newMove);
                        }
                    }
                }
            }

            return move;
        }

        return null;
    }

    @Override
    public List<Move> possibleMoves(Board board, Board.BoardPos boardPos) {
        List<Move> moves = new ArrayList<Move>();
        int x = boardPos.x;
        int y = boardPos.y;

        System.out.println("Possible Moves:");

        Move move = new Move();
        if (board.isInBounds(x - 1, y + direction) && board.getPiece(x - 1, y + direction) == null) {
            move.visitedFields.add(new Board.BoardPos(x - 1, y + direction));
            moves.add(move);
        }
        move = new Move();
        if (board.isInBounds(x + 1, y + direction) && board.getPiece(x + 1, y + direction) == null) {
            move.visitedFields.add(new Board.BoardPos(x + 1, y + direction));
            moves.add(move);
        }

        MoveGraph moveGraph = findMoveRecursive(board, new Board.BoardPos(x, y));

        moveGraph.toMoveList(moves, new ArrayList<>());

        Optional<Move> maxSizeMove = moves.stream().max(Comparator.comparingInt(cmove -> cmove.visitedFields.size()));

        if (maxSizeMove.isPresent()) {
            int maxLen = maxSizeMove.get().visitedFields.size();
            System.out.println("MaxSize = " + maxLen);
            moves = moves
                    .stream()
                    .filter(compMove -> compMove.visitedFields.size() >= maxLen)
                    .collect(Collectors.toList());
        }
        
        moves.stream().forEach(m -> {
            for(int i = 1; i < m.visitedFields.size(); i++)
            {
                Board.BoardPos pos1 = m.visitedFields.get(i - 1);
                Board.BoardPos pos2 = m.visitedFields.get(i);

                m.removedPawns.add(new BoardPos((pos1.x + pos2.x) / 2, (pos1.y + pos2.y) / 2));
            }
        }); {

        }
        return moves;
    }

    // @Override
    // public ArrayList<Move> possibleMoves(Board board, Board.BoardPos boardPos) {
    // ArrayList<Move> moves = new ArrayList<Move>();
    // int x = boardPos.x;
    // int y = boardPos.y;
    // Move move = new Move();
    // if(x > 0 && y > 0 && board.getPiece(x - 1 ,y - 1) == null)
    // {
    // move.visitedFields.add(new Board.BoardPos(x - 1, y - 1));
    // }
    // if(x < board.xDim - 1 && y > 0 && board.getPiece(x + 1 ,y - 1) == null)
    // {
    // move.visitedFields.add(new Board.BoardPos(x + 1, y - 1));
    // }
    // moves.add(move);
    // return moves;
    // }

    @Override
    public Boolean canAscend(Board board, Board.BoardPos boardPos) {
        if (color == Color.BLACK) {
            if (boardPos.y == 0)
                return true;
        } else if (color == Color.WHITE) {
            if (boardPos.y == board.xDim - 1)
                return true;
        }
        return false;
    }

    public Boolean isAscended() {
        return false;
    }
}
