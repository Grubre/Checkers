package com.checkers;

import com.checkers_core.RegularPawn;
import com.checkers_core.Board.Color;

import javafx.scene.shape.Circle;

public class Checker extends RegularPawn implements AbstractChecker {
    private Circle circle;
    Checker(Color color) {
        super(color);
        circle = new Circle();
        circle.setRadius(50);
        if(color == Color.BLACK)
        {
            circle.setFill(javafx.scene.paint.Color.RED);
        }
        else if(color == Color.WHITE)
        {
            circle.setFill(javafx.scene.paint.Color.GRAY);
        }
        //TODO Auto-generated constructor stub
    }

    public Circle get_shape()
    {
        return circle;
    }
    
}
