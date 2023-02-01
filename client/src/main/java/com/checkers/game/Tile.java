package com.checkers.game;

import com.checkers.VisualChecker;
import com.checkers_core.boards.Board;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
    public enum State {
        BASE,
        SELECTED,
        LEGALMOVE,
        PAWNHASMOVE
    }
    
    private final int x;
    private final int y;
    private State state;

    public enum Color {
        BLACK,
        WHITE
    }

    public VisualChecker piece;
    private Tile.Color color;

    Tile(Tile.Color color, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.color = color;
        GridPane.setFillWidth(this, true);
        GridPane.setFillHeight(this, true);

        switch(color)
        {
            case BLACK -> getStyleClass().add("black_field");
            case WHITE -> getStyleClass().add("white_field");
            default -> {}
        }
    }

    void setState(State state)
    {
        this.state = state;
        if(this.state == State.SELECTED)
        {
            getStyleClass().clear();
            getStyleClass().add("field_selected");
        }
        else if(this.state == State.LEGALMOVE)
        {
            getStyleClass().clear();
            getStyleClass().add("field_legalmove");
        }
        else if(this.state == State.PAWNHASMOVE)
        {
            getStyleClass().clear();
            getStyleClass().add("field_pawnhasmove");
        }
        else
        {
            getStyleClass().clear();
            switch(color)
            {
                case BLACK -> getStyleClass().add("black_field");
                case WHITE -> getStyleClass().add("white_field");
                default -> {}
            }
        }
    }

    State getState()
    {
        return state;
    }

    void setPiece(VisualChecker piece)
    {
        if(this.piece != null) {
            getChildren().remove(this.piece);
        }
        
        this.piece = piece;

        if(piece != null) {
            getChildren().add(this.piece);
        }
    }

    VisualChecker getPiece()
    {
        return piece;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Board.BoardPos getPos() { return new Board.BoardPos(x,y); }
}
