package com.checkers;

import com.checkers_core.boards.Board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class VisualAscendedChecker extends VisualChecker {
    public VisualAscendedChecker(Board.Color color)
    {
        super(color);
        Ellipse fgEllipse = new Ellipse(25, 20);
        fgEllipse.setStroke(Color.WHITE);
        fgEllipse.setStrokeWidth(1.5);
        
        if(color == Board.Color.BLACK)
            fgEllipse.getStyleClass().add("black_pawn");
        else
            fgEllipse.getStyleClass().add("white_pawn");

        Ellipse bgEllipse = new Ellipse(27,22);
        bgEllipse.setFill(Color.WHITE);
        bgEllipse.setStroke(Color.WHITE);
        bgEllipse.setStrokeWidth(2.5);
        bgEllipse.setTranslateY(2.5);
        //bgEllipse.setTranslateX(0.5);
        getChildren().addAll(bgEllipse, fgEllipse);
    }
}
