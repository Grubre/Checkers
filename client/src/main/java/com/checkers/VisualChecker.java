package com.checkers;

import com.checkers_core.boards.Board;

import javafx.scene.layout.StackPane;

public abstract class VisualChecker extends StackPane
{
    protected final Board.Color color;

    VisualChecker(Board.Color color)
    {
        this.color = color;
    }

    public Board.Color getColor()
    {
        return color;
    }
}
