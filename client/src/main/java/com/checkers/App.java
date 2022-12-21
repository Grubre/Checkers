package com.checkers;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage mStage;

    @Override
    public void start(Stage stage) {
        mStage = stage;

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BLACK);
 
        Rectangle r = new Rectangle(25,25,250,250);
        r.setFill(Color.BLUE);
        root.getChildren().add(r);

        TranslateTransition translate = 
        new TranslateTransition(Duration.millis(750)); 
        translate.setToX(390); 
        translate.setToY(390); 
 
        FillTransition fill = new FillTransition(Duration.millis(750)); 
        fill.setToValue(Color.RED); 
 
        RotateTransition rotate = new RotateTransition(Duration.millis(750)); 
        rotate.setToAngle(360); 
 
        ScaleTransition scale = new ScaleTransition(Duration.millis(750)); 
        scale.setToX(0.1); 
        scale.setToY(0.1); 
 
        ParallelTransition transition = new ParallelTransition(r, 
        translate, fill, rotate, scale); 
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true); 
        transition.play(); 

        Pane pane = new Pane();

        Scene scene2 = new Scene(pane, 500, 500, Color.WHITE);

        scene.addEventFilter(
         KeyEvent.KEY_PRESSED,
         event -> {
            mStage.setScene(scene2);
        });

        scene2.addEventFilter(
         KeyEvent.KEY_PRESSED,
         event -> {
            mStage.setScene(scene);
        });
 
        mStage.setTitle("Checkers");
        mStage.setScene(scene2);
        mStage.show();
    }

    /**
     * @return the main stage, needed for calculating size of gui components
     */
    public static Stage getStage()
    {
        return mStage;
    }

    public static void main(String[] args) {
        launch();
    }

}