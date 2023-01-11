package com.checkers;

import com.checkers_core.Board;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    public enum State {
        BASE,
        SELECTED,
        LEGALMOVE
    }
    
    private final int x,y;
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
            case BLACK:
                getStyleClass().add("black_field");
            break;
            case WHITE:
                getStyleClass().add("white_field");
            break;
        }
    }

    void set_state(State state)
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

    State get_state()
    {
        return state;
    }

    void set_piece(VisualChecker piece)
    {
        if(this.piece != null)
            getChildren().remove(this.piece);
        
        this.piece = piece;

        if(piece != null)
            getChildren().add(this.piece);
    }

    VisualChecker get_piece()
    {
        return piece;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
