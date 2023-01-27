package com.checkers;

import com.checkers_core.Board;

import javafx.scene.layout.StackPane;

public abstract class VisualChecker extends StackPane
{
    protected final Board.Color color;

    VisualChecker(Board.Color color)
    {
        this.color = color;
    }

    
    /** 
     * @return Color
     */
    public Board.Color getColor()
    {
        return color;
    }
}
