package com.checkers_core;

import java.util.Optional;

public abstract class Board {
    public enum Color{
        BLACK,
        WHITE
    };

    public static class BoardPos{
        public BoardPos(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public int x;
        public int y;
    }

    public final int xDim;
    public final int yDim;

    protected AbstractPawn[][] board;

    protected AbstractPawnFactory pawnFactory;

    Board(int xDim, int yDim, AbstractPawnFactory pawnFactory)
    {
        this.xDim = xDim;
        this.yDim = yDim;
        this.pawnFactory = pawnFactory;

        board = new AbstractPawn[xDim][yDim];
    }

    public AbstractPawn getPiece(int i, int j)
    {
        return board[i][j];
    }

    public AbstractPawn[][] getBoard()
    {
        return board;
    }

    public void movePiece(Board.BoardPos piecePos, Board.BoardPos targetPos)
    {
        board[targetPos.x][targetPos.y] = board[piecePos.x][piecePos.y];
        board[piecePos.x][piecePos.y] = null;
        update();
    }

    private void update()
    {
        for (int j = 0; j < yDim; j++) {
            for (int i = 0; i < xDim; i++) {
                if(board[i][j] != null && board[i][j].canAscend(this, new BoardPos(i, j)))
                    board[i][j] = pawnFactory.create_ascended(board[i][j].getColor());
            }
        }
    }

    public boolean isInBounds(int x, int y)
    {
        return (0 <= x && x < xDim) && (0 <= y && y < yDim);
    }

    public abstract void setupBoard();
    public abstract Optional<Color> gameOver();
}
