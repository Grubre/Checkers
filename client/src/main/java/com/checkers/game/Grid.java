package com.checkers.game;

import java.util.ArrayList;
import java.util.List;

import com.checkers.VisualAscendedChecker;
import com.checkers.VisualBasicChecker;
import com.checkers.game.Tile.Color;
import com.checkers.game.Tile.State;
import com.checkers_core.AbstractPawn;
import com.checkers_core.Board;
import com.checkers_core.MoveGraph;
import com.checkers_core.MoveNode;
import com.checkers_core.Board.BoardPos;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * A container which extends GridPane with added support for multithreaded Cells.
 */
public class Grid extends GridPane {
    private GameController  controller;
    
    private final int xDim;
    private final int yDim;

    private Board board;

    Tile[][] tiles;
    private Tile selected = null;

    private Board.Color playerColor;
    private Board.Color currentPlayerColor;

    private MoveGraph thisTurnsMoveGraph;
    private MoveNode currentMoves = null;
    private boolean isInMove = false;

    public Grid(Board board, Board.Color color, GameController controller)
    {
        this.controller = controller;

        this.xDim = board.xDim;
        this.yDim = board.yDim;
        this.board = board;
        this.playerColor = color;

        currentPlayerColor = Board.Color.WHITE;
        
        board.setupBoard();

        String css = "checkerboard.css";
        this.getStylesheets().add(css);

        addRowsAndColumns();

        tiles = new Tile[xDim][yDim];

        for(int j = 0; j < yDim; j++)
        {
            for(int i = 0; i < xDim; i++)
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

        thisTurnsMoveGraph = board.getPossibleMovesForColor(currentPlayerColor);

        drawBoard();
    }

    public void handleClick(Tile tile)
    {
        if(tile.getState() == State.LEGALMOVE)
        {
            board.movePiece(new BoardPos(selected.getX(), selected.getY()),
                            new BoardPos(tile.getX(), tile.getY()));
            selectNextTile(tile);
            drawBoard();
            
            return;
        }

        if(isInMove) {
            return;
        }

        if(tile.getPiece() == null || tile.getPiece().getColor() != playerColor) {
            System.out.println("Clicked on null");
            selected = null;
            resetTilesState();
            drawBoard();
            return;
        }
        
        selectNewTile(tile);
        drawBoard();

        //////
        controller.queuePiece(tile.getX(), tile.getY());
    }

    private void selectNextTile(Tile tile)
    {
        System.out.println("Continuing the current move!");
        resetTilesState();

        isInMove = true;
        MoveNode nextNode = null;
        for (MoveNode moveNode : currentMoves) {
            if(moveNode.getPos().equals(tile.getPos()))
            {
                nextNode = moveNode;
            }
        }
        currentMoves = nextNode;

        ///////
        controller.addMove(tile.getX() + tile.getY() * board.xDim);

        // The move has ended
        if(currentMoves.getLongestPathLength() <= 1)
        {
            newTurn();

            ////
            controller.endTurn();

            return;
        }

        select(tile);

        //currentMoves.print("");

        setPossibleMovesForSelected();
    }

    private void select(Tile tile)
    {
        selected = tile;
        selected.setState(State.SELECTED);
    }

    public void selectNewTile(Tile tile)
    {
        System.out.println("Starting a new move!");
        resetTilesState();

        select(tile);

        int x = selected.getX();
        int y = selected.getY();
        currentMoves = thisTurnsMoveGraph.getMoveNodeAt(new BoardPos(x, y));
        //currentMoves.print("");

        setPossibleMovesForSelected();
    }

    private void setPossibleMovesForSelected()
    {
        if(currentMoves == null)
        {
            System.out.println("possibleMoves is null");
            return;
        }
        for (MoveNode move : currentMoves) {
            if(!move.isMarkedForMove()) {
                continue;
            }
            Board.BoardPos pos = move.getPos();
            tiles[pos.x][pos.y].setState(State.LEGALMOVE);
        }
    }

    public void resetTilesState()
    {
        for(int j = 0; j < yDim; j++)
        {
            for(int i = 0; i < xDim; i++)
            {
                tiles[i][j].setState(State.BASE);
            }
        }
    }

    public void newTurn()
    {
        selected = null;
        currentMoves = null;
        isInMove = false;
        System.out.println("Starting a new turn");
        board.updateAndAscend();
        resetTilesState();
        drawBoard();

        if(currentPlayerColor == Board.Color.WHITE) {
            currentPlayerColor = Board.Color.BLACK;
        }
        else {
            currentPlayerColor = Board.Color.WHITE;
        }

        thisTurnsMoveGraph = board.getPossibleMovesForColor(currentPlayerColor);

        markPawnsThatCanMove();
    }

    private void markPawnsThatCanMove()
    {
        if(currentPlayerColor != playerColor) {
            return;
        }
        for(int j = 0; j < yDim; j++)
        {
            for(int i = 0; i < xDim; i++)
            {
                boolean hasMove = false;
                MoveNode moveNode = thisTurnsMoveGraph.getMoveNodeAt(new BoardPos(i, j));
                if(moveNode != null) {
                    for (MoveNode move : moveNode) {
                        if(move.isMarkedForMove()) {
                            hasMove = true;
                        }
                    }
                    if(hasMove) {
                        System.out.println("Marked for move");
                        tiles[i][j].setState(State.PAWNHASMOVE);
                    }
                }
            }
        }
    }

    public void drawBoard()
    {
        for(int j = 0; j < yDim; j++)
        {
            for(int i = 0; i < xDim; i++)
            {
                AbstractPawn pawn = board.getPiece(i, j);
                if(pawn == null)
                {
                    tiles[i][j].setPiece(null);
                    //System.out.println("Setting null");
                }
                else if(pawn.isAscended())
                {
                    tiles[i][j].setPiece(new VisualAscendedChecker(pawn.getColor()));
                    //System.out.println("Setting ascended");
                }
                else
                {
                    tiles[i][j].setPiece(new VisualBasicChecker(pawn.getColor()));
                    //System.out.println("Setting non ascended");
                }
            }
        }
    }

    public void setAnchorPaneLayout(Boolean top, Boolean left, Boolean bottom, Boolean right)
    {
        if(top) { AnchorPane.setTopAnchor(this, 0.0); }
        if(left) { AnchorPane.setLeftAnchor(this, 0.0); }
        if(bottom) { AnchorPane.setBottomAnchor(this, 0.0); }
        if(right)  {AnchorPane.setRightAnchor(this, 0.0); }
    }

    /**
     * A method which adds gridWidth amount of columns
     * and gridHeight amount of rows.
     */
    private void addRowsAndColumns()
    {
        for (int i = 0; i < xDim; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setFillWidth(true);
            colConst.setPrefWidth(100);
            colConst.setPercentWidth(100.0 / xDim);
            getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < yDim; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setFillHeight(true);
            rowConst.setPrefHeight(100);
            rowConst.setPercentHeight(100.0 / yDim);
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
    public int getXDim(){return xDim;}
    /**
     * @return the height of the grid.
     */
    public int getYDim(){return yDim;}
    /**
     * @return the game board.
     */
    public Board getBoard() { return board; }
}