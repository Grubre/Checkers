package com.checkers;

import com.checkers_core.Board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VisualBasicChecker extends VisualChecker {
    public VisualBasicChecker(Board.Color color)
    {
        super(color);
        Circle circ = new Circle(25);
        if(color == Board.Color.BLACK)
            circ.setFill(Color.DARKRED);
        else
            circ.setFill(Color.DARKBLUE);
        getChildren().add(circ);
    }
}
