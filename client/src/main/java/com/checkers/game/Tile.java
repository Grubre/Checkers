package com.checkers.game;

import com.checkers.VisualChecker;
import com.checkers_core.Board;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
    public enum State {
        BASE,
        SELECTED,
        LEGALMOVE
    }
    
    private final int x;
    private final int y;
    private State state;

    public enum Color {
        BLACK,
        WHITE
    }

    public VisualChecker piece;

    Tile(Tile.Color color, int x, int y)
    {
        this.x = x;
        this.y = y;
        GridPane.setFillWidth(this, true);
        GridPane.setFillHeight(this, true);

        switch(color)
        {
            case BLACK -> getStyleClass().add("black_field");
            case WHITE -> getStyleClass().add("white_field");
        }
    }

    void setState(State state)
    {
        this.state = state;
        if(this.state == State.SELECTED)
        {
            getStyleClass().remove("field_legalmove");
            getStyleClass().add("field_selected");
        }
        else if(this.state == State.LEGALMOVE)
        {
            getStyleClass().remove("field_selected");
            getStyleClass().add("field_legalmove");
        }
        else
        {
            getStyleClass().remove("field_selected");
            getStyleClass().remove("field_legalmove");
        }
    }

    State getState()
    {
        return state;
    }

    void setPiece(VisualChecker piece)
    {
        if(this.piece != null)
            getChildren().remove(this.piece);
        
        this.piece = piece;

        if(piece != null)
            getChildren().add(this.piece);
    }

    VisualChecker getPiece()
    {
        return piece;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Board.BoardPos getPos() { return new Board.BoardPos(x,y); }
}
