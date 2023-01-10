package com.checkers;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    private final int x,y;
    private boolean selected = false;

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

    void toggle_selection()
    {
        selected = !selected;
        if(selected)
        {
            getStyleClass().add("field_selected");
        }
        else
        {
            getStyleClass().remove("field_selected");
        }
    }

    void set_piece(VisualChecker piece)
    {
        //if(this.piece != null)
        //    getChildren().remove(this.piece);
        
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
