package com.checkers;

import java.util.ArrayList;
import java.util.List;

import com.checkers_core.AbstractPawn;
import com.checkers_core.Board;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A container which extends GridPane with added support for multithreaded Cells.
 */
public class Grid extends GridPane {
    private final int x_dim;
    private final int y_dim;

    private Board board;

    Grid(int x_dim, int y_dim, Board board)
    {
        this.x_dim = x_dim;
        this.y_dim = y_dim;
        this.board = board;
        board.setup_board();

        addRowsAndColumns();

        for(int j = 0; j < y_dim; j++)
        {
            for(int i = 0; i < x_dim; i++)
            {
                Region rect = new Region();
                rect.setPrefHeight(100);
                rect.setPrefWidth(100);
                String css = getClass().getResource("checkerboard.css").toExternalForm();
                this.getStylesheets().add(css);
                rect.getStylesheets().add(css);
                add(rect, i, j);
                if((i + j) % 2 == 0)
                {
                    rect.setId("white_rect");
                }
                else
                {
                    rect.setId("black_rect");
                }
            }
        }
        AbstractPawn[][] fields = board.get_board();

        for(int j = 0; j < y_dim; j++)
        {
            for(int i = 0; i < x_dim; i++)
            {
                if(fields[i][j] != null)
                {
                    add(((Checker)fields[i][j]).get_shape(), i, j);
                }
            }
        }
    }

    public void SetAnchorPaneLayout(Boolean Top, Boolean Left, Boolean Bottom, Boolean Right)
    {
        if(Top)    AnchorPane.setTopAnchor(this, 0.0);
        if(Left)   AnchorPane.setLeftAnchor(this, 0.0);
        if(Bottom) AnchorPane.setBottomAnchor(this, 0.0);
        if(Right)  AnchorPane.setRightAnchor(this, 0.0);
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
            colConst.setPercentWidth(100.0 / x_dim);
            getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < y_dim; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setFillHeight(true);
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
    public int getX_dim(){return x_dim;}
    /**
     * @return the height of the grid.
     */
    public int getY_dim(){return y_dim;}
    /**
     * @return the game board.
     */
    public Board get_board() { return board; }
}