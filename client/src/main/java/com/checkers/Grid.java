package com.checkers;

import java.util.ArrayList;
import java.util.List;

import com.checkers.Tile.Color;
import com.checkers.Tile.State;
import com.checkers_core.AbstractPawn;
import com.checkers_core.Board;
import com.checkers_core.AbstractPawn.Move;
import com.checkers_core.Board.BoardPos;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 * A container which extends GridPane with added support for multithreaded Cells.
 */
public class Grid extends GridPane {
    private final int x_dim;
    private final int y_dim;

    private Board board;

    Tile[][] tiles;
    private Tile selected = null;

    private Board.Color playerColor;

    Grid(Board board, Board.Color color)
    {
        this.x_dim = board.x_dim;
        this.y_dim = board.y_dim;
        this.board = board;
        this.playerColor = color;
        board.setup_board();

        String css = getClass().getResource("checkerboard.css").toExternalForm();
        this.getStylesheets().add(css);

        addRowsAndColumns();

        tiles = new Tile[x_dim][y_dim];

        for(int j = 0; j < y_dim; j++)
        {
            for(int i = 0; i < x_dim; i++)
            {
                Tile tile;
                if((i + j) % 2 == 0)
                {
                    tile = new Tile(Color.WHITE, i, j);
                }
                else {
                    tile = new Tile(Color.BLACK, i, j);
                }
                tile.getStylesheets().add(css);
                tile.setOnMouseClicked(eventAction -> {
                    System.out.println("Clicked tile " + tile.getX() + " " + tile.getY());
                    handleClick(tile);
                });
                tiles[i][j] = tile;
                add(tiles[i][j], i, j);
            }
        }
        drawBoard();
    }

    public void handleClick(Tile tile)
    {
        if(tile.getState() == State.LEGALMOVE)
        {
            board.move_piece(new BoardPos(selected.getX(), selected.getY()),
                             new BoardPos(tile.getX(), tile.getY()));
            drawBoard();
            return;
        }

        if(tile.getPiece() == null || tile.getPiece().get_color() != playerColor)
            return;  
        
        resetTilesState();

        tile.setState(State.SELECTED);

        int x = tile.getX(), y = tile.getY();
        ArrayList<Move> moves = board.get_piece(x, y).possible_moves(board, new Board.BoardPos(x, y));
        for (Move move : moves) {
            for (Board.BoardPos pos : move.visitedFields) {
                tiles[pos.x][pos.y].setState(State.LEGALMOVE);
            }
        }

        selected = tile;
    }

    public void resetTilesState()
    {
        for(int j = 0; j < y_dim; j++)
        {
            for(int i = 0; i < x_dim; i++)
            {
                tiles[i][j].setState(State.BASE);
            }
        }
    }

    public void drawBoard()
    {
        resetTilesState();
        for(int j = 0; j < y_dim; j++)
        {
            for(int i = 0; i < x_dim; i++)
            {
                AbstractPawn pawn = board.get_piece(i, j);
                if(pawn == null)
                {
                    tiles[i][j].setPiece(null);
                    System.out.println("Setting null");
                }
                else if(pawn.is_ascended())
                {
                    tiles[i][j].setPiece(new VisualAscendedChecker(pawn.get_color()));
                    System.out.println("Setting ascended");
                }
                else
                {
                    tiles[i][j].setPiece(new VisualBasicChecker(pawn.get_color()));
                    System.out.println("Setting non ascended");
                }
            }
        }
    }

    public void setAnchorPaneLayout(Boolean top, Boolean left, Boolean bottom, Boolean right)
    {
        if(top)    AnchorPane.setTopAnchor(this, 0.0);
        if(left)   AnchorPane.setLeftAnchor(this, 0.0);
        if(bottom) AnchorPane.setBottomAnchor(this, 0.0);
        if(right)  AnchorPane.setRightAnchor(this, 0.0);
    }

    /**
     * A method which adds gridWidth amount of columns
     * and gridHeight amount of rows.
     */
    private void addRowsAndColumns()
    {
        for (int i = 0; i < x_dim; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setFillWidth(true);
            colConst.setPrefWidth(100);
            colConst.setPercentWidth(100.0 / x_dim);
            getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < y_dim; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setFillHeight(true);
            rowConst.setPrefHeight(100);
            rowConst.setPercentHeight(100.0 / y_dim);
            getRowConstraints().add(rowConst);
        }
    }
    
    public <T extends Node> List<T> getCell(Class<T> conversion, int iIndex, int jIndex) {
        List<T> children = new ArrayList<T>();
        for(Node node : getChildren()) {
            if(conversion.isInstance(node)) {   
                if(getColumnIndex(node) == iIndex) {
                    if(getRowIndex(node) == jIndex) {
                        children.add(conversion.cast(node));
                    }
                }
            }
        }
        return children;
    }
    
    public <T extends Node> void removeCell(Class<T> clazz, int i, int j) {
        getChildren().removeAll(getCell(clazz, i, j));
    }

    
    /**
     * @return the width of the grid.
     */
    public int getXDim(){return x_dim;}
    /**
     * @return the height of the grid.
     */
    public int getYDim(){return y_dim;}
    /**
     * @return the game board.
     */
    public Board getBoard() { return board; }
}