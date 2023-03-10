package com.checkers_core.boards;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import com.checkers_core.moves.Move;
import com.checkers_core.moves.MoveGraph;
import com.checkers_core.pawns.AbstractPawn;
import com.checkers_core.pawns.AbstractPawnFactory;
import com.checkers_core.rules.AbstractRuleFactory;

public abstract class Board implements Cloneable{
    public enum Color {
        BLACK(0),
        WHITE(1);

        int id;

        Color(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return switch (this) {
                case BLACK -> "black";
                case WHITE -> "white";
            };
        }

        public static Color getOpposite(Color color) {
            return switch (color) {
                case BLACK -> WHITE;
                case WHITE -> BLACK;
            };
        }

        public static Color fromString(String color) {
            return switch (color.toLowerCase(Locale.US)) {
                case "black" -> BLACK;
                case "white" -> WHITE;
                default -> null;
            };
        }
    }

    public static class BoardPos {
        public BoardPos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x;
        public int y;

        @Override
        public final boolean equals(Object other) {
            return other instanceof BoardPos && ((BoardPos) other).x == x && ((BoardPos) other).y == y;
        }
        public int hashCode() {
            return Objects.hash(this,x,y);
        }
    }

    public final int xDim;
    public final int yDim;

    protected AbstractPawn[][] board;

    protected AbstractPawnFactory pawnFactory;

    protected AbstractRuleFactory ruleFactory;

    Board(int xDim, int yDim, AbstractPawnFactory pawnFactory, AbstractRuleFactory ruleFactory) {
        this.xDim = xDim;
        this.yDim = yDim;
        this.pawnFactory = pawnFactory;
        this.ruleFactory = ruleFactory;

        board = new AbstractPawn[xDim][yDim];

        setupBoard();
    }

    public AbstractPawn getPiece(int i, int j) {
        if (!isInBounds(i, j)) {
            return null;
        }
        return board[i][j];
    }

    public AbstractPawn getPiece(BoardPos pawnPos) {
        if (!isInBounds(pawnPos.x, pawnPos.y)) {
            return null;
        }
        return board[pawnPos.x][pawnPos.y];
    }

    public void setPiece(int i, int j, AbstractPawn piece) {
        if (isInBounds(i, j)) {
            board[i][j] = piece;
        }
    }

    public void movePiece(Board.BoardPos piecePos, Board.BoardPos targetPos) {
        board[targetPos.x][targetPos.y] = board[piecePos.x][piecePos.y];
        board[piecePos.x][piecePos.y] = null;

        int directionX = targetPos.x > piecePos.x ? 1 : -1;
        int directionY = targetPos.y > piecePos.y ? 1 : -1;
        int len = Math.abs(targetPos.x - piecePos.x);
        if (len != Math.abs(targetPos.y - piecePos.y)) {
            throw new IllegalArgumentException();
        }

        for (int i = 1; i < len; i++) {
            int enemyX = piecePos.x + directionX * i;
            int enemyY = piecePos.y + directionY * i;
            if (getPiece(enemyX, enemyY) != null &&
                    getPiece(targetPos.x, targetPos.y) != null &&
                    getPiece(enemyX, enemyY).getColor() != getPiece(targetPos.x, targetPos.y).getColor()) {
                setPiece(enemyX, enemyY, null);
            }
        }
    }

    public void movePieceAndUpdate(Move move) {
        for(int i = 0; i < move.visitedFields.size() - 1; i++) {
            movePiece(move.visitedFields.get(i), move.visitedFields.get(i + 1));
        }
        updateAndAscend();
    }

    public void updateAndAscend() {
        for (int j = 0; j < yDim; j++) {
            for (int i = 0; i < xDim; i++) {
                if (board[i][j] != null && board[i][j].canAscend(this, new BoardPos(i, j))) {
                    board[i][j] = pawnFactory.createAscended(board[i][j].getColor());
                }
            }
        }
    }

    public MoveGraph getPossibleMovesForColor(Board.Color color) {
        return ruleFactory.getPossibleMoves(this, color);
    }

    public boolean isInBounds(int x, int y) {
        return 0 <= x && x < xDim && 0 <= y && y < yDim;
    }

    public void setupBoard() {
        ruleFactory.setupBoard(this, this.pawnFactory);
    }

    public int eval(Board.Color maximizingPLayer) {
        return ruleFactory.eval(this, maximizingPLayer);
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    // public abstract Board clone();

    public Optional<Board.Color> gameOver() {
        return ruleFactory.gameOver(this);
    }
}
