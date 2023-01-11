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

    public final int x_dim;
    public final int y_dim;

    protected AbstractPawn[][] board;

    protected AbstractPawnFactory pawnFactory;

    Board(int x_dim, int y_dim, AbstractPawnFactory pawnFactory)
    {
        this.x_dim = x_dim;
        this.y_dim = y_dim;
        this.pawnFactory = pawnFactory;

        board = new AbstractPawn[x_dim][y_dim];
    }

    public AbstractPawn get_piece(int i, int j)
    {
        return board[i][j];
    }

    public AbstractPawn[][] get_board()
    {
        return board;
    }

    public void move_piece(Board.BoardPos piecePos, Board.BoardPos targetPos)
    {
        board[targetPos.x][targetPos.y] = board[piecePos.x][piecePos.y];
        board[piecePos.x][piecePos.y] = null;
        update();
    }

    private void update()
    {
        for (int j = 0; j < y_dim; j++) {
            for (int i = 0; i < x_dim; i++) {
                if(board[i][j] != null && board[i][j].can_ascend(this, new BoardPos(i, j)))
                    board[i][j] = pawnFactory.create_ascended(board[i][j].get_color());
            }
        }
    }

    public abstract void setup_board();
    public abstract Optional<Color> game_over();
}
