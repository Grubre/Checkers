package com.checkers;

import com.checkers_core.Board;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public abstract class VisualChecker extends StackPane
{
    protected final Board.Color color;

    VisualChecker(Board.Color color)
    {
        this.color = color;
    }

    public Board.Color get_color()
    {
        return color;
    }
}
